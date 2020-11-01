----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:27:23 11/09/2018 
-- Design Name: 
-- Module Name:    datapath - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library ieee;
use IEEE.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

entity datapath is
  port(
      clk      : in  std_logic;    -- Input clock
      reset    : in  std_logic;    -- General reset
		a_in     : in  std_logic_vector(3 downto 0);   
      b_in    	: in  std_logic_vector(3 downto 0); 
		control  : in  std_logic_vector(4 downto 0);		
      res_2Displays		: out std_logic_vector(13 downto 0);
		status   : out std_logic_vector(1 downto 0)
       );
end datapath;

architecture rtl of datapath is
------------------------------------------------------------------------------
  -- Dentro de la Architecture necesitamos un puñado de señales auxiliares.
  -- Las primeras son para desglosar el vector de control.
  -- Las de ancho 8 son señales que dan vida a los registros, al mux y al sumador.
----------------------------------------------------------------------------
 signal ldr,sh,ce,ldacc,mux_acc : std_logic;
 signal n_zero : std_logic;
 signal a,b,acc, mux_output,add_res : std_logic_vector(7 downto 0);
 signal n : std_logic_vector(2 downto 0);
 constant c_4_ceros : std_logic_vector(3 downto 0):= "0000";

	component conv7_BCD_2Displays is
	  port (x       : in  std_logic_vector (7 downto 0);
			  display : out std_logic_vector (13 downto 0));
	end component;


 begin

 ------------------------------------------------------------------------------
  -- Al comenzar realizamos las conexiones pertinentes
 ----------------------------------------------------------------------------
	(ldr,sh,ce,ldacc,mux_acc)<=control; -- control(4) es ldr.
	 status <= (n_zero & b(0));-- status(1) es n_zero y  status(0) es b(0)
	 conversor: conv7_BCD_2Displays port map (acc,res_2Displays); -- Bits 13-7 son un display. 6-0 el otro.
	 
 -----------------******************************------------------------------
  -- Se implementan todos los componentes de la ruta con process.
 ----------------******************************----------------------------------------
  -----------------------------------------------------------------------------
  -- Registro desplazamiento a izquierda A
  -----------------------------------------------------------------------------
  p_areg : process (clk, reset) is
  
  begin
    if reset = '1' then
      a <= (others => '0');
    elsif rising_edge(clk) then
      if ldr = '1' then
        a <= c_4_ceros & a_in;
		elsif sh = '1' then
				a <=  a(6 downto 0)&'0';
		end if;
    end if;
  end process p_areg;
  -----------------------------------------------------------------------------
  -- Registro desplazamiento a derecha B
  -----------------------------------------------------------------------------
  p_breg : process (clk, reset) is
  
  begin
    if reset = '1' then
      b <= (others => '0');
    elsif rising_edge(clk) then
      if ldr = '1' then
        b <= c_4_ceros & b_in;
		elsif sh = '1' then
			b <= '0' & b(7 downto 1);
		end if;
    end if;
  end process p_breg;
	
  -----------------------------------------------------------------------------
  -- Registro con carga paralelo ACC
  -----------------------------------------------------------------------------
  p_accreg : process (clk, reset) is
  begin
    if reset = '1' then
      acc <= (others => '0');
    elsif rising_edge(clk) then
      if ldacc = '1' then
        acc <= mux_output;
      end if;
    end if;
  end process p_accreg;
  -----------------------------------------------------------------------------
  -- Contador descendente (n). Se necesita libreria ieee.std_logic_arith.all;
  -----------------------------------------------------------------------------
  p_ncounter : process (clk, reset) is
  begin
    if reset = '1' then 
      n <= (others => '0');
    elsif rising_edge(clk) then
		if ldr = '1' then
			n <= "100";
		elsif ce = '1' then
				n <= n - 1;
		end if;
    end if;
  end process p_ncounter;
  -----------------------------------------------------------------------------
  -- Multiplexor
  -----------------------------------------------------------------------------
  p_mux : process (mux_acc, add_res) is
  begin  
    if mux_acc = '0' then
      mux_output <= (others => '0');
    else
      mux_output <= add_res;
    end if;
  end process p_mux;
  -----------------------------------------------------------------------------
  -- Sumador. Se necesita libreria ieee.std_logic_arith.all;
  -----------------------------------------------------------------------------
  p_add : process (acc, a) is
  begin  
		add_res <= acc + a ;
  end process p_add;
  -----------------------------------------------------------------------------
  -- Comparador con cero
  -----------------------------------------------------------------------------
  p_cmp : process (n) is
  begin
    if n = "000" then
      n_zero <= '1';
    else
      n_zero <= '0';
    end if;
  end process p_cmp;		
  
end rtl;
