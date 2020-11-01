-- -----------------------------------------------------
-- Ejemplo13: Disparador de fila.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MM-YYYY HH:MI:SS';

-- -----------------------------------------------------
-- Tablas para ejecutar el ejemplo.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas (cod NUMBER(5) PRIMARY KEY, 
  descr VARCHAR2(30), precio NUMBER(11,2));

-- Creacion de tabla de log y secuencia.
DROP SEQUENCE SEQCambios;
CREATE SEQUENCE SEQCambios START WITH 1;
DROP TABLE log_cambios;
CREATE TABLE log_cambios (
       IdSeq NUMBER PRIMARY KEY, 
       usuario VARCHAR2(100), 
       cambio VARCHAR2(100), 
       hora DATE);

-- -----------------------------------------------------
-- Disparador de fila que inserta una fila en la
-- tabla log_cambios cuando se modifica la tabla piezas.
-- Se ejecuta una vez por cada fila que se modifica.
-- -----------------------------------------------------
CREATE OR REPLACE TRIGGER ActualizaLogCambiosFila
AFTER UPDATE OR INSERT OR DELETE
ON piezas
FOR EACH ROW
--WHEN (piezas.precio > 1000)
DECLARE
  vUser VARCHAR2(100);
BEGIN
  vUser := USER;  -- funcion predefinida en PL/SQL
  IF INSERTING OR UPDATING THEN
    INSERT INTO log_cambios VALUES (SEQCambios.NEXTVAL, vUser, 
      'Insertando o modificando tabla piezas. Codigo: ' ||
      :NEW.cod, SYSDATE);
  ELSIF DELETING THEN
    INSERT INTO log_cambios VALUES (SEQCambios.NEXTVAL, vUser, 
      'Borrando fila en tabla piezas. Codigo: ' || :OLD.cod, SYSDATE);
  END IF;
END;
/

-- Instrucciones para probar el disparador.
delete from log_cambios;
delete from piezas; -- por si existe alguna fila.
update piezas set precio = 33 where cod = 444;
insert into piezas values (444, 'pieza con cod. 444', 400.00);
insert into piezas values (555, 'pieza con cod. 555', 500.00);
delete from piezas;

select * from log_cambios;





