-- -----------------------------------------------------
-- Ejemplo02: Ejemplo de SELECT ... INTO
-- -----------------------------------------------------
SET SERVEROUTPUT ON;

-- -----------------------------------------------------
-- Tabla para ejecutar el ejemplo y bloque para cargar datos.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas (cod NUMBER(5) PRIMARY KEY, 
  descr VARCHAR2(30), precio NUMBER(11,2));

BEGIN
  FOR id IN 1..15 LOOP
    INSERT INTO piezas
    VALUES (id, 'pieza con código ' || id, id*10);
  END LOOP;
END;
/

-- -----------------------------------------------------
-- Bloque del ejemplo02: consulta con SELECT ... INTO
-- -----------------------------------------------------
DECLARE
  v_cod piezas.cod%TYPE := 7;
  v_descr piezas.descr%TYPE;
  v_precio piezas.precio%TYPE;
BEGIN
  SELECT descr, precio INTO v_descr, v_precio
  FROM piezas WHERE cod = v_cod;
  DBMS_OUTPUT.PUT_LINE('----------------------------------------');
  DBMS_OUTPUT.PUT_LINE('Pieza : ' || v_cod || ' - ' || v_descr);
  DBMS_OUTPUT.PUT_LINE('Precio: ' || v_precio);
  DBMS_OUTPUT.PUT_LINE('----------------------------------------');
END;
/
