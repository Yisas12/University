----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:26:36 11/09/2018 
-- Design Name: 
-- Module Name:    controller - Behavioral 
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

entity controller is
  port(
      clk      : in  std_logic;         -- Input clock
      reset    : in  std_logic;         -- General reset
      inicio   : in  std_logic;         -- Start of algorithm
		status   : in std_logic_vector(1 downto 0); 
		control  : out std_logic_vector(4 downto 0);
      done     : out std_logic
       );
end controller;

architecture rtl of controller is
	type t_st is (s0, s1, s2, s3, s4);
	signal current_state, next_state : t_st;
	signal n_zero, b_zero     : std_logic;

	constant c_ldr      : std_logic_vector(4 downto 0) := "10000";
   constant c_sh       : std_logic_vector(4 downto 0) := "01000";
   constant c_ce       : std_logic_vector(4 downto 0) := "00100";
   constant c_ldacc    : std_logic_vector(4 downto 0) := "00010";
   constant c_mux_acc  : std_logic_vector(4 downto 0) := "00001";
	
	component debouncer is
	  port (
		 rst             : in  std_logic;
		 clk             : in  std_logic;
		 x               : in  std_logic;
		 xdeb            : out std_logic;
		 xdebfallingedge : out std_logic;
		 xdebrisingedge  : out std_logic
		 );
	end component;
	
	signal ini_listo,Sig2,Sig3: std_logic;
	
 begin
 (n_zero, b_zero) <= status;
 
  rebotes: 		debouncer 	port map (reset,clk,inicio,ini_listo,Sig2,Sig3); -- Mapeado del eliminador de rebotes
  
 --------------------------------------------------------------------------------------
 -- Proceso Síncrono básico de toda máquina de estados.
 ------------------------------------------------------------------------------------
  p_sincrono : process (clk, reset) is
  begin
    if reset = '1' then
      current_state <= s0;
    elsif rising_edge(clk) then
      current_state <= next_state;
    end if;
  end process  p_sincrono;
 
 --------------------------------------------------------------------------------------
 -- Proceso combinacional de toma de decisión de estado siguiente
 ------------------------------------------------------------------------------------
 p_next_state : process (current_state, ini_listo, n_zero, b_zero) is
 begin
  case current_state is
      when s0 =>
       if ini_listo='1' then
			next_state<=s1;
		 else
			next_state<=s0;
		end if;
      when s1 =>
			next_state<=s2;
      when s2 =>
			if n_zero='1' then
				next_state<=s0;
			elsif b_zero='0' then
				next_state<=s3;
			else
				next_state<=s4;
			end if;
      when s3 => 
			next_state<=s2;
      when s4 =>
			next_state<=s2;
       when others => null;
    end case;
 
 end process p_next_state;
 
 --------------------------------------------------------------------------------------
 -- Proceso combinacional de cálculo de salida de máquina Moore. Sólo depende de current_state
 -- Se usan constantes "one-hot" para configurar la salida de control.
 ------------------------------------------------------------------------------------
 p_outputs : process (current_state) is
 begin
 control<= (others => '0');
 case current_state is
      when s0 =>
			control<= (others => '0');
			done<='1';
      when s1 =>
			control<= c_ldr or c_ldacc ;
			done<='0';
      when s2 =>
			control<= (others => '0');
			done<='0';
      when s3 => 
			control<= c_sh or c_ce ;
			done<='0';
      when s4 =>
			control<= c_sh or c_ce or c_ldacc or c_mux_acc;
			done<='0';
       when others => null;
    end case;
 end process p_outputs;
 
 
end rtl;

