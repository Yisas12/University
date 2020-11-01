-- -----------------------------------------------------
-- Ejemplo10: Captura de excepciones.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- 
-- -----------------------------------------------------
CREATE OR REPLACE PROCEDURE manejo_excepciones  IS
  v_cod piezas.cod%TYPE;
BEGIN  
  SELECT cod INTO v_cod FROM piezas WHERE 1=2;
  DBMS_OUTPUT.PUT_LINE('Todo bien.');
EXCEPTION
  WHEN TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('Consulta SELECT INTO devuelve varias filas.');
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Consulta SELECT INTO no devuelve ninguna fila.');
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Otro error : ' || SQLCODE);
    DBMS_OUTPUT.PUT_LINE('con mensaje: ' || SQLERRM);
END;
/

-- Bloque para probar la funcion.
BEGIN
  manejo_excepciones;
END;
/




