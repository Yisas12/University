-- -----------------------------------------------------
-- Ejemplo11: Excepciones de usuario.
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
-- Decrementa el precio de las piezas y lanza excepcion
-- si el importe resultante de alguna es < 0.
-- -----------------------------------------------------
CREATE OR REPLACE PROCEDURE oferta_total(p_impte_dto NUMBER) IS
  CURSOR cr_piezas IS
    SELECT cod, descr, precio FROM piezas FOR UPDATE OF precio;
  v_precio piezas.precio%TYPE;
  e_precio_negativo EXCEPTION;
BEGIN  
  FOR r_piezas IN cr_piezas LOOP
    v_precio := r_piezas.precio - p_impte_dto;
    IF v_precio < 0 THEN
      RAISE e_precio_negativo;
    END IF;
    UPDATE piezas SET precio = v_precio
    WHERE CURRENT OF cr_piezas;
  END LOOP;
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Todo bien.');
EXCEPTION
  WHEN e_precio_negativo THEN
    DBMS_OUTPUT.PUT_LINE('Precio negativo en pieza');
    DBMS_OUTPUT.PUT_LINE('Se cancelan todos los cambios.');
    ROLLBACK;
END;
/

-- Bloque para probar el procedimiento
BEGIN
  oferta_total(50);
END;
/
select * from piezas;



