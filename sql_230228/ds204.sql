SET SERVEROUTPUT ON;

-- ���ν��� : ���ֻ���ϴ� SQL�� �ۼ� -> �ʿ�� ȣ�� -> ��ȯ���� ����.
-- ����) ȸ�����̵� ������ UPDATE ������ INSERT ����.
-- SELECT(���̵�Ȯ��) -> INSERT OR UPDATE(�� �� �ϳ� ����)

-- ���ν��� ����
CREATE OR REPLACE PROCEDURE ���ν�����(
�Ķ���� IN, OUT ���
)
IS
BEGIN

END;
/

-- ���ڸ� ������ ����� �������ִ� ���ν���
CREATE OR REPLACE PROCEDURE proc_if_exam(
    in_num IN NUMBER --���޵Ǵ� �Ķ���� :������ INNUM�� ���
)
IS
    tmp_grade VARCHAR(2); -- ��޺����� ���ں���
BEGIN
    IF in_num >=90 THEN
        tmp_grade := 'A';
    ELSIF in_num >=80 THEN
        tmp_grade := 'B';
    ELSE 
        tmp_grade := 'C';
    END IF;
    DBMS_OUTPUT.PUT_LINE('����� ' || tmp_grade || '�Դϴ�.');
END;
/
-- ���ν��� ����
EXEC proc_if_exam(90);
--
BEGIN
    --������ ���ν��� ȣ��
    EXEC proc_if_exam(90);
END;
------------------------


--ȸ������ ���ν���(SQL�� �� ���ϱ� �� �� ����)
CREATE OR REPLACE PROCEDURE proc_nnn_insert(
    in_id IN NNN.ID%TYPE,
    in_name IN NNN.NAME%TYPE,
    in_pw IN NNN.PW%TYPE,
    in_age IN NNN.AGE%TYPE,
    out_ret OUT NUMBER -- �Ϸᰡ �Ǹ� ������ ���и� Ȯ��. NUMBER. 0�Ǵ� 1�� Ȯ�ΰ���
)
IS
BEGIN
    INSERT INTO nnn (id, name, pw, age, mdate)
    VALUES(in_id, in_name, in_pw, in_age, CURRENT_DATE);
    COMMIT;
    out_ret := 1;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    out_ret := 0;
END;
/

-- ��ȯ���� ������ ���ν��� �׽�Ʈ
DECLARE 
    out_ret NUMBER(1) := -1; -- ���ν��� �����ϸ� 1�� ���´�. �ֳ��ϸ� ���ν����� ���� �����߱� ������
BEGIN
    --������ ���ν��� ȣ��
    proc_nnn_insert('D1','SBC1','PWPW1',35,out_ret);
    DBMS_OUTPUT.PUT_LINE('������� ' || out_ret);
END;
/
COMMIT;
SELECT * FROM nnn;


-----------------------------
-- SELECT COUNT(*) FROM ���̺�
--����) ȸ�������� �����ϸ� ȸ�����̵� ������ ��� �̸� ���� ����ó ������ ������Ʈ
--�������� ������ �߰��ϱ�
--���ν��� ��Ī proc_nnn_upsert

CREATE OR REPLACE PROCEDURE proc_nnn_upsert(
    in_id IN NNN.ID%TYPE,
    in_name IN NNN.NAME%TYPE,
    in_pw IN NNN.PW%TYPE,
    in_age IN NNN.AGE%TYPE,
    out_ret OUT NUMBER 
)
IS
 tmp_chk NUMBER(1) := 0; --�������� Ȯ�ο� ����
BEGIN
        SELECT COUNT(*) INTO tmp_chk FROM nnn n WHERE n.id = in_id;
    IF tmp_chk = 1 THEN
        UPDATE nnn 
        SET name = in_name, age = in_age, pw =  WHERE id = in_id;
    ELSE 
        INSERT INTO nnn (id, name, pw, age, mdate)
        VALUES(in_id, in_name, in_pw, in_age, CURRENT_DATE);
    END IF;
    COMMIT;
    out_ret := 1;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    out_ret := 0;
END;
/
----------------------------------------
--���ν����� NNN��ȸ
CREATE OR REPLACE PROCEDURE proc_nnn_select(
    in_name IN VARCHAR2,
    out_cursor OUT SYS_REFCURSOR
    )
IS
BEGIN
    OPEN out_cursor FOR
        SELECT n.* FROM nnn n WHERE n.name = in_name;
END;
/

--proc_nnn_select ���ν��� �׽�Ʈ
-- out_cursor: List<NNN>
-- out_cursor�� �ڹ�Ÿ������ �ٲٱ� �����
SELECT * FROM NNN;

DECLARE
    in_name VARCHAR2(20) := 'name2';
    out_cursor SYS_REFCURSOR;
    -- nnnŸ�� ����(cursorŸ���� ����Ϸ��� ��ü�� �ʿ�
    TYPE nnntype IS RECORD ( 
    ID VARCHAR2(20),
    NAME VARCHAR2(20),
    PW VARCHAR2(200),
    AGE NUMBER(3),
    mdate TIMESTAMP
    );
    -- nnn Ÿ������ �������� --LOMBOK
    nnnobj nnntype;
BEGIN
    --���ν���ȣ��
    proc_nnn_select(in_name, out_cursor);    
    LOOP
        FETCH out_cursor INTO nnnobj;  -- for(nnntype nnnobj : out_cursor)
        EXIT WHEN out_cursor%NOTFOUND; -- ���������� ������
        DBMS_OUTPUT.PUT_LINE(nnnobj.ID|| ' ' || nnnobj.NAME);
    END LOOP;
    CLOSE out_cursor;
END;
/

-------------------------
SELECT n.* FROM nnn n;

-- �������� ���� ���

INSERT ALL
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A14','name10','pw10',17,CURRENT_DATE)
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A15','name11','pw11',17,CURRENT_DATE)
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A16','name12','pw12',17,CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;

SELECT r.* FROM restaurant r;
-- �ϰ������� 5�� �߰��ϱ�

INSERT ALL
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-8','name10','�߱�','etw17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-9','name11','�߱�','fef17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-10','name12','����','dfs17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-11','name13','����','dts17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-12','name14','����','dws17')
SELECT * FROM DUAL;
COMMIT;
------------------------
------------------------


-- �������� �ִ°��

SELECT i.* FROM item i;

-- �������� ������ �Լ����x

SELECT seq_item_no.NEXTVAL FROM DUAL;
INSERT INTO item(code, name, price, quantity, content, regdate)
SELECT seq_item_no.NEXTVAL code, T1.* FROM(  --T1�� ����?
SELECT  '�̸�1' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
--UNION ALL : ���η� ���̴°�
UNION ALL
SELECT  '�̸�2' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
UNION ALL
SELECT  '�̸�3' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
) T1;
COMMIT;


--- ���� �Ѱ� nnnselect ���� �ڹٿ��� ������


