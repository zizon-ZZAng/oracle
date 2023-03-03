--전체 조회
--SELECT 컬럼명 FROM 테이블명 별칭;

SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;
SELECT i.* FROM item i;
SELECT p.* FROM purchase p;

--SEQUENCE 만들기
--CACHE 1001 1002 1003     좀 쉬고있으면 .. 1021이 됨 => NOCACHE로 설정
CREATE SEQUENCE seq_memberaddr_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_item_code INCREMENT BY 2 START WITH 1 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_purchase_no INCREMENT BY 1 START WITH 10001 NOMAXVALUE NOCACHE;


--회원주소등록
INSERT INTO memberaddr(userno , useraddr, userpostcode, userdate, userid)
VALUES(seq_memberaddr_no.NEXTVAL,'부산시 수영구',41257,CURRENT_DATE,'e');


--회원가입
--INSERT INTO 테이블명(컬럼명들...) VALUES(실제추가할 값들);
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
VALUES( 'e','abcdef','라마',5,'010-1456-4528','F',CURRENT_DATE);   --내장함수 CURRENT_DATE

--물품 등록
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'젤리',1300,30,'달콤',CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'초콜릿',15000,100,'달콤',CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'과자',10000,3,'냠냠',CURRENT_DATE);

--주문하기
--code는 기본ㅋㅣ, userid는 member의 기본키
INSERT INTO purchase(no,cnt,regdate,code,userid)VALUES(seq_purchase_no.NEXTVAL,115,CURRENT_DATE,5,'c');


--수정하기
--UPDATE 테이블명 SET 컬럼명=변경값 WHERE 조건;
UPDATE member SET username='라마바' WHERE userid='a';

--이름과 가격, 수량 변경
UPDATE item SET name='아이스크림', price =13200, quantity =11 WHERE code=5;

--재고수량 3000이하인것만 재고수량을 5000개로 수정
UPDATE item SET quantity=5000 WHERE quantity<=3000;


--중요한 정보는 삭제보다는 null값으로 만들기
UPDATE member SET userpw='', username='', userage=0, 
userphone='',usergender=null, userdate=null WHERE userid='a';

--삭제하기
--DELETE FROM 테이블 WHERE 조건;
DELETE FROM item WHERE code =17;


COMMIT; --적용(커밋까지해야 정확히 들어간 것임)
ROLLBACK; --되돌리기
