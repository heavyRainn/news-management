CREATE SEQUENCE  ATR_SEQ_ID  MINVALUE 1 MAXVALUE 1111111111111 INCREMENT BY 1 START WITH 321 CACHE 20 NOORDER  NOCYCLE ;

create or replace trigger ATR_INC_ID
before insert on AUTHORS
for each row
begin
 IF :new.ATR_ID is null THEN
   select ATR_SEQ_ID.nextval into :new.ATR_ID from dual;
 end if;
end;

CREATE SEQUENCE  TAG_SEQ_ID  MINVALUE 1 MAXVALUE 111111111111 INCREMENT BY 1 START WITH 221 CACHE 20 NOORDER  NOCYCLE ;

create or replace TRIGGER TAG_INC_ID 
BEFORE INSERT ON TAGS
FOR EACH ROW
BEGIN
  IF :new.TG_ID is null THEN
    select TAG_SEQ_ID.nextval into :new.TG_ID from dual;
  END IF;
END;


CREATE SEQUENCE  COM_SEQ_ID  MINVALUE 1 MAXVALUE 11111111111111 INCREMENT BY 1 START WITH 421 CACHE 20 NOORDER  NOCYCLE ;


create or replace TRIGGER COM_INC_ID 
BEFORE INSERT ON COMMENTS 
FOR EACH ROW
BEGIN
IF :new.COM_ID is null THEN
  SELECT COM_SEQ_ID.nextval into :new.COM_ID from dual;
  END IF;
END;


CREATE SEQUENCE  N_SEQ_ID  MINVALUE 1 MAXVALUE 111111111111111 INCREMENT BY 1 START WITH 521 CACHE 20 NOORDER  NOCYCLE ;

create or replace TRIGGER N_INC_ID 
BEFORE INSERT ON NEWS 
FOR EACH ROW
BEGIN
  IF :new.N_ID is null THEN
    SELECT N_SEQ_ID.nextval into :new.N_ID from dual;
  END IF;
END;


CREATE SEQUENCE  UR_SEQ_ID  MINVALUE 1 MAXVALUE 111111111111111 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE ;

create or replace TRIGGER UR_INC_ID 
BEFORE INSERT ON USERS 
for each row
BEGIN
  IF :new.UR_ID is null THEN
    select UR_SEQ_ID.nextval into :new.UR_ID from dual;
  END IF;
END;