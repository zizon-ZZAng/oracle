
-- WEATHER1 ���̺� ���� ����
-- ��������: �÷����� TEMPHIGH, TEMPROW -> temperature
-- ��������: �������� TEMPHIGH_HOTTER_THEN_TEMPROW X
-- ��������: temperature NUMBER -> FLOAT
-- ��������: ��Ÿ ��ħ : func_SEQ_WEATHER1_CODE_nextval (naxt->next)

-- ��������: 


SELECT * FROM WEATHER1 ;

DROP SEQUENCE SEQ_WEATHER1_CODE;
DROP TABLE WEATHER1 CASCADE CONSTRAINTS;
DROP FUNCTION FUNC_SEQ_WEATHER1_CODE_NAXTVAL;

CREATE TABLE weather1
(
  code        number(10)   NOT NULL,
  regdate     TIMESTAMP    NOT NULL,
  weather     VARCHAR2(50),
  temperature NUMBER(10)  ,
  no          NUMBER(5)    NOT NULL,
  CONSTRAINT PK_weather1 PRIMARY KEY (code)
);

ALTER TABLE recommend1
  ADD CONSTRAINT FK_weather1_TO_recommend1
    FOREIGN KEY (code)
    REFERENCES weather1 (code);




--- ��������
-- �ܷ�Ű
ALTER TABLE weather1
  ADD CONSTRAINT FK_location1_TO_weather1
    FOREIGN KEY (no)
    REFERENCES location1 (no);
-- �ܷ�Ű �����ϱ�
ALTER TABLE weather1 DROP CONSTRAINT FK_location1_TO_weather1;


--- WEATHER1 ������ �Է�

-- ������
CREATE SEQUENCE SEQ_WEATHER1_CODE INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- ����
DROP SEQUENCE SEQ_WEATHER1_CODE;

-- �Լ�
CREATE OR REPLACE FUNCTION func_SEQ_WEATHER1_CODE_nextval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_WEATHER1_CODE.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- ����
DROP FUNCTION func_SEQ_WEATHER1_CODE_nextval;



-- �ϰ��Է�

INSERT ALL 
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '����', 9.8, 30)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '��������', 14.0, 31)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '��������', 14.1, 32)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '�帲', 7.1, 33)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '��', 13.1, 34)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '��', 12.0, 35)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '�� �Ǵ� ��', 13.6, 36)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , 'õ�չ���', 9.8, 37)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '�Ȱ�', 9.2, 38)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , 'Ȳ��', 11.2, 39)
SELECT * FROM DUAL;
-- TEMPERATURE�� '������'���� (����: �α���(���ӽ���)����)
-- weather�� '���糯��'����

-- WEATHER1 �������� TEMPERATURE�� NUMBER -> FLOAT���� �ٲ�
-- �Ҽ��� ����� �ȵ���

-- �Ҽ��� ������ ������ �Ѱ� �ֱ� �õ�
INSERT
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nExtval, CURRENT_DATE+1 , '��������', 10.4, 32);

SELECT * FROM WEATHER1;

ROLLBACK;

COMMIT;


