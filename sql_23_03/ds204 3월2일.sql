SET SERVEROUTPUT ON;

-- 트리거 => 어떤 테이블에 insert, update, delete가 수행될 때 자동으로 호출되는 프로시저
-- member테이블에 데이터가 추가 되면 memver_bk테이블에 추가, 삭제, 수정
-- 구매테이블에 주문하면 아이템테이블의 재고수량 변경

--구조
CREATE OR REPLACE TRIGGER 트리거명
    (BEFORE | AFTER) INSERT, DELETE, UPDATE ON 테이블명
    FOR EACH ROW -- 행의 데이터가 변환되면 적용됨
    
BEGIN

END;
/
---------------------------------------------------------
-- NNN에 데이터 삽입
CREATE OR REPLACE TRIGGER TRI_NNN_INSERT
    AFTER INSERT ON NNN
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
BEGIN
DBMS_OUTPUT.PUT_LINE('NNN테이블에 데이터가 추가되었음');

END;
/

SELECT * FROM NNN;

--확인
INSERT INTO NNN(id, name, pw, age, mdate)
VALUES('C1','새 이름', '새 비번', 45, CURRENT_DATE);

COMMIT;

---------------------------------------------------------
--테이블 만듦(왜만들지?)
CREATE TABLE NNN_BACKUP AS SELECT n.* FROM nnn n;

SELECT n.* FROM NNN_BACKUP n;
SELECT n.* FROM NNN n;

--- NNN테이블 데이터 변경시 NNN_BACKUP에도 데이터 변경
CREATE OR REPLACE TRIGGER NNN_BACKUP
    AFTER INSERT ON NNN
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
BEGIN
    INSERT INTO nnn_backup(id, name, pw, age, mdate)
    VALUES(:new.id,:new.name,:new.pw,:new.age,CURRENT_DATE);
END;
/
---------------------------------------------------------

---NNN데이터 변경되면 메세지 뜸
CREATE OR REPLACE TRIGGER tri_nnn_upd
    AFTER UPDATE ON nnn
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
BEGIN
    DBMS_OUTPUT.PUT_LINE('nnn 테이블에 데이터가 변경됨');
    DBMS_OUTPUT.PUT_LINE('변경전이름 ' || :old.name);
    DBMS_OUTPUT.PUT_LINE('변경후이름 ' || :new.name);
END;
/
--확인
UPDATE nnn SET name = '변경하기', age =29 
WHERE id='A2';
-- >??? 업데이트 메세지만 뜨고 트리거메세지 안뜸

---------------------------------------------------------

--- nnn테이블 업데이트 되면 nnn_backup에도 정보 변경
CREATE OR REPLACE TRIGGER tri_nnn_upd2
    AFTER UPDATE ON nnn
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
BEGIN
    UPDATE nnn_backup SET id=:new.id, name=:new.name, pw=:new.pw, age=:new.age
        WHERE id=:old.id;
END;
/

--- nnn에서 아이디를 삭제하면 백업에서도 이전 아이디 삭제
CREATE OR REPLACE TRIGGER tri_nnn_upd3
    AFTER DELETE ON nnn
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
BEGIN
    DELETE FROM nnn_backup WHERE id=:old.id;
END;
/


--- INSERT UPDATE DELETE 한번에
CREATE OR REPLACE TRIGGER tri_nnn_UPINDE
    AFTER INSERT OR UPDATE OR DELETE ON nnn
    FOR EACH ROW -- 행의 데이터가 변환되면 적용
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


--문제 : purchase 테이블에 주문이 추가되면 item테이블의 재고수량을 주문한만큼 차감시키는 트리거 생성
-- 트리거명 : tri_purchase_update_item
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
--다안품
INSERT INTO purchase (no, cnt, regdate, code, userid)
    VALUES(seq_purchase_no.NEXTVAL, 1, CURRENT_DATE, 1, lsd);


--문제 : purchase에 cnt주문수량이 변경되면 item quantity재고수량을 변경하는 트리거 생성
-- 기존 주문 수량이 10일경우 9로 변경되면 재고수량 1증가됨.
-- 기존 주문 수량이 10일경우 11로 변경되면 재고수량 1감소됨.
CREATE OR REPLACE TRIGGER tri_purchase_cnt_item
    AFTER UPDATE ON purchase
    FOR EACH ROW
BEGIN
    IF :old.cnt > :new.cnt THEN --재고수량 증가
        UPDATE item SET quantity = quantity - (:old.cnt - :new.cnt)  WHERE code=:new.code;
    ELSE -- 재고수량 감소 
         UPDATE item SET quantity = quantity -(:new.cnt - :old.cnt) WHERE code=:new.code;
    END IF;
END;
/

-- 트리거 비활성화
ALTER TRIGGER tri_purchase_cnt_item DISABLE;
--테이블 만듦(왜만들지?)
CREATE TABLE purchase_BACKUP AS SELECT p.* FROM purchase p;
-- 주문내역의 추가 수정 삭제시 재고수량을 실시간으로 변경하는 트리거 작성
-- item? purchase? purchase_BACKUP?
-- 블로그에 올려주심 나중에 참고
CREATE OR REPLACE TRIGGER tri_purchase_cnt_action
    AFTER INSERT OR UPDATE OR DELETE ON purchase
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
      INSERT INTO purchase (no, cnt, regdate, code, userid)
        VALUES(seq_purchase_no.NEXTVAL, :new.cnt ,CURRENT_DATE ,:new.code ,:new.userid);
        DBMS_OUTPUT.PUT_LINE('INSERT완료');
    ELSIF UPDATING THEN
        UPDATE item 
        SET quantity = quantity - :new.cnt 
        WHERE code=:new.code;
        DBMS_OUTPUT.PUT_LINE('UPDATE완료');
    ELSIF DELETING THEN
        DELETE FROM purchase WHERE no=:old.no;
        DBMS_OUTPUT.PUT_LINE('DELETE완료');
    END IF;
END;
/
--넣어봄
INSERT INTO purchase( no, cnt, regdate, code, userid)
     VALUES(seq_purchase_no.NEXTVAL, 10, CURRENT_DATE, 37, 'a1' );
COMMIT;

SELECT i.* FROM item i;
SELECT p.* FROM purchase p;
SELECT m.* FROM member m;



-----------------------
-- 쿼리문
-- 일괄추가(시퀀스,함수)

-- 일괄 수정
SELECT m.* FROM member m;
UPDATE member SET username='qqurudgkrl', userage=77 ??????????
/

-- 아이디가 a1a2인 항목에서 각각 이름과 나이 일괄변경
UPDATE member SET 
    username = (CASE 
        WHEN userid='a1' THEN '강옥'
        WHEN userid='a2' THEN '차경'
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



