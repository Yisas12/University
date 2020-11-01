
library IEEE;
use IEEE.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;


entity fsm is
	port (clk : in std_logic;
			rst : in std_logic;
			clave : in std_logic_vector(7 downto 0);
			boton : in std_logic;
			intentos : out std_logic_vector(3 downto 0);
			abierto : out std_logic_vector(9 downto 0));
end fsm;

architecture Behavioral of fsm is
	type t_state is (inicial_st, tres_st, dos_st, uno_st, final_st);
	signal current_state , next_state : t_state;
	signal clave_acceso : std_logic_vector(7 downto 0);
	
begin
	p_reg: process (clk,rst)
	begin
		if rst='1' then
			clave_acceso <= "00000000";
		elsif rising_edge(clk) then
			if (boton='1' and current_state=inicial_st) then
			clave_acceso <= clave;
			end if;
		end if;	
	end process;

	p_sincrono: process (clk, rst)
	begin
		if rst='1' then
			current_state <= inicial_st;
		elsif rising_edge(clk) then
			current_state <= next_state;
		end if;	
	end process;
	
	p_next_state : process(current_state , clave,clave_acceso, boton)
	begin
		case current_state is
			when inicial_st=>
				intentos<="0000";
				abierto<="1111111111";
				if boton='1' then
					next_state<=tres_st;
				else
					next_state<=current_state;
				end if;
			
			when tres_st=>
				intentos<="0011";
				abierto<="0000000000";
				if (boton='1' and clave=clave_acceso) then
					next_state<=inicial_st;
				elsif (boton='1' and clave/=clave_acceso) then
					next_state<=dos_st;
				else
					next_state<=current_state;
				end if;
			
			when dos_st=>
				intentos<="0010";
				abierto<="0000000000";
				if (boton='1' and clave=clave_acceso) then
					next_state<=inicial_st;
				elsif (boton='1' and clave/=clave_acceso) then
					next_state<=uno_st;
				else
					next_state<=current_state;
				end if;
			
			when uno_st=>
			intentos<="0001";
			abierto<="0000000000";
				if (boton='1' and clave=clave_acceso) then
					next_state<=inicial_st;
				elsif (boton='1' and clave/=clave_acceso) then
					next_state<=final_st;
				else
					next_state<=current_state;
				end if;
			
			when final_st=>
				intentos<="1111";
				abierto<="0000000000";
				next_state<=current_state;
			when others=>
					intentos<="0000";
					abierto<="1111111111";
					next_state<=inicial_st;
		end case;
		
	end process;
		

end Behavioral;

