-- -----------------------------------------------------
-- Ejemplo01: Ejemplo minimo de bloque anónimo
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

DECLARE -- Seccion de declaraciones
  varSaludo VARCHAR2(20);
BEGIN -- Seccion de instrucciones
  varSaludo := 'Hola Mundo';
  DBMS_OUTPUT.PUT_LINE(varSaludo);
END;
/
