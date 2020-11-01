-- -----------------------------------------------------
-- Ejemplo12: Disparador de instruccion.
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
BEGIN
  INSERT INTO log_cambios VALUES (SYSDATE, USER, 'Cambio en tabla piezas');
  -- USER es una funcion predefinida.
END;
/

-- -----------------------------------------------------
-- Instrucciones para probar el disparador.
-- -----------------------------------------------------
-- La tabla piezas está inicialmente vacía.
delete from piezas where cod = 444; -- no modifica ninguna fila.
insert into piezas values (444, 'pieza con cod. 444', 400.00);
select * from log_cambios;
-- En log_cambios hay *dos* filas, una por cada instruccion.








