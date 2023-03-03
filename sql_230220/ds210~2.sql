--전체 조회
--SELECT 컬럼명 FROM 테이블명 별칭;

SELECT m.* FROM member m;   --회원

SELECT ma.* FROM memberaddr ma; --회원주소

SELECT i.* FROM item i; --물품

SELECT p.* FROM purchase p; --주문

COMMIT; 

--값 추가(회원가입)
--INSERT INTO 테이블명(컬럼명들) VALUES(실제 추가할 값들);
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('a','a','가나다',12,'010-0000-0000','F',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('b','b','다나가',21,'010-0001-0002','M',CURRENT_DATE);      

INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('c','c','비둘기',8,'010-9999-9999','F',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('d','d','빅파이',30,'010-3143-1431','M',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('e','d','빅파이',30,'010-3143-1431','M',CURRENT_DATE);

-- 값을 추가하고 나면 COMMIT or ROLLBACK을 꼭 해줘야 한다.

--적용
COMMIT;     -- 찝찝하면 여러번 해봐

--되돌리기
ROLLBACK;   --commit을 먼저해야 rollback을 해도 없어지지 않음

--------------------------------------------------------------------------------

--시퀀스 생성 (memberaddr)
CREATE SEQUENCE seq_memberaddr_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;

--값 추가(회원주소등록)
INSERT INTO memberaddr(userid, userdate, userpostcode, useraddr, userno) 
VALUES('c',CURRENT_DATE, 55789, '부산사상구',seq_memberaddr_no.NEXTVAL);

COMMIT; --적용

--------------------------------------------------------------------------------

--시퀀스 생성 (item)
CREATE SEQUENCE seq_item_code INCREMENT BY 2 START WITH 1 NOMAXVALUE NOCACHE;

--값 추가(item 등록)
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '사과', 15020, 100, '상큼', CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '제로펩시', 2000, 10, '탄산', CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '수박', 18900, 50, '신선', CURRENT_DATE);

COMMIT;

--------------------------------------------------------------------------------

--시퀀스 생성
CREATE SEQUENCE seq_purchase_no INCREMENT BY 1 START WITH 10001 NOMAXVALUE NOCACHE;

--값 추가(purchase 등록)
-- code는 item의 기본키, userid는 member의 기본키
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 5, CURRENT_DATE,1, 'a');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 10, CURRENT_DATE,3, 'b');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 2, CURRENT_DATE,5, 'c');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 1, CURRENT_DATE,1, 'd');

COMMIT;

--------------------------------------------------------------------------------

--수정하기 
--UPDATE 테이블명 SET 컬럼명=변경값, 컬럼명=변경값 WHERE 조건;
UPDATE member SET username='마우스' WHERE userid='a';

COMMIT; -- 조회하고 잘 바뀌면 commit 

UPDATE member SET username='제어판' WHERE userid='b';

UPDATE item SET name='청사과', price=7800, quantity='7008' WHERE code='1';


-- 재고수량 3000이하인것만 재고수량을 5000개로 변경
UPDATE item SET quantity=5000 WHERE quantity <= 3000;

--------------------------------------------------------------------------------

--주의해야함
--삭제하기
--DELETE FROM 테이블 WHERE 조건;

DELETE FROM item WHERE code=11;

COMMIT;

--------------------------------------------------------------------------------
--회원 데이터 삭제하는 방법 (비워두기하면 됨)
UPDATE member SET userpw='', username='', userage=0, userphone='', usergender=null, userdate=null WHERE userid='e';

