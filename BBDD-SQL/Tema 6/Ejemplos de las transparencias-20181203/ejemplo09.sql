-- -----------------------------------------------------
-- Ejemplo09: Valor de retorno en funciones.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Funcion de ejemplo.
-- -----------------------------------------------------
CREATE OR REPLACE FUNCTION fun1(p_param VARCHAR2) RETURN VARCHAR2 IS
BEGIN  
  RETURN '***' || p_param || '***';
END;
/

-- Bloque para probar la funcion.
BEGIN
  DBMS_OUTPUT.PUT_LINE(fun1('HOLA'));
END;
/

-- Se puede utilizar en cualquier contexto.
SELECT fun1(DESCR) FROM PIEZAS where precio > 100;




