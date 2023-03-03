-- �ֿܼ� ��� ȯ�� ����
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('hello world' || '!!');  -- System.out.println("hello world" + "!!");
END;
/   -- 


-- ��������
DECLARE
    t_str VARCHAR2(20) := 'aaa';    -- String t_str = "aaa"
    t_num NUMBER(4) := 1234;        -- int t_num = 1234
BEGIN
    DBMS_OUTPUT.PUT_LINE(t_str || ',' || t_num); --Sysout("aaa"+","+1234);
END;
/


-- ���ǹ�
DECLARE
    t_score NUMBER(3) := 97;
    t_grade VARCHAR2(2);
BEGIN
    IF t_score >=90 AND t_score<100 THEN    -- if(t_score>=90 && t_score<100)
        t_grade := 'A';
    ELSIF t_score >=80 THEN                 -- else if()
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
    t_num NUMBER := 3;
BEGIN
    FOR i IN 1..9 LOOP
        DBMS_OUTPUT.PUT_LINE( t_num || '*' || i || '=' || t_num*i );
    END LOOP;
END;
/


-- Ȧ�� ¦�� �ݺ���
DECLARE
    t_num NUMBER := 10;
BEGIN
    FOR i IN 1..t_num LOOP
        IF MOD(i,2) = 0 THEN
            DBMS_OUTPUT.PUT_LINE( i || '¦��');
        ELSE
            DBMS_OUTPUT.PUT_LINE( i || 'Ȧ��');
        END IF;
    END LOOP;
END;
/


-- ����Ŭ �����Լ� ���� ���
SELECT MOD(15,2) FROM DUAL;     -- MOD ������
SELECT CURRENT_DATE FROM DUAL;  -- CURRENT_DATE ���糯¥

SELECT m.* FROM member m;

-- 20�� ������ �߰�
DECLARE
    t_num NUMBER(2) := 20;
BEGIN
    FOR i IN 1..t_num LOOP
        INSERT INTO member( userid, userpw, username, userage, userphone, usergender, userdate )
        VALUES('a' || i, 'pw', 'name', 11+i, '010', 'M', CURRENT_DATE);
    END LOOP;
    COMMIT;
    
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;
/


-- ��ȸ�ϱ�
DECLARE
BEGIN
    FOR tmp IN (SELECT m.* FROM member m ORDER BY userid ASC) LOOP
        DBMS_OUTPUT.PUT_LINE(tmp.userid || tmp.username);
    END LOOP;
END;
/


-- cursor�� �̿��� ��ȸ
DECLARE
    CURSOR cur IS SELECT m.* FROM member m ORDER BY userid ASC;
BEGIN
    FOR mem IN cur() LOOP
        DBMS_OUTPUT.PUT_LINE(mem.userid || ' ' || mem.username);
    END LOOP;
END;
/


-- �Լ������
-- public String funcToCharToday() { }
CREATE OR REPLACE FUNCTION func_tochar_today RETURN VARCHAR2
IS
    t_date VARCHAR2(30);
BEGIN
    SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') INTO t_date FROM DUAL;
    RETURN t_date;
EXCEPTION WHEN OTHERS THEN
    RETURN '';
END;
/

SELECT func_tochar_today FROM DUAL;


-- seq_item_code ������ ���� ���� �������� �Լ�
-- public String funcToCharToday() { }
CREATE OR REPLACE FUNCTION func_seq_item_code_nextval RETURN NUMBER
IS
BEGIN
    RETURN seq_item_code.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


SELECT i.* FROM item i;

-- �ϰ��߰� : �������� ����ϸ� ���� ���ڰ� �ԷµǾ ������.
INSERT ALL
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'ǰ��',123,345,'����',CURRENT_DATE)
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'ǰ��',123,345,'����',CURRENT_DATE)
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'ǰ��',123,345,'����',CURRENT_DATE)
SELECT * FROM DUAL;


-- ������ ��� ���ϴ� ��쿡�� �ϰ� �߰� ����
INSERT ALL
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b1', 'pw1', 'bname', 24, '011', 'F', CURRENT_DATE)
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b2', 'pw2', 'bname', 24, '011', 'F', CURRENT_DATE)
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b3', 'pw3', 'bname', 24, '011', 'F', CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;


SELECT P.* FROM purchase p;

-- EXTRACT(�Ķ����1) CURRENT_TIMESTAMP
SELECT CURRENT_TIMESTAMP FROM DUAL;
SELECT EXTRACT(MINUTE FROM CURRENT_TIMESTAMP) FROM DUAL;
SELECT EXTRACT(HOUR FROM CURRENT_TIMESTAMP) FROM DUAL;
SELECT EXTRACT(DAY FROM CURRENT_TIMESTAMP) FROM DUAL;


-- �ð��뺰 �Ǹż����� ��ȯ�ϴ� �Լ�(�������� in_hour �Ķ����)
CREATE OR REPLACE FUNCTION func_purchase_group_hour(in_hour NUMBER) RETURN NUMBER
IS
    tmp_total NUMBER := 0;
BEGIN
    SELECT SUM(p.cnt) INTO tmp_total FROM purchase p WHERE EXTRACT(HOUR FROM p.regdate) = in_hour
    GROUP BY EXTRACT(HOUR FROM p.regdate);
    RETURN tmp_total;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


-- �Լ� �׽�Ʈ
SELECT func_purchase_group_hour(14) FROM DUAL;


-- item ���̺��� �������� ��ȯ�ϴ� �Լ�(func_item_group_quantity)
CREATE OR REPLACE FUNCTION func_item_group_quantity (in_code NUMBER) RETURN NUMBER
IS
    tmp_quan NUMBER :=0;
BEGIN
    SELECT i.quantity INTO tmp_quan FROM item i WHERE i.code = in_code;
    -- GROUP BY i.code;
    RETURN tmp_quan;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/

-- �Լ� �׽�Ʈ ��ǰ��ȣ�� 1�� �������� ��µ�.
SELECT func_item_group_quantity(3) FROM DUAL;

--=============================================================================

-- ȸ������ ���̺� ����
CREATE TABLE membertest(
    username VARCHAR2(30),
    userpassword VARCHAR2(20),
    userid NUMBER(10),
    userage NUMBER(10),
    userphone VARCHAR2(20),
    userdate 
);

SELECT m.* FROM membertest m;
COMMIT;
rollback;

-- ȸ����� (PL/SQL�� �̿��� ȸ�� 5�� ���)
INSERT INTO membertest(username, userpassword, userid, userage, userphone, userdate)
VALUES('�̸�5','��й�ȣ5',, 15,'010-0000-0005',CURRENT_DATE);



-- ȸ������
UPDATE membertest
SET userid = '���̵�5'
WHERE username = '�̸�5';


-- ȸ��Ż��(Update�� �̿��� ������ ����)
UPDATE membertest
SET username = NULL, userpassword = NULL, userage=null, userphone=NULL, userdate=NULL
WHERE username = '�̸�4';


-- ȸ�� �̸��� �˻�� ���Ե� �׸� ��ȸ
SELECT *
FROM membertest
WHERE username LIKE '%' || '��' || '%';


-- ��Ͻð����� ���� ��ġ�ϴ� �׸� ȸ�� ��ȸ
SELECT *
FROM membertest
WHERE TO_CHAR(userdate,'MI') = 9;


-- ȸ�� ���̵� 3�ڸ������� ǥ���Ͽ� ��ȸ
SELECT SUBSTR(userid,1,3) AS userid3
FROM membertest;


-- ���ڸ� �Է¹����� 3�ڸ��� ��ȯ�ϴ� �Լ� ���� (�Լ��� : FUNC_STR, �����Լ� SUBSTR ���)
-- FUNC_STR�� �̿��Ͽ� ȸ�� ���̵� 3�ڸ������� ǥ���Ͽ� ��ȸ ex) a12345 => a12
CREATE OR REPLACE FUNCTION FUNC_STR (in_userid VARCHAR2) RETURN VARCHAR2
IS
    tmp_userid VARCHAR2(20) := NULL;
BEGIN
    SELECT SUBSTR(in_userid,1,3) INTO tmp_userid FROM DUAL;
    RETURN tmp_userid;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


SELECT FUNC_STR(userid) FROM membertest;


-- ������ ���� ===============================================================
CREATE OR REPLACE FUNCTION func_str( in_str VARCHAR2 ) RETURN VARCHAR2
IS
    tmp_str VARCHAR2(6);
BEGIN
    SELECT SUBSTR( in_str, 1, 3) INTO tmp_str FROM DUAL;
    RETURN tmp_str;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

SELECT func_str('12345')  FROM DUAL;
SELECT m.*, func_str(m.userid) FROM member m;
--===========================================================================




-- �˻���� �������� �����ϸ� �˻�� �ش��ϴ� ȸ���� ��ȸ(�������� 5����)
CREATE OR REPLACE FUNCTION FUNC_STR5 (in_search VARCHAR2, in_page NUMBER) RETURN VARCHAR2
IS
    tmp_userid VARCHAR2(20) := NULL;
BEGIN
    SELECT userid INTO tmp_userid FROM membertest WHERE username LIKE '%' || in_search || '%';
    RETURN tmp_userid;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/

SELECT FUNC_STR5('��',5) FROM membertest;


-- ���� =========================== �Լ��δ� ���� �� ����!
SELECT m.*, ROW_NUMBER() OVER (ORDER BY userid ASC) ROWN  FROM membertest m WHERE m.userid LIKE '%' || '��' || '%' ;

SELECT * FROM (
SELECT m.*, ROW_NUMBER() OVER (ORDER BY userid ASC) ROWN  FROM membertest m WHERE m.userid LIKE '%' || '��' || '%'
) WHERE rown BETWEEN 1 AND 5;



