----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:21:02 11/09/2018 
-- Design Name: 
-- Module Name:    sequential_mul_plus - Behavioral 
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
use ieee.std_logic_1164.all;

----------------------------------------------------------------------------------
-- La Entity de la máquina contiene las señales externas del proyecto.
-- El resultado res_2Display tiene 14 pines para 2 dígitos. 13-7 Decenas. 6-0 Unidades.
----------------------------------------------------------------------------------
entity sequential_mul_plus is
  port(clk           : in  std_logic;   -- Input clock
       reset         : in  std_logic;   -- General reset
       inicio        : in  std_logic;   -- Start of algorithm
       a_in        	: in  std_logic_vector(3 downto 0);   
       b_in    		: in  std_logic_vector(3 downto 0);    
       res_2Displays	: out std_logic_vector(13 downto 0); 
       done       	: out std_logic   
       );
end sequential_mul_plus;

architecture structural of sequential_mul_plus is
  signal control : std_logic_vector(4 downto 0);   -- Control signals
  signal status  : std_logic_vector(1 downto 0);   -- Status signals
  
  -- Las señales de Control bajan de la UC para dar órdenes al CD.
  -- Las señales de status suben a la UC para que tome decisiones de transición.
  
   component controller
    port (
      clk      : in  std_logic;         -- Input clock
      reset    : in  std_logic;         -- General reset
      inicio   : in  std_logic;         -- Start of algorithm
		status   : in std_logic_vector(1 downto 0); 
		control  : out std_logic_vector(4 downto 0);
      done     : out std_logic 
      );
  end component;

  component datapath
    port (
      clk      			: in  std_logic;    -- Input clock
      reset    			: in  std_logic;    -- General reset
		a_in     			: in  std_logic_vector(3 downto 0);   
      b_in    				: in  std_logic_vector(3 downto 0);
		control 			   : in  std_logic_vector(4 downto 0);		
      res_2Displays		: out std_logic_vector(13 downto 0);  
		status  				: out std_logic_vector(1 downto 0)
      );
  end component;
begin

  uc : controller port map (
    clk      => clk,                    -- Input clock
    reset    => reset,                  -- General reset
	 inicio	 => inicio,
	 status   => status,
	 control  => control,
	 done     => done
    );

  dp : datapath port map
    (clk     => clk,              -- Input clock
     reset   => reset,            -- General reset
     a_in    => a_in,
     b_in    => b_in,
	  control => control, 
     res_2Displays => res_2Displays,
	  status  => status
     );

end structural;
