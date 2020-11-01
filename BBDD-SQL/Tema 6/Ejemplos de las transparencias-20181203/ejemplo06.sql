-- -----------------------------------------------------
-- Ejemplo06: Ejemplo de cursor para actualización.
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
-- Bloque del ejemplo06: Ejemplo de cursor para actualizacion.
-- Rebaja un 5% el precio de las piezas de mas de 1000 euros.
-- -----------------------------------------------------
DECLARE
  CURSOR cr_piezas IS
    SELECT cod, descr, precio FROM piezas WHERE precio > 100
      FOR UPDATE OF precio;
BEGIN
  FOR r_piezas IN cr_piezas LOOP
    UPDATE piezas SET precio = precio * 0.95 WHERE CURRENT OF cr_piezas;
  END LOOP;
  COMMIT;
END;
/

-- Para ver el contenido antes y despues de la actualizacion.
SELECT * FROM piezas;

