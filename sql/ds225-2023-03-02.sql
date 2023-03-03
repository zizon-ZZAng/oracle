-- Ʈ���� => � ���̺� insert, update, delete �� ����� �� �ڵ����� ȣ��Ǵ� ���ν���
-- member ���̺� �����Ͱ� �߰��Ǹ� member_bk���̺� �߰�, ����, ����

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER Ʈ���Ÿ�
    (BEFORE | AFTER) INSERT, DELETE, UPDATE ON ���̺��
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� �����.
BEGIN

END;
/

-- Ʈ���� ��Ȱ��ȭ(DISABLE) Ȱ��ȭ(ENABLE)
ALTER TRIGGER Ʈ���Ÿ� DISABLE;


CREATE OR REPLACE TRIGGER tri_member_insert
    AFTER INSERT ON member
    FOR EACH ROW -- ���� �����Ͱ� ��ȯ�Ǹ� �����.
BEGIN
    DBMS_OUTPUT.PUT_LINE('member ���̺� �����Ͱ� �߰��Ǿ���');
END;
/

CREATE TABLE member_backup AS SELECT m.* FROM member m;

SELECT m.* FROM member_backup m;
SELECT m.* FROM member m;


--<<INSERT Ʈ����>>==================================================================
CREATE OR REPLACE TRIGER tri_member_backup_insert
    AFTER INSERT ON member
    FOR EACH ROW
BEGIN
    INSERT INTO member_backup(userid, userpw, username, userage, userphone, usergender, userdate)
    VALUES(:new.userid, :new.userpw, :new.username, :new.userage, :new.userphone, :new.usergender, CURRENT_DATE)
    -- commit�� ����� �� ����, �ڵ����� commit��.
END;
/


INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
VALUES('a2001','��ȣ','�̸�',12,'010','M',CURRENT_DATE);
COMMIT;


--<<UPDATE Ʈ����>>====================================================
CREATE OR REPLACE TRIGGER tri_member_update
    AFTER UPDATE ON member
    FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('member ���̺� �����Ͱ� �����');
    DBMS_OUTPUT.PUT_LINE('������ �̸�=>' || :old.username);
    DBMS_OUTPUT.PUT_LINE('������ �̸�=>' || :new.username);
    
END;
/

UPDATE member SET username='�����ϱ�', userage=28 WHERE userid='d234';
COMMIT;


CREATE OR REPLACE TRIGGER tri_member_backup_update
    AFTER UPDATE ON member
    FOR EACH ROW
BEGIN
   UPDATE member_backup SET username=:new.username, userage =:new.userage
   WHERE userid=:old.userid;
END;
/

UPDATE member SET username='�����ϱ�2', userage=28 WHERE userid='d234';
COMMIT;


--<<DELETE Ʈ����>>============================================================
CREATE OR REPLACE TRIGGER tri_member_backup_delete
    AFTER DELETE ON member
    FOR EACH ROW
BEGIN
    DELETE FROM member_backup WHERE userid=:old.userid;
END;
/


DELETE FROM member WHERE userid='d234';


-- insert, update, delete �Ѳ����� ==========================================
CREATE OR REPLACE TRIGGER tri_member_backup_action
    AFTER INSERT OR UPDATE OR DELETE ON member
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO member_backup(userid, userpw, username, userage, userphone, usergender, userdate)
            VALUES(:new.userid, :new.userpw, :new.username, :new.userage, 
                :new.userphone, :new.usergender, CURRENT_DATE);
    ELSIF UPDATING THEN
        UPDATE member_backup SET username=:new.username, userage =:new.userage
        WHERE userid=:old.userid;
    ELSIF DELETING THEN
        DELETE FROM member_backup WHERE userid=:old.userid;
    END IF;
END;
/


--===============================================================================
-- ����) purchase ���̺� �ֹ��� �߰��Ǹ� item���̺��� ��� ������ �ֹ��Ѹ�ŭ ������Ű�� Ʈ���� ����
-- Ʈ���Ÿ� : tri_purchase_update_item

SELECT * FROM purchase;
SELECT * FROM item;

CREATE OR REPLACE TRIGGER tri_purchase_update_item
    AFTER INSERT ON purchase
    FOR EACH ROW
BEGIN
    UPDATE item SET quantity = quantity - :new.cnt  WHERE code = :new.code;
END;
/

INSERT INTO purchase(no, cnt, regdate, code, userid)
VALUES(seq_purchase_no.NEXTVAL,100,CURRENT_DATE,1,'a');
commit;


--=================================================================================
-- ����) ���� �ֹ����� 10�� ��� 9�� ����Ǹ� ������ 1 ������.
--  11�� ����Ǹ� ������ 1 ���ҵ�.
CREATE OR REPLACE TRIGGER tri_purchase_cnt_item
    AFTER UPDATE ON purchase
    FOR EACH ROW
BEGIN
    IF :old.cnt > :new.cnt THEN
        UPDATE item SET quantity = quantity + (:old.cnt-:new.cnt)  WHERE code = :new.code;
    ELSE
        UPDATE item SET quantity = quantity - (:new.cnt-:old.cnt)  WHERE code = :new.code;
    END IF;
END;
/


-- �ֹ����� ������ ������ ������� �ǵ����� ============================================
CREATE OR REPLACE TRIGGER tri_purchase_cnt_back_item
    AFTER DELETE ON purchase
    FOR EACH ROW
BEGIN
    UPDATE item SET quantity = quantity + :old.cnt  WHERE code = :old.code;
END;
/

DELETE FROM purchase WHERE no = 10034;
rollback;
commit;

ALTER TRIGGER tri_purchase_update_item DISABLE;
ALTER TRIGGER tri_purchase_cnt_item DISABLE;
ALTER TRIGGER tri_purchase_cnt_back_item DISABLE;
COMMIT;

-- ������ ���� ��ġ�� ===============================================================
CREATE OR REPLACE TRIGGER tri_purchase_cnt_action
    AFTER INSERT OR UPDATE OR DELETE ON purchase
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE item SET quantity = quantity - :new.cnt  WHERE code = :new.code;
    ELSIF UPDATING THEN
        IF :old.cnt > :new.cnt THEN
            UPDATE item SET quantity = quantity + (:old.cnt-:new.cnt)  WHERE code = :new.code;
        ELSE
            UPDATE item SET quantity = quantity - (:new.cnt-:old.cnt)  WHERE code = :new.code;
        END IF;
    ELSIF DELETING THEN
        UPDATE item SET quantity = quantity + :old.cnt  WHERE code = :old.code;
    END IF;
END;
/

-- �ϰ� ���� ===========================================================
SELECT m.* FROM member m;

SELECT m.* FROM member m ORDER BY userid ASC;
UPDATE member SET username='�����ϱ�', userage=29
WHERE userid='a2000' OR userid='a2001';
COMMIT;

-- ���̵� a1000, a1001�� �׸� ���ؼ� ������ �̸��� ���� �ϰ�����
UPDATE member SET
    username = (CASE
        WHEN userid='a2000' THEN '�̸�1000'
        WHEN userid='a2001' THEN '�̸�1001'
    END),
    userage = (CASE
        WHEN userid='a2000' THEN 111
        WHEN userid='a2001' THEN 222
    END)
    WHERE userid IN('a2000','a2001');
COMMIT;


-- upsert
-- ���̵� a1000�� ������ update, ������ insert�� ����
MERGE INTO
    member
USING DUAL
    ON (userid='a1000')
    WHEN MATCHED THEN
        UPDATE SET username='bbb', userage=111
    WHEN NOT MATCHED THEN
        INSERT (userid, userpw, username, userage, userphone, usergender, userdate)
            VALUES('a1000','��ȣ2','�̸�2',22,'010-','M', CURRENT_DATE);
COMMIT;


