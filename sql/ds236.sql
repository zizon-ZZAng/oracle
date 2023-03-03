CREATE TABLE clothes1
(
  clno      number(20)   NOT NULL,
  clname    varchar2(50) NULL    ,
  texture   varchar2(50) NULL    ,
  thickness varchar2(10) NULL    ,
  type      number(10)   NOT NULL ,
  PRIMARY KEY (clno)
);

CREATE TABLE clothescate1
(
  type number(10) NOT NULL,
  PRIMARY KEY (type)
);

DROP TABLE clothes1;
DROP TABLE clothescate;


CREATE TABLE clothescate1
(
  type varchar2(20) NOT NULL,
  PRIMARY KEY (type)
);



ALTER TABLE clothes1
  ADD CONSTRAINT FK_clothescate1_TO_clothes1
    FOREIGN KEY (type)
    REFERENCES clothescate1 (type);

ALTER TABLE clothescate1 ADD name VARCHAR2(20);
COMMIT;
    
CREATE SEQUENCE seq_clothes_no
INCREMENT BY 1
START WITH 10001 NOMAXVALUE NOCACHE;

COMMIT;

SELECT * FROM clothescate1;
SELECT * FROM clothes1 ORDER BY type;
select * from location1;


INSERT INTO clothescate1 (type,name) 
VALUES (8,'���'); 

UPDATE clothescate1 set name ='�Ź�' where type=2;

-- ����
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'color mix raglan shirring jumper in black','���Ϸ�','�β���',3);

INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'ROUND STITCH VARSITY JUMPER IN IVORY','���̿� 50 ��ư 47','����',3);

-- ����
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'COLOR BLOCK LOGO HOODY IN NAVY','��ư 100','����',0);

-- �Ź�
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'MATIN KIM LOGO COMFORT FLIP FLOP FOR MEN IN BLACK','EVA','����',2);

-- ����
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'TWO TUCK CURTAIN TROUSER IN BROWN','���������� 80 ���̿� 16','����',1);


