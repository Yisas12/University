--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:35:44 10/01/2018
-- Design Name:   
-- Module Name:   C:/Xilinx/14.1/ISE_Projects/UCM/Practica_1b/tb.vhd
-- Project Name:  Practica_1b
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: registro_siso
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
library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_textio.all;
use std.textio.all;


entity tb_registro_siso is
end tb_registro_siso;

architecture beh of tb_registro_siso is

-- declaracion del componente que vamos a simular

  component registro_siso
    port(
      rst : in  std_logic;
      clk : in  std_logic;
      es  : in  std_logic;
      ss  : out std_logic
      );
  end component;

--entradas
  signal rst : std_logic;
  signal clk : std_logic;
  signal es  : std_logic;

--salidas
  signal ss : std_logic;

--se define el periodo de reloj 
  constant clk_period : time := 50 ns;

begin

  -- instanciacion de la unidad a simular 

  uut : registro_siso port map (
    rst => rst,
    clk => clk,
    es  => es,
    ss  => ss
    );

  -- definicion del process de reloj
  p_reloj : process
  begin
    clk <= '0';
    wait for clk_period/2;
    clk <= '1';
    wait for clk_period/2;
  end process;


  --proceso de estimulos
  p_stim : process
  begin
    -- se mantiene el rst activado durante 50 ns.
    rst <= '1';
    es  <= '0';
    wait for 50 ns;
    rst <= '0';
    es  <= '0';
    wait for 50 ns;
    rst <= '0';
    es  <= '1';
    wait for 50 ns;
    rst <= '0';
    es  <= '0';
    wait for 50 ns;
    rst <= '0';
    es  <= '1';
    wait for 50 ns;
    rst <= '0';
    es  <= '1';
    wait for 50 ns;
    rst <= '0';
    es  <= '0';
    wait for 50 ns;
    rst <= '0';
    es  <= '1';
    wait for 50 ns;
    rst <= '0';
    es  <= '1';
    wait for 50 ns;
    rst <= '0';
    es  <= '0';
    wait for 50 ns;
    rst <= '0';
    es  <= '0';
    wait;
  end process;

end beh;