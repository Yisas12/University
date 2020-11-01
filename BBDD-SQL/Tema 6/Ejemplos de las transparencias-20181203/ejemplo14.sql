-- -----------------------------------------------------
-- Ejemplo14: Disparador de instruccion con predicados de
-- operacion. 
-- -----------------------------------------------------
SET SERVEROUTPUT ON;
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MM-YYYY HH24:MI:SS';

-- -----------------------------------------------------
-- Tablas para ejecutar el ejemplo.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas (cod NUMBER(5) PRIMARY KEY, 
  descr VARCHAR2(30), precio NUMBER(11,2));

-- Creacion de tabla de log.
DROP TABLE log_cambios;
CREATE TABLE log_cambios (
       hora DATE,
       usuario VARCHAR2(100),
       cambio VARCHAR2(100)); 

-- -----------------------------------------------------
-- Disparador de instruccion que inserta una fila en la
-- tabla log_cambios cuando se modifica la tabla piezas.
-- -----------------------------------------------------
CREATE OR REPLACE TRIGGER ActualizaLogCambios
AFTER UPDATE OR INSERT OR DELETE
ON piezas
DECLARE
  vUser VARCHAR2(100);
  vOp   VARCHAR2(10);
BEGIN
  vUser := USER;  -- funcion predefinida en PL/SQL
  IF INSERTING THEN   vOp := 'insert';
  ELSIF UPDATING THEN vOp := 'update';
  ELSIF DELETING THEN vOp := 'delete';
  END IF;
  vUser := USER;  -- funcion predefinida en PL/SQL
  INSERT INTO log_cambios VALUES (SYSDATE, vUser, 
    'Cambio en tabla piezas: ' || vOp);
END;
/

-- -----------------------------------------------------
-- Instrucciones para probar el disparador.
-- -----------------------------------------------------
-- Instrucciones para probar el disparador.
delete from log_cambios;
-- La tabla piezas está inicialmente vacía.
delete from piezas where cod = 444; -- no modifica ninguna fila.
insert into piezas values (444, 'pieza con cod. 444', 400.00);
update piezas set precio = precio * 0.10 where cod = 444;
select * from log_cambios;
-- En log_cambios hay *dos* filas, una por cada instruccion de
-- modificacion de datos (aunque no se hayan modificado filas).









