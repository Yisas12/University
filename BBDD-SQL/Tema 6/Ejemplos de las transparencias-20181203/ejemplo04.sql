-- -----------------------------------------------------
-- Ejemplo04: Ejemplo de cursor con variable de tipo registro.
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
-- Bloque del ejemplo04: consulta con cursor con variable de tipo registro.
-- -----------------------------------------------------
DECLARE
  CURSOR cr_piezas IS
    SELECT cod, descr, precio FROM piezas WHERE precio > 100;
  r_piezas cr_piezas%ROWTYPE;
BEGIN
  OPEN cr_piezas;
  LOOP
    FETCH cr_piezas INTO r_piezas;
    EXIT WHEN cr_piezas%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(r_piezas.cod,'99999') || ' - ' || 
                         RPAD(r_piezas.descr,25) || ' ' || 
                         TO_CHAR(r_piezas.precio,'99G999D99'));
  END LOOP;
  CLOSE cr_piezas;
END;
/
