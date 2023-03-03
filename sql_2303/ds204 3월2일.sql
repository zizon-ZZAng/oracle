SET SERVEROUTPUT ON;

-- Ʈ���� => � ���̺� insert, update, delete�� ����� �� �ڵ����� ȣ��Ǵ� ���ν���
-- member���̺� �����Ͱ� �߰� �Ǹ� memver_bk���̺� �߰�, ����, ����
-- �������̺� �ֹ��ϸ� ���������̺��� ������ ����

--����
CREATE OR REPLACE TRIGGER Ʈ���Ÿ�
    (BEFORE | AFTER) INSERT, DELETE, UPDATE ON ���̺��
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� �����
    
BEGIN

END;
/
---------------------------------------------------------
-- NNN�� ������ ����
CREATE OR REPLACE TRIGGER TRI_NNN_INSERT
    AFTER INSERT ON NNN
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
DBMS_OUTPUT.PUT_LINE('NNN���̺� �����Ͱ� �߰��Ǿ���');

END;
/

SELECT * FROM NNN;

--Ȯ��
INSERT INTO NNN(id, name, pw, age, mdate)
VALUES('C1','�� �̸�', '�� ���', 45, CURRENT_DATE);

COMMIT;

---------------------------------------------------------
--���̺� ����(�ָ�����?)
CREATE TABLE NNN_BACKUP AS SELECT n.* FROM nnn n;

SELECT n.* FROM NNN_BACKUP n;
SELECT n.* FROM NNN n;

--- NNN���̺� ������ ����� NNN_BACKUP���� ������ ����
CREATE OR REPLACE TRIGGER NNN_BACKUP
    AFTER INSERT ON NNN
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
    INSERT INTO nnn_backup(id, name, pw, age, mdate)
    VALUES(:new.id,:new.name,:new.pw,:new.age,CURRENT_DATE);
END;
/
---------------------------------------------------------

---NNN������ ����Ǹ� �޼��� ��
CREATE OR REPLACE TRIGGER tri_nnn_upd
    AFTER UPDATE ON nnn
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
    DBMS_OUTPUT.PUT_LINE('nnn ���̺� �����Ͱ� �����');
    DBMS_OUTPUT.PUT_LINE('�������̸� ' || :old.name);
    DBMS_OUTPUT.PUT_LINE('�������̸� ' || :new.name);
END;
/
--Ȯ��
UPDATE nnn SET name = '�����ϱ�', age =29 
WHERE id='A2';
-- >??? ������Ʈ �޼����� �߰� Ʈ���Ÿ޼��� �ȶ�

---------------------------------------------------------

--- nnn���̺� ������Ʈ �Ǹ� nnn_backup���� ���� ����
CREATE OR REPLACE TRIGGER tri_nnn_upd2
    AFTER UPDATE ON nnn
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
    UPDATE nnn_backup SET id=:new.id, name=:new.name, pw=:new.pw, age=:new.age
        WHERE id=:old.id;
END;
/

--- nnn���� ���̵� �����ϸ� ��������� ���� ���̵� ����
CREATE OR REPLACE TRIGGER tri_nnn_upd3
    AFTER DELETE ON nnn
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
    DELETE FROM nnn_backup WHERE id=:old.id;
END;
/


--- INSERT UPDATE DELETE �ѹ���
CREATE OR REPLACE TRIGGER tri_nnn_UPINDE
    AFTER INSERT OR UPDATE OR DELETE ON nnn
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� ����
BEGIN
    IF INSERTING THEN
      INSERT INTO nnn_backup(id, name, pw, age, mdate)
        VALUES(:new.id,:new.name,:new.pw,:new.age,CURRENT_DATE);
    ELSIF UPDATING THEN
        UPDATE NNN_BACKUP SET id=:new.id, name=:new.name, pw=:new.pw, age=:new.age
        WHERE id=:old.id;
    ELSIF DELETING THEN
         DELETE FROM nnn_backup WHERE id=:old.id;
    END IF;
END;
/


--���� : purchase ���̺� �ֹ��� �߰��Ǹ� item���̺��� �������� �ֹ��Ѹ�ŭ ������Ű�� Ʈ���� ����
-- Ʈ���Ÿ� : tri_purchase_update_item
CREATE OR REPLACE TRIGGER tri_purchase_update_item
    AFTER INSERT ON purchase
    FOR EACH ROW
BEGIN
    UPDATE item 
    SET quantity = quantity - :new.cnt 
    WHERE code=:new.code;
END;
/

SELECT i.* FROM item i;
SELECT p.* FROM purchase p;
SELECT m.* FROM member m;
--�پ�ǰ
INSERT INTO purchase (no, cnt, regdate, code, userid)
    VALUES(seq_purchase_no.NEXTVAL, 1, CURRENT_DATE, 1, lsd);


--���� : purchase�� cnt�ֹ������� ����Ǹ� item quantity�������� �����ϴ� Ʈ���� ����
-- ���� �ֹ� ������ 10�ϰ�� 9�� ����Ǹ� ������ 1������.
-- ���� �ֹ� ������ 10�ϰ�� 11�� ����Ǹ� ������ 1���ҵ�.
CREATE OR REPLACE TRIGGER tri_purchase_cnt_item
    AFTER UPDATE ON purchase
    FOR EACH ROW
BEGIN
    IF :old.cnt > :new.cnt THEN --������ ����
        UPDATE item SET quantity = quantity - (:old.cnt - :new.cnt)  WHERE code=:new.code;
    ELSE -- ������ ���� 
         UPDATE item SET quantity = quantity -(:new.cnt - :old.cnt) WHERE code=:new.code;
    END IF;
END;
/

-- Ʈ���� ��Ȱ��ȭ
ALTER TRIGGER tri_purchase_cnt_item DISABLE;
--���̺� ����(�ָ�����?)
CREATE TABLE purchase_BACKUP AS SELECT p.* FROM purchase p;
-- �ֹ������� �߰� ���� ������ �������� �ǽð����� �����ϴ� Ʈ���� �ۼ�
-- item? purchase? purchase_BACKUP?
-- ��α׿� �÷��ֽ� ���߿� ����
CREATE OR REPLACE TRIGGER tri_purchase_cnt_action
    AFTER INSERT OR UPDATE OR DELETE ON purchase
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
      INSERT INTO purchase (no, cnt, regdate, code, userid)
        VALUES(seq_purchase_no.NEXTVAL, :new.cnt ,CURRENT_DATE ,:new.code ,:new.userid);
        DBMS_OUTPUT.PUT_LINE('INSERT�Ϸ�');
    ELSIF UPDATING THEN
        UPDATE item 
        SET quantity = quantity - :new.cnt 
        WHERE code=:new.code;
        DBMS_OUTPUT.PUT_LINE('UPDATE�Ϸ�');
    ELSIF DELETING THEN
        DELETE FROM purchase WHERE no=:old.no;
        DBMS_OUTPUT.PUT_LINE('DELETE�Ϸ�');
    END IF;
END;
/
--�־
INSERT INTO purchase( no, cnt, regdate, code, userid)
     VALUES(seq_purchase_no.NEXTVAL, 10, CURRENT_DATE, 37, 'a1' );
COMMIT;

SELECT i.* FROM item i;
SELECT p.* FROM purchase p;
SELECT m.* FROM member m;



-----------------------
-- ������
-- �ϰ��߰�(������,�Լ�)

-- �ϰ� ����
SELECT m.* FROM member m;
UPDATE member SET username='qqurudgkrl', userage=77 ??????????
/

-- ���̵� a1a2�� �׸񿡼� ���� �̸��� ���� �ϰ�����
UPDATE member SET 
    username = (CASE 
        WHEN userid='a1' THEN '����'
        WHEN userid='a2' THEN '����'
    END),
    userage = (CASE
        WHEN userid='a1' THEN 28
        WHEN userid='a2' THEN 32
    END)
    WHERE userid IN ('a1','a2');
    /
-- upsert
MERGE INTO member
USING DUAL
    ON (userid = 'a2')
    WHEN MATCHED THEN
        UPDATE SET username='bbbb', userage = 11
    WHEN NOT MATCHED THEN
        INSERT (userid, userpw, username, userage, userphone, usergender, userdate)
            VALUES('a2', '?', '???', 22, '010', 'F', CURRENT_DATE);
COMMIT;



