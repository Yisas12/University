-- -----------------------------------------------------
-- Ejemplo08: Parametros en procedimientos y funciones.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Parametros de entrada, salida y entrada-salida.
-- -----------------------------------------------------
CREATE OR REPLACE PROCEDURE proc2(p_in IN VARCHAR2, 
       	                          p_out OUT VARCHAR2,
       	                          p_inout IN OUT VARCHAR2) IS
BEGIN
  DBMS_OUTPUT.PUT_LINE('Entrada: p_in: ' || p_in);
  DBMS_OUTPUT.PUT_LINE('Entrada: p_out: ' || p_out);
  DBMS_OUTPUT.PUT_LINE('Entrada: p_inout: ' || p_inout);

--  p_in no puede modificar su valor porque es de entrada.
  p_out := 'este valor sale del procedimiento.';
  p_inout := 'este tambien.';
END;
/

-- Bloque para poder probar el procedimiento.
DECLARE
  v_in VARCHAR2(50):= 'uno';
  v_out VARCHAR2(50) := 'dos';
  v_inout VARCHAR2(50) := 'tres';
BEGIN
  proc2(v_in, v_out, v_inout);
  DBMS_OUTPUT.PUT_LINE('Salida: p_in: ' || v_in);
  DBMS_OUTPUT.PUT_LINE('Salida: p_out: ' || v_out);
  DBMS_OUTPUT.PUT_LINE('Salida: p_inout: ' || v_inout);
END;
/


