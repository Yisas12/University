prompt ---------------------------------------------------------------------.
prompt EJERCICIO EVALUABLE 2. GRUPO B. 16/01/2018.
prompt ---------------------------------------------------------------------.
set linesize 300;
SET SERVEROUTPUT ON;
alter session set nls_date_format='DD/MM/YYYY';

DROP TABLE VENTA CASCADE CONSTRAINTS;
DROP TABLE OFERTA CASCADE CONSTRAINTS;
DROP TABLE DPTO CASCADE CONSTRAINTS;
DROP TABLE TIENDA CASCADE CONSTRAINTS;

CREATE TABLE TIENDA (
  IdTienda VARCHAR2(5) PRIMARY KEY,
  Direccion VARCHAR2(40)
);

CREATE TABLE DPTO (
  IdTienda VARCHAR2(5) REFERENCES TIENDA,
  NumDpto NUMBER(3),
  Descr VARCHAR2(40),
  CONSTRAINT PK_DPTO PRIMARY KEY (IdTienda, NumDpto)
);

CREATE TABLE OFERTA (
  IdOferta VARCHAR2(5) PRIMARY KEY,
  IdTienda VARCHAR2(5),
  NumDpto NUMBER(3),
  FechaIni DATE,
  FechaFin DATE,
  Producto VARCHAR2(40),
  UnidadesOfertadas NUMBER(4) NOT NULL,
  UnidadesVendidas NUMBER(4) DEFAULT 0 NOT NULL,
  CONSTRAINT FK_OFERTA_DPTO FOREIGN KEY (IdTienda, NumDpto) REFERENCES DPTO,
  CONSTRAINT CHK_OFERTA CHECK (UnidadesOfertadas > 0)
);

CREATE TABLE VENTA(
  IdVenta VARCHAR2(5) PRIMARY KEY,
  IdOferta VARCHAR2(5) REFERENCES OFERTA,
  FechaVenta DATE,
  Cliente VARCHAR2(40),
  NumUnidades NUMBER(4),
  CONSTRAINT CHK_VENTA CHECK (NumUnidades > 0)
);

INSERT INTO TIENDA VALUES ('37','Conde de PeĂ±alver, 44');
INSERT INTO TIENDA VALUES ('44','Princesa, 25');

INSERT INTO DPTO VALUES ('37',1, 'Papeleria');
INSERT INTO DPTO VALUES ('37',2, 'Informatica');
INSERT INTO DPTO VALUES ('37',3, 'Imagen y sonido');
INSERT INTO DPTO VALUES ('44',1, 'Informatica');
INSERT INTO DPTO VALUES ('44',2, 'Libreria');

INSERT INTO OFERTA VALUES ('o01', '37', 1, TO_CHAR('01-02-2018'), TO_CHAR('01-02-2018'), 'Destructora de papel SuperDestroyer 60', 50, 0);
INSERT INTO OFERTA VALUES ('o02', '37', 2, TO_CHAR('15-03-2018'), TO_CHAR('15-04-2018'), 'Ordenador Victor i7 16Gb 1Tb HD', 15, 0);
INSERT INTO OFERTA VALUES ('o03', '37', 2, TO_CHAR('15-03-2018'), TO_CHAR('15-04-2018'), 'Monitor 27in 4K', 15, 0);
INSERT INTO OFERTA VALUES ('o04', '37', 3, TO_CHAR('01-02-2018'), TO_CHAR('15-05-2018'), 'Barra de sonido Megatron', 20, 0);
INSERT INTO OFERTA VALUES ('o05', '44', 1, TO_CHAR('01-02-2018'), TO_CHAR('15-04-2018'), 'Ordenador Compaq i5 8Gb 1Tb HD', 84, 0);
INSERT INTO OFERTA VALUES ('o06', '44', 1, TO_CHAR('01-02-2018'), TO_CHAR('15-02-2018'), 'Impresora Saikushi 3000', 20, 0);
INSERT INTO OFERTA VALUES ('o07', '44', 2, TO_CHAR('01-02-2018'), TO_CHAR('15-02-2018'), 'Tetralogia El anillo', 25, 0);

--Escribe un procedimiento almacenado OfertasFecha que, dado un identificador de tienda, un n´umero de
--departamento y una fecha, muestre un listado en la consola que indique las ofertas vigentes en ese departamento
--de esa tienda en la fecha dada. Debe mostrar: IdOferta, producto, fecha de fin de la oferta (posterior a la
--fecha dada) y n´umero de unidades disponibles. Los resultados se deben mostrar en orden cronol´ogico por
--FechaIni. Si no hay ofertas, debe mostrar el texto ’ No hay ofertas.’
create or replace procedure OfertasFecha (p_idtienda in varchar2, p_numDpto in number, p_fecha in date) is
cursor pr is
select ofe.idoferta, ofe.producto, ofe.fechafin, ofe.unidadesofertadas - ofe.unidadesvendidas as unidades from oferta ofe
where p_idtienda = ofe.idtienda and p_numDpto = ofe.numDpto
and p_fecha <= ofe.fechafin and p_fecha >= ofe.fechaini
order by ofe.fechaini;

numero integer := 0;

begin
for r_oferta in pr loop
numero := numero + 1;
DBMS_OUTPUT.PUT_LINE('  ' || RPAD(r_oferta.IdOferta,5) || 
                         '  ' || RPAD(r_oferta.Producto,35) ||
                   			 '  ' || TO_CHAR(r_oferta.FechaFin,'DD-MM-YYYY') || 
                    		 '  ' || TO_CHAR(r_oferta.Unidades,'999') || ' unidades');
  END LOOP;
  if numero = 0 then
  DBMS_OUTPUT.PUT_LINE(' No hay ofertas ');
  end if;
end;
/

--Escribe un procedimiento almacenado OfertasTienda que, dado un identificador de tienda y una fecha,
--muestre en la consola la direcci´on de la tienda y las ofertas disponibles en esa fecha en todos sus departamentos.
--Por cada departamento de la tienda debe mostrar la descripci´on del departamento y a continuaci´on debe
--llamar al procedimiento OfertasFecha para mostrar las ofertas de ese dpto. Si no existe ninguna tienda con ese
--identificador, debe manejarse esta situaci´on mediante una excepci´on y mostrar un mensaje de error.
create or replace procedure OfertasTienda (r_idtienda in varchar2, r_fecha in date) is
cursor pa is
select numDpto, descr from dpto
where idtienda = r_idtienda
order by numdpto;
v_Direccion TIENDA.Direccion%TYPE;
begin
select direccion into v_Direccion from tienda
where idtienda = r_idtienda;
 DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------------------------');
  DBMS_OUTPUT.PUT_LINE('OFERTAS DEL DIA ' || TO_CHAR(r_fecha,'DD-MM-YYYY') ||
  		       ' EN LA TIENDA ' || r_IdTienda || ' -- ' || v_Direccion);
  DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------------------------');
for r_dpto in pa loop
DBMS_OUTPUT.PUT_LINE('Departamento: ' || TO_CHAR(r_dpto.NumDpto,'999') ||
    			 ' -- ' || r_dpto.Descr);
    OfertasFecha(r_IdTienda, r_dpto.NumDpto, r_fecha);
  END LOOP;
exception
when no_data_found then
DBMS_OUTPUT.PUT_LINE('No hay ninguna tienda con identificador: ' || r_IdTienda);
END;
/

--Escribe un disparador ActualizaUnidadesVendidas que, cuando se modifiquen los datos de la tabla VENTA
--(por cualquier operaci´on: inserci´on, actualizaci´on o borrado de filas), actualice correctamente el valor de
--la columna UnidadesVendidas en las filas afectadas de OFERTA.
--Incluye en la soluci´on las sentencias necesarias para probar el disparador.
create or replace trigger ActualizaUnidadesVendidas 
after update or delete or insert on Venta
for each row
begin
if inserting then
update oferta set unidadesvendidas = unidadesvendidas + :new.numUnidades
where idoferta = :new.idoferta;
elsif deleting then
update oferta set unidadesvendidas = unidadesvendidas - :old.numunidades
where idoferta = :old.idoferta;
elsif updating then 
update oferta set unidadesvendidas = unidadesvendidas - :old.numunidades
where idoferta = :old.idoferta;

update oferta set unidadesvendidas = unidadesvendidas + :new.numunidades
where idoferta = :new.idoferta;
end if;
end;
/

INSERT INTO VENTA VALUES ('V01','o02', TO_CHAR('15-04-2018'),'Andres Garcia', 2);
INSERT INTO VENTA VALUES ('V02','o02', TO_CHAR('15-03-2018'),'Alvaro Armengol', 3);
INSERT INTO VENTA VALUES ('V03','o06', TO_CHAR('15-01-2018'),'Renato Matina', 5);
UPDATE VENTA SET IdOferta = 'o03', 
                 NumUnidades = 2
WHERE IdVenta = 'V02';   
DELETE FROM VENTA WHERE IdVenta = 'V03';
SELECT * FROM VENTA;
SELECT * FROM OFERTA;
