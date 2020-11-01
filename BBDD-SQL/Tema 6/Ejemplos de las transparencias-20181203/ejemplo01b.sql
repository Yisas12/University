-- -----------------------------------------------------
-- Ejemplo01b: Ejemplo minimo de comunicacion PL ==> SQL
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Tabla para ejecutar el ejemplo y bloque para cargar datos.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas(
  cod NUMBER(5),
  descr VARCHAR2(30),
  precio NUMBER(11,2));


-- -----------------------------------------------------
-- Ejemplo01b: Ejemplo minimo de comunicacion PL ==> SQL
-- -----------------------------------------------------
BEGIN
  FOR X IN 1..15 LOOP
    INSERT INTO piezas
    VALUES (X, 'pieza num: '||X, X*10);
  END LOOP;
END;
/

SELECT * FROM piezas;
