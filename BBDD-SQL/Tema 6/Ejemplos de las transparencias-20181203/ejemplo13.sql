-- -----------------------------------------------------
-- Ejemplo13: Disparador de fila y uso de :OLD y :NEW.
-- -----------------------------------------------------
SET SERVEROUTPUT ON;
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MM-YYYY HH:MI:SS';

-- -----------------------------------------------------
-- Tabla para ejecutar el ejemplo y carga de datos de prueba.
-- -----------------------------------------------------
DROP TABLE piezas;
CREATE TABLE piezas (cod NUMBER(5) PRIMARY KEY, 
  descr VARCHAR2(30), precio NUMBER(11,2));

insert into piezas values (444, 'Pieza num. 444', 400.00);
insert into piezas values (555, 'Pieza num. 555', 500.00);

-- -----------------------------------------------------
-- Disparador de fila que escribe un mensaje en la consola
-- cada vez que una pieza se modifica.
-- -----------------------------------------------------
CREATE OR REPLACE TRIGGER RecordsTest
AFTER UPDATE ON piezas FOR EACH ROW
BEGIN
  DBMS_OUTPUT.PUT_LINE('Actualización de una fila de la tabla piezas.');
  DBMS_OUTPUT.PUT_LINE(' Valor antiguo: ' || :OLD.cod || ' - ' || :OLD.descr || ' - ' || :OLD.precio);
  DBMS_OUTPUT.PUT_LINE(' Valor nuevo  : ' || :NEW.cod || ' - ' || :NEW.descr || ' - ' || :NEW.precio);
END;
/

-- Sentencias para comprobar el disparador.
update piezas set precio = precio * 1.05;
commit;




