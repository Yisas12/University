-- -----------------------------------------------------
-- Ejemplo05: Ejemplo de cursor con bucle FOR de cursor.
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
-- Bloque del ejemplo05: consulta con cursor en bucle FOR de cursor.
-- -----------------------------------------------------
DECLARE
  CURSOR cr_piezas IS
    SELECT cod, descr, precio FROM piezas WHERE precio > 100;
BEGIN
  FOR r_piezas IN cr_piezas LOOP
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(r_piezas.cod,'99999') || ' - ' || 
                         RPAD(r_piezas.descr,25) || ' ' || 
                         TO_CHAR(r_piezas.precio,'99G999D99'));
  END LOOP;
END;
/
