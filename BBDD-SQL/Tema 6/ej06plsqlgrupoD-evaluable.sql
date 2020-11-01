prompt ---------------------------------------------------------------------.
prompt EJERCICIO EVALUABLE 2. GRUPO D. 15/01/2018.
prompt ---------------------------------------------------------------------.
set linesize 300;
SET SERVEROUTPUT ON;
alter session set nls_date_format='DD/MM/YYYY HH24:MI';

DROP TABLE VENTA CASCADE CONSTRAINTS;
DROP TABLE PASE CASCADE CONSTRAINTS;
DROP TABLE SALA CASCADE CONSTRAINTS;
DROP TABLE CINE CASCADE CONSTRAINTS;

CREATE TABLE CINE (
  IdCine VARCHAR2(5) PRIMARY KEY,
  Direccion VARCHAR2(40)
);

CREATE TABLE SALA (
  IdCine VARCHAR2(5) REFERENCES CINE,
  NumSala NUMBER(3),
  Aforo NUMBER(4),
  CONSTRAINT PK_SALA PRIMARY KEY (IdCine, NumSala)
);

CREATE TABLE PASE (
  IdCine VARCHAR2(5),
  NumSala NUMBER(3),
  HoraIni DATE,
  Titulo VARCHAR2(40),
  EntradasVendidas NUMBER(4) DEFAULT 0 NOT NULL,
  CONSTRAINT FK_PASE_SALA FOREIGN KEY (IdCine, NumSala) REFERENCES SALA,
  CONSTRAINT PK_PASE PRIMARY KEY (IdCine, NumSala, HoraIni)
);

CREATE TABLE VENTA(
  IdVenta VARCHAR2(5) PRIMARY KEY,
  IdCine VARCHAR2(5),
  NumSala NUMBER(3),
  HoraIni DATE,
  NumEntradas NUMBER(4),
  CONSTRAINT FK_VENTA_PASE FOREIGN KEY (IdCine, NumSala, HoraIni) REFERENCES PASE,
  CONSTRAINT CK_VENTA CHECK (NumEntradas > 0)
);

INSERT INTO CINE VALUES ('37','Conde de PeĂ±alver, 44');
INSERT INTO CINE VALUES ('44','Princesa, 25');

INSERT INTO SALA VALUES ('37',1, 250);
INSERT INTO SALA VALUES ('37',2, 350);
INSERT INTO SALA VALUES ('37',3, 185);
INSERT INTO SALA VALUES ('44',1, 125);
INSERT INTO SALA VALUES ('44',2, 97);

INSERT INTO PASE VALUES ('37', 1, TO_CHAR('15-01-2018 15:30'), 'Lo que el viento se llevo', 22);
INSERT INTO PASE VALUES ('37', 1, TO_CHAR('15-01-2018 22:30'), 'La reina de Africa', 15);
INSERT INTO PASE VALUES ('37',2, TO_CHAR('15-01-2018 16:00'), 'George de la jungla', 44);
INSERT INTO PASE VALUES ('37',3, TO_CHAR('15-01-2018 22:00'), 'Miguel Strogoff', 20);
INSERT INTO PASE VALUES ('44',1, TO_CHAR('15-01-2018 15:00'), 'La guerra de las galaxias', 84);
INSERT INTO PASE VALUES ('44',1, TO_CHAR('15-01-2018 18:00'), 'El imperio contraataca', 60);
INSERT INTO PASE VALUES ('44',1, TO_CHAR('15-01-2018 21:00'), 'El retorno del Jedi', 88);
INSERT INTO PASE VALUES ('44',2, TO_CHAR('15-01-2018 18:00'), 'La casa de la pradera: la pelicula', 25);


create or replace procedure PasesSala (p_idcine in varchar2, p_numsala in number) is
cursor pr is
select titulo, horaini, s.aforo - pa.entradasvendidas as asientos from pase pa join sala s on pa.idcine = s.idcine
where p_idcine = s.idcine and p_numsala = s.numsala
order by horaini;

begin
for r_c in pr loop
DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(r_c.HoraIni,'HH24:MI') || 
                         '  ' || RPAD(r_c.Titulo,30) ||
			 '  ' || TO_CHAR(r_c.asientos,'999') || ' asientos disponibles');
	end loop;
end;
/


create or replace procedure cartelera (p_idcine in varchar2) is
cursor pa is
select numsala, idcine from sala
where p_idcine = idcine
order by numsala;
v_direccion cine.direccion%type;
begin
select direccion into v_direccion from cine
where idcine = p_idcine;
 DBMS_OUTPUT.PUT_LINE('------------------------------------------------------------');
  DBMS_OUTPUT.PUT_LINE('CARTELERA DEL CINE ' ||
                        p_IdCine || ' -- ' || v_Direccion);
  DBMS_OUTPUT.PUT_LINE('------------------------------------------------------------');
for rj in pa loop
DBMS_OUTPUT.PUT_LINE('Sala: ' || TO_CHAR(rj.NumSala,'999'));
    PasesSala(p_IdCine, rj.NumSala);
end loop;
exception
when no_data_found then
DBMS_OUTPUT.PUT_LINE('No existe cine con ese id');
end;
/


create or replace trigger ActualizaEntradasVendidas 
after update or insert or delete on Venta
for each row
begin
if inserting then
update pase set entradasvendidas = entradasvendidas + :new.numentradas
where idcine = :new.idcine and horaini = :new.horaini and numsala = :new.numsala;
elsif deleting then
update pase set entradasvendidas = entradasvendidas - :old.numentradas
where idcine = :old.idcine and horaini = :old.horaini and numsala = :old.numsala;
elsif updating then
update pase set entradasvendidas = entradasvendidas - :old.numentradas
where idcine = :old.idcine and horaini = :old.horaini and numsala = :old.numsala;

update pase set entradasvendidas = entradasvendidas + :new.numentradas
where idcine = :new.idcine and horaini = :new.horaini and numsala = :new.numsala;
end if;
end;
/