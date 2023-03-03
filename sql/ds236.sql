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
VALUES (8,'우비'); 

UPDATE clothescate1 set name ='신발' where type=2;

-- 외투
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'color mix raglan shirring jumper in black','나일론','두꺼움',3);

INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'ROUND STITCH VARSITY JUMPER IN IVORY','레이온 50 코튼 47','보통',3);

-- 상의
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'COLOR BLOCK LOGO HOODY IN NAVY','코튼 100','보통',0);

-- 신발
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'MATIN KIM LOGO COMFORT FLIP FLOP FOR MEN IN BLACK','EVA','보통',2);

-- 하의
INSERT INTO clothes1 (clno, clname, texture, thickness,type)
VALUES(seq_clothes_no.nextval, 'TWO TUCK CURTAIN TROUSER IN BROWN','폴리에스터 80 레이온 16','보통',1);


