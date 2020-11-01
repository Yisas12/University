
library IEEE;
use IEEE.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

entity cerrojo is
	port (clk : in std_logic;
			rst : in std_logic;
			boton : in std_logic;
			clave : in std_logic_vector(7 downto 0);
			intentos : out std_logic_vector(6 downto 0);
			abierto : out std_logic_vector(9 downto 0));
end cerrojo;

architecture Behavioral of cerrojo is
	--component rebotes
	--	port (
	--			rst             : in  std_logic;
	--			clk             : in  std_logic;
	--			x               : in  std_logic;
	--			xdeb            : out std_logic;
	--			xdebfallingedge : out std_logic;
	--			xdebrisingedge  : out std_logic
	--	);
	--end component;

	component conv7
		port (	x       : in  std_logic_vector (3 downto 0);
					display : out std_logic_vector (6 downto 0)
		);
	end component;

	component fsm
		port (clk : in std_logic;
				rst : in std_logic;
				clave : in std_logic_vector(7 downto 0);
				boton : in std_logic;
				intentos : out std_logic_vector(3 downto 0);
				abierto : out std_logic_vector(9 downto 0)
		);
	end component;

	---signal S1,S2,S3: std_logic;
	signal intentos_BCD: std_logic_vector(3 downto 0);
begin
	--debouncer: rebotes port map (rst,clk,boton,S1,S2,S3);
	convert: conv7 port map (intentos_BCD,intentos);
	maquina: fsm port map(clk,rst,clave,boton,intentos_BCD,abierto);

end Behavioral;

