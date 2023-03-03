
SET SERVEROUTPUT ON;

--- LOCATION1 ���̺�
CREATE TABLE location1
(
  no   NUMBER(5)    NOT NULL,
  name VARCHAR2(20),
  CONSTRAINT PK_location1 PRIMARY KEY (no)
);
DROP TABLE weather1 CASCADE CONSTRAINTS;

--- LOCATION1 ������ �߰�

-- ������ ����
CREATE SEQUENCE SEQ_LOCATION1_NO INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- ����
DROP SEQUENCE SEQ_LOCATION1_NO

-- �Լ� ����
CREATE OR REPLACE FUNCTION func_seq_location1_no_naxtval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_LOCATION1_NO.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- ����
DROP FUNCTION func_seq_location1_no_naxtval;

-- Į�������� �� �۴ٰ� �ؼ� Į�������� ����
ALTER TABLE location1 MODIFY(name varchar2(100));

-- ���� �ϰ� �߰� ����
INSERT ALL
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '����Ư����')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '�λ걤����')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '�뱸������')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��õ������')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '���ֱ�����')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '����������')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��걤����')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '����Ư����ġ��')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��⵵')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '������')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��û�ϵ�')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��û����')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '����ϵ�')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '���󳲵�')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '���ϵ�')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '��󳲵�')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '����Ư����ġ��')
    SELECT * FROM DUAL;
 
-- ������ ����  
DELETE FROM location1
WHERE no = 4;


ROLLBACK;

COMMIT;

SELECT * FROM location1;

--------------------------------------------------

-- WEATHER1 ���̺�(�������� ��������)
DROP TABLE weather1 CASCADE CONSTRAINTS;
CREATE TABLE weather1
(
  code     number(10)   NOT NULL,
  regdate  TIMESTAMP    NOT NULL,
  weather  VARCHAR2(50),
  TEMPHIGH NUMBER(2)   ,
  TEMPROW  NUMBER(2)   ,
  no       NUMBER(5)    NOT NULL,
  CONSTRAINT PK_weather1 PRIMARY KEY (code)
);

--- ��������
-- �ܷ�Ű
ALTER TABLE weather1
  ADD CONSTRAINT FK_location1_TO_weather1
    FOREIGN KEY (no)
    REFERENCES location1 (no);
-- weather1 :��������� �ְ��º��� ū ���� ���� �� ����
ALTER TABLE weather1 
ADD CONSTRAINT TEMPHIGH_HOTTER_THEN_TEMPROW 
CHECK(TEMPROW<=TEMPHIGH);

ALTER TABLE weather1 DROP CONSTRAINT FK_location1_TO_weather1;
ALTER TABLE weather1 DROP CONSTRAINT TEMPHIGH_HOTTER_THEN_TEMPROW;



--- WEATHER1 ������ �Է�

-- ������
CREATE SEQUENCE SEQ_WEATHER1_CODE INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- ����
DROP SEQUENCE SEQ_WEATHER1_CODE;

-- �Լ�
CREATE OR REPLACE FUNCTION func_SEQ_WEATHER1_CODE_naxtval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_WEATHER1_CODE.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- ����
DROP FUNCTION func_SEQ_WEATHER1_CODE_naxtval;

-- �ϰ��Է�
-- ������ ��� �������� :https://www.weather.go.kr/w/weather/forecast/mid-term.do

INSERT ALL 
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '����', 8, 25, 30)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '��������', 2, 14, 31)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '��������', 10.4, 32)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '�帲', 12.0, 33)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '��', 9.8, 34)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '��(��������)', 11.3, 35)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '�� �Ǵ� ��(��������)', 11.6, 36)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , 'õ�չ���(��Ͼȵ�)', 14.1, 37)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '�Ȱ�(�泲â��)', 13.5, 38)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , 'Ȳ��', 11.2, 39)
SELECT * FROM DUAL;

DELETE FROM WEATHER1
WHERE TEMPROW = 3;

-- ORA-00001: ���Ἲ ���� ����(DS236.PK_WEATHER1)�� ����˴ϴ�
-- DK .. tL. .

-- WEATHER1�� �ڵ� �߰�
-- �ٽ� ���� ���� ��������
--- ������ ���� SEQ_WEATHER1_CODE
-- �Լ� ���� func_SEQ_WEATHER1_CODE_naxtval 
-- �ϰ� ���� code, func_SEQ_WEATHER1_CODE_naxtval �߰�

--10�� �� ��(��) ���ԵǾ����ϴ�.
--Ŀ�� �Ϸ�.


SELECT * FROM WEATHER1;

ROLLBACK;

COMMIT;
