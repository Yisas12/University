--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   10:34:39 11/09/2018
-- Design Name:   
-- Module Name:   C:/Proyectos TOC/sequential_mul_plus/tb.vhd
-- Project Name:  sequential_mul_plus
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: sequential_mul_plus
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
 
    COMPONENT sequential_mul_plus
    PORT(
         clk : IN  std_logic;
         reset : IN  std_logic;
         inicio : IN  std_logic;
         a_in : IN  std_logic_vector(3 downto 0);
         b_in : IN  std_logic_vector(3 downto 0);
         res_2Displays : OUT  std_logic_vector(13 downto 0);
         done : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal clk : std_logic := '0';
   signal reset : std_logic := '0';
   signal inicio : std_logic := '0';
   signal a_in : std_logic_vector(3 downto 0) := (others => '0');
   signal b_in : std_logic_vector(3 downto 0) := (others => '0');

 	--Outputs
   signal res_2Displays : std_logic_vector(13 downto 0);
   signal done : std_logic;

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: sequential_mul_plus PORT MAP (
          clk => clk,
          reset => reset,
          inicio => inicio,
          a_in => a_in,
          b_in => b_in,
          res_2Displays => res_2Displays,
          done => done
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
		reset<='1';
		wait for 100 ns;
		reset<='0';
		a_in<="0111";
		b_in<="1011";
      wait for 60 ms;
		inicio <='1';
		wait for 100 ns;
		inicio <='0';

		
		wait for 110 ms;
		a_in<="1001";
		b_in<="1001";
		wait for 100 ns;
		inicio <='1';
		wait for 100 ns;
		inicio <='0';
		
		wait for 110 ms;
		a_in<="1010";
		b_in<="1010";
		wait for 100 ns;
		inicio <='1';
		wait for 100 ns;
		inicio <='0';
		
		wait for 110 ms;
		a_in<="0000";
		b_in<="0010";
		wait for 100 ns;
		inicio <='1';
		wait for 100 ns;
		inicio <='0';

		
		wait for 110 ms;
		a_in<="1100";
		b_in<="0101";
		wait for 100 ns;
		inicio <='1';
		wait for 100 ns;
		inicio <='0';
      wait;
   end process;

END;
