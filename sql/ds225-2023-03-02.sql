-- 트리거 => 어떤 테이블에 insert, update, delete 가 수행될 때 자동으로 호출되는 프로시저
-- member 테이블에 데이터가 추가되면 member_bk테이블에 추가, 삭제, 수정

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER 트리거명
    (BEFORE | AFTER) INSERT, DELETE, UPDATE ON 테이블명
    FOR EACH ROW -- 행의 데이터가 변환되면 적용됨.
BEGIN

END;
/

-- 트리거 비활성화(DISABLE) 활성화(ENABLE)
ALTER TRIGGER 트리거명 DISABLE;


CREATE OR REPLACE TRIGGER tri_member_insert
    AFTER INSERT ON member
    FOR EACH ROW -- 행의 데이터가 변환되면 적용됨.
BEGIN
    DBMS_OUTPUT.PUT_LINE('member 테이블에 데이터가 추가되었음');
END;
/

CREATE TABLE member_backup AS SELECT m.* FROM member m;

SELECT m.* FROM member_backup m;
SELECT m.* FROM member m;


--<<INSERT 트리거>>==================================================================
CREATE OR REPLACE TRIGER tri_member_backup_insert
    AFTER INSERT ON member
    FOR EACH ROW
BEGIN
    INSERT INTO member_backup(userid, userpw, username, userage, userphone, usergender, userdate)
    VALUES(:new.userid, :new.userpw, :new.username, :new.userage, :new.userphone, :new.usergender, CURRENT_DATE)
    -- commit은 사용할 수 없음, 자동으로 commit됨.
END;
/


INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
VALUES('a2001','암호','이름',12,'010','M',CURRENT_DATE);
COMMIT;


--<<UPDATE 트리거>>====================================================
CREATE OR REPLACE TRIGGER tri_member_update
    AFTER UPDATE ON member
    FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('member 테이블에 데이터가 변경됨');
    DBMS_OUTPUT.PUT_LINE('변경전 이름=>' || :old.username);
    DBMS_OUTPUT.PUT_LINE('변경후 이름=>' || :new.username);
    
END;
/

UPDATE member SET username='변경하기', userage=28 WHERE userid='d234';
COMMIT;


CREATE OR REPLACE TRIGGER tri_member_backup_update
    AFTER UPDATE ON member
    FOR EACH ROW
BEGIN
   UPDATE member_backup SET username=:new.username, userage =:new.userage
   WHERE userid=:old.userid;
END;
/

UPDATE member SET username='변경하기2', userage=28 WHERE userid='d234';
COMMIT;


--<<DELETE 트리거>>============================================================
CREATE OR REPLACE TRIGGER tri_member_backup_delete
    AFTER DELETE ON member
    FOR EACH ROW
BEGIN
    DELETE FROM member_backup WHERE userid=:old.userid;
END;
/


DELETE FROM member WHERE userid='d234';


-- insert, update, delete 한꺼번에 ==========================================
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
-- 문제) purchase 테이블에 주문이 추가되면 item테이블의 재고 수량을 주문한만큼 차감시키는 트리거 생성
-- 트리거명 : tri_purchase_update_item

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
-- 문제) 기존 주문수량 10일 경우 9로 변경되면 재고수량 1 증가됨.
--  11로 변경되면 재고수량 1 감소됨.
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


-- 주문수량 삭제시 재고수량 원래대로 되돌리기 ============================================
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

-- 위에꺼 세개 합치기 ===============================================================
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

-- 일괄 수정 ===========================================================
SELECT m.* FROM member m;

SELECT m.* FROM member m ORDER BY userid ASC;
UPDATE member SET username='변경하기', userage=29
WHERE userid='a2000' OR userid='a2001';
COMMIT;

-- 아이디가 a1000, a1001인 항목에 대해서 각각의 이름과 나이 일괄변경
UPDATE member SET
    username = (CASE
        WHEN userid='a2000' THEN '이름1000'
        WHEN userid='a2001' THEN '이름1001'
    END),
    userage = (CASE
        WHEN userid='a2000' THEN 111
        WHEN userid='a2001' THEN 222
    END)
    WHERE userid IN('a2000','a2001');
COMMIT;


-- upsert
-- 아이디가 a1000이 있으면 update, 없으면 insert를 수행
MERGE INTO
    member
USING DUAL
    ON (userid='a1000')
    WHEN MATCHED THEN
        UPDATE SET username='bbb', userage=111
    WHEN NOT MATCHED THEN
        INSERT (userid, userpw, username, userage, userphone, usergender, userdate)
            VALUES('a1000','암호2','이름2',22,'010-','M', CURRENT_DATE);
COMMIT;


