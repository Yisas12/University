-- -----------------------------------------------------
-- Ejemplo03: Ejemplo de cursor con bucle LOOP.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Tabla para ejecutar el ejemplo y bloque para cargar datos de prueba.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas (cod NUMBER(5) PRIMARY KEY, 
  descr VARCHAR2(30), precio NUMBER(11,2));

BEGIN
  FOR id IN 1..15 LOOP
    INSERT INTO piezas
    VALUES (id, 'pieza con codigo ' || id, id*10);
  END LOOP;
END;
/

-- -----------------------------------------------------
-- Bloque del ejemplo03: consulta con cursor en bucle LOOP
-- -----------------------------------------------------
DECLARE
  CURSOR cr_piezas IS
    SELECT cod, descr, precio FROM piezas WHERE precio > 100;
  v_cod piezas.cod%TYPE;
  v_descr piezas.descr%TYPE;
  v_precio piezas.precio%TYPE;
BEGIN
  OPEN cr_piezas;
  LOOP
    FETCH cr_piezas INTO v_cod, v_descr, v_precio;
    EXIT WHEN cr_piezas%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(v_cod,'99999') || ' - ' || 
                         RPAD(v_descr,25) || ' ' || 
                         TO_CHAR(v_precio,'99G999D99'));
  END LOOP;
  CLOSE cr_piezas;
END;
/
