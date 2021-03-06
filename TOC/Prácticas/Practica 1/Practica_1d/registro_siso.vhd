----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    09:48:02 10/03/2018 
-- Design Name: 
-- Module Name:    registro_siso - Behavioral 
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
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity registro_siso is
	port (rst : in  std_logic;
			clk_100mhz : in  std_logic; --para implementar añadimos el _100mhz
			--es  : in  std_logic;
			ep  : in  std_logic_vector(7 downto 0);
			load: in  std_logic;
			ss  : out std_logic);
end registro_siso;

architecture Behavioral of registro_siso is
	signal clk_int : std_logic;
	signal data    : std_logic_vector(7 downto 0);
	
	------------------------------------------------------
	-- Descomentar para implementación
	------------------------------------------------------
	
	component divisor is
		port(rst     : in  std_logic;
			  clk_100mhz     : in  std_logic;
			  clk_1hz : out std_logic);
	end component;
	
begin
	----------------------------------------------------------------------
	-- Descomentar cuando se vaya a realizar la implementación en la FPGA
	----------------------------------------------------------------------
	i_clk_int : divisor port map(
		rst     => rst,
		clk_100mhz     => clk_100mhz,
		clk_1hz => clk_int);
	----------------------------------------------------------------------
	-- Comentar cuando se vaya a realizar la implementación en la FPGA
	----------------------------------------------------------------------  
	--clk_int <= clk;
	
	p_reg : process(clk_int, rst)
	begin
		if rst = '1' then
			data <= "00000000";
		elsif rising_edge(clk_int) then
			if (load = '1') then
				data <= ep;
			else
				data(6 downto 0) <= data(7 downto 1);
			end if;			
    end if;
  end process p_reg; 
  ss <= data(0); 

end Behavioral;


