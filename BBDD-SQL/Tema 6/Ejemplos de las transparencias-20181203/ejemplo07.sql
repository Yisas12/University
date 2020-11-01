-- -----------------------------------------------------
-- Ejemplo07: Ejemplo de procedimiento.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Ejemplo de procedimiento
-- -----------------------------------------------------
CREATE OR REPLACE PROCEDURE proc1(p_param VARCHAR2) IS
  v_local VARCHAR2(50) := 'Mi primer procedimiento.';
BEGIN
  DBMS_OUTPUT.PUT_LINE(v_local || ' Param: ' || p_param);
END;
/

-- Bloque para poder probar el procedimiento.
BEGIN
  proc1('Hola mundo!');
END;
/


