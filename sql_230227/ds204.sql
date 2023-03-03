






-- �ֿܼ� ���ȯ�� ����
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD'||'!!'); --SYSO (DJwJRN+DJwJRN)
END;
/ --�����Ŷ� �ؿ��� ���౸�м� (���ϸ� ����µ�)

--��������
DECLARE
    t_str VARCHAR2(20):= 'AAA';
    t_num NUMBER(4):= 1234;
BEGIN
    DBMS_OUTPUT.PUT_LINE(t_str || ',' || t_num); 
END;
/

--���ǹ�
DECLARE
    t_score NUMBER(3) := 67;
    t_grade VARCHAR(2);
BEGIN
    IF t_score >=90 AND t_score <=98 THEN -- if(t_score >= 90 && t_score <=98)
        t_grade := 'A'; -- else if () 
    ELSIF t_score >=80 THEN 
        t_grade := 'B';
    ELSIF t_score >=70 THEN
        t_grade := 'C';
    ELSE 
        t_grade := 'D';
    END IF;
    DBMS_OUTPUT.PUT_LINE(t_grade || '���');
END;
/

-- �ݺ���
DECLARE
    t_num NUMBER :=3;
BEGIN
    FOR i IN 1..9 LOOP  -- for(int i=1, i<=9; i++){
        DBMS_OUTPUT.PUT_LINE(t_num || '*' || i || '=' || (t_num*i));
    END LOOP;
END;
/
--PL/SQL ���ἱ�� ('/' �ڿ� �ּ��� ������ ������)


-- Ȧ�� ¦�� �ݺ���
DECLARE
    t_num NUMBER :=10;
BEGIN
    FOR i IN 1..t_num LOOP  -- for(int i=1, i<=9; i++){
        IF MOD(i, 2) = 0 THEN -- if ( i%2 ==0 ) {  //MOD ����Ŭ �����Լ� ���������ϱ�
            DBMS_OUTPUT.PUT_LINE(i || '¦��'); 
        ELSE
            DBMS_OUTPUT.PUT_LINE(i || 'Ȧ��');
        END IF; --IF ������ ����
    END LOOP; --FOR �ݺ��� ����
END;
/

--����Ŭ �����Լ� ������
SELECT MOD(15,2) FROM DUAL; -- MOD������
SELECT CURRENT_DATE FROM DUAL; --CURRENT_DATE���糯¥
-- �̰� '/' �ʿ����



----------------------------



SELECT m.* FROM member m;

DECLARE
    t_mum NUMBER (2) := 25;  
BEGIN
    FOR i IN 1..t_mum LOOP
        INSERT INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('a' || i, 'pw', 'name', 11+i, '010', CURRENT_DATE, 'M');
    END LOOP;
    COMMIT;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;
/
-- �����̳� �߰����� EXCEPTION WHEN OTHERS THEN ROLLBACK; ������ ��ȸ���� ���� ���ʿ� ����

DECLARE
BEGIN
    FOR tmp IN(SELECT m.* FROM member m ORDER BY userid  ASC) LOOP 
        DBMS_OUTPUT.PUT_LINE(tmp.userid || tmp.username);
    END LOOP;
END;
/

-- ��ȸ�ϱ� : cursor�� �̿�
DECLARE
    CURSOR cur IS SELECT m.* FROM member m ORDER BY userid ASC;
BEGIN
    FOR mem IN cur() LOOP
        DBMS_OUTPUT.PUT_LINE(mem.userid || mem.username);
    END LOOP;
END;
/

---------------------------------------

--�Լ������
--public string func() {
CREATE OR REPLACE FUNCTION func_tochar_today RETURN VARCHAR2
IS
    t_date VARCHAR2(30); 
BEGIN
    -- �����Լ� => CURRENT_DATE, TO_CHAR(���泯¥,����)
    SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') INTO t_date FROM DUAL;
    RETURN t_date;
EXCEPTION WHEN OTHERS THEN
    RETURN '';
END;
/

SELECT func_tochar_today FROM DUAL;


--�ϰ��߰�
SELECT m.* FROM member m;

INSERT ALL
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B', 'pw', 'name', 11, '010--', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B1', 'pw1', 'name1', 11, '010---', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B2', 'pw2', 'name2', 11, '010----', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B3', 'pw3', 'name3', 12, '010-----', CURRENT_DATE, 'F')
SELECT * FROM DUAL;
COMMIT;

-- ������(seq_item_code)�� ������ڸ� �������� �Լ��� ������
CREATE OR REPLACE FUNCTION func_seq_item_code_naxtval RETURN NUMBER
IS
BEGIN
    RETURN seq_item_no.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--�ð��뺰 �Ǹż����� ��ȯȭ�� �Լ��� ������
CREATE OR REPLACE FUNCTION func_purchase_group_hour(in_hour NUMBER) RETURN NUMBER
IS
    tmp_total NUMBER := 0;
BEGIN
    SELECT SUM(p.cnt) INTO tmp_total FROM purchase p WHERE EXTRACT(HOUR FROM p.regdate) = in_hour
    GROUP BY EXTRACT(HOUR FROM p.regdate) ;
    RETURN tmp_total;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

-- �Լ� �׽�Ʈ

SELECT func_purchase_group_hour(14) FROM DUAL;





--�ϰ��߰�
INSERT ALL
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, 'ǰ��', 123, 456, '����------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, 'ǰ��', 123, 456, '����------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, 'ǰ��', 123, 456, '����------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, 'ǰ��', 123, 456, '����------', CURRENT_DATE )
SELECT * FROM DUAL;


SELECT m.* FROM member m;
SELECT i.* FROM item i;
SELECT i.* FROM item i;
SELECT p.* FROM purchase p;

-- EXTRACT(�Ķ����1) CURRNET_TIMESTEMP
SELECT EXTRACT(MINUTE FROM CURRENT_TIMESTAMP)FROM DUAL;

-- item ���̺��� ��ǰ��ȣ�� �������� ��ȯ�ϴ� �Լ�(func_item_group_quantity)
CREATE OR REPLACE FUNCTION func_item_group_quantity(in_code NUMBER) RETURN NUMBER
IS
    tmp_quantity NUMBER:= 0;
BEGIN
    SELECT quantity INTO tmp_quantity FROM item WHERE item.code = in_code;
    RETURN tmp_quantity;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--�Լ� �׽�Ʈ ��ǰ��ȣ�� 1�� ������ ��µ�


commit;

------------

--ȸ������ ���̺� ����
CREATE TABLE nnn(
id VARCHAR2(20) CONSTRAINT PK_nnn PRIMARY KEY , 
name VARCHAR2 (20), 
pw VARCHAR2(200), 
age NUMBER(3), 
mdate TIMESTAMP
);
--DROP TABLE nnn; -- nnn����� ���� �ø���
DROP TABLE nnn;
--ȸ����� (PL/SQL�� �̿��� ȸ�� 5�� ���)
INSERT ALL
INTO nnn( id, pw, name, age, mdate)
        VALUES('B432', 'pw4', 'name4', 13, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B4D5', 'pw5', 'name5', 24, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B23G', 'pw6', 'name6', 25, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B895D', 'pw7', 'name7', 18, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B3', 'pw0', 'name8', 21, CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;
--PL/SQL�� �̿��� ȸ�� 5�� ���(DKDKKDKDKKK)(������ DID)
DECLARE
BEGIN
    FOR i IN 1..5 LOOP
        INSERT INTO nnn( id, name, pw, age, userdate )
        VALUES( 'B' || i  ,'name'|| i, 'pw' || i, 11+i , CURRENT_DATE);   
    END LOOP;
    COMMIT;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;    
END;
/
COMMIT;

SELECT * FROM nnn;

--ȸ������
UPDATE nnn
set name = 'A', age= 15
WHERE id = 'B';

--ȸ��Ż��(UPDATE�� �̿��� ������ ����)
UPDATE nnn
set name = '', pw = '', age= 0, mdate=''
WHERE id = 'B4';

--ȸ�� 1�� ��ȸ (��ȣ�� �������ڴ� ��ȸ�� ����)
SELECT n.id, n.age, n.name FROM nnn n WHERE n.id = 'B1';

--ȸ���̸��� �˻�� ���Ե� �׸���ȸ
SELECT n.* FROM nnn n WHERE n.name like '%'||'1'||'%';
COMMIT;

--��Ͻð��׸񿡼� ���� ��ġ�ϴ� ȸ����ȸ
SELECT n.* FROM nnn n WHERE TO_CHAR(n.mdate,'MI') = 15;

--���ڸ� �Է¹����� 3�ڸ��� ��ȯ�ϴ� �Լ�����( �Լ���: FUNC_STR, �����Լ� : SUBSTR)
CREATE OR REPLACE FUNCTION FUNC_STR(in_id VARCHAR2) RETURN VARCHAR2
IS
    tmp_STR VARCHAR2(20) := '';
BEGIN
   SELECT SUBSTR(in_id, 1, 3) INTO tmp_STR FROM nnn WHERE nnn.id = in_id ;
   RETURN tmp_STR;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--FUNC_STR�� �̿��Ͽ� ȸ�����̵� 3�ڸ������� ǥ���Ͽ� ��ȸ EX) A1234 => A12
SELECT nnn.name, nnn.id, FUNC_STR(nnn.id) FROM nnn;
SELECT * FROM nnn;

--�˻���� �������� �����ϸ� �˻�� �ش��ϴ� ȸ���� ��ȸ(�������� 5��)
SELECT * FROM (
    SELECT ROWNUM AS RNUM, n.* FROM nnn n WHERE n.name LIKE '%n%'
)WHERE RNUM BETWEEN 10 AND 15;
--������did
SELECT * FROM (
    SELECT n.*, ROW_NUMBER() OVER (ORDER BY id ASC) ROWN FROM nnn n WHERE n.id LIKE '%' || 'B' || '%'
) WHERE ROWN BETWEEN 4 AND 10;


