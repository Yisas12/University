--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   17:56:25 10/11/2018
-- Design Name:   
-- Module Name:   C:/Users/csanchez/Desktop/Curso 2018_2019/TOC/Proyectos/cerrojo3/tb.vhd
-- Project Name:  cerrojo3
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: cerrojo
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY tb IS
END tb;
 
ARCHITECTURE behavior OF tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT cerrojo
    PORT(
         clk : IN  std_logic;
         rst : IN  std_logic;
         boton : IN  std_logic;
         clave : IN  std_logic_vector(7 downto 0);
         intentos : OUT  std_logic_vector(6 downto 0);
         abierto : OUT  std_logic_vector(9 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal rst : std_logic := '0';
   signal boton : std_logic := '0';
   signal clave : std_logic_vector(7 downto 0) := (others => '0');

 	--Outputs
   signal intentos : std_logic_vector(6 downto 0);
   signal abierto : std_logic_vector(9 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: cerrojo PORT MAP (
          clk => clk,
          rst => rst,
          boton => boton,
          clave => clave,
          intentos => intentos,
          abierto => abierto
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      
		clave<="11001100";
      wait for 20 ns;
		rst<='1';
		wait for 10 ns;
		boton<='1';
		wait for 17 ns;
		rst<='0';
		wait for 15 ns;
		boton<='0';
		wait for 5 ns;
		clave<="11000000";
		wait for 25 ns;
		boton<='1';

      wait;
   end process;

END;
