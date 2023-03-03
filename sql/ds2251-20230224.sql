SELECT c.* FROM customer c;
SELECT o.* FROM ordertbl o;
SELECT m.* FROM menu m;
SELECT d.* FROM delivery d;
commit;

-- 기본키로 조건을 잡음, 결과가 1개이거나 없거나
-- 해쉬 비밀번호 단방향 : 다시 되돌아 갈 수 없음...?
SELECT c.* FROM customer c WHERE c.email='javaid';


-- 주문하기(기본키, 고객아이디+메뉴의 정보)
INSERT INTO ordertbl(no, cnt, email, menuno)
VALUES(seq_ordertbl_no.NEXTVAL, 1,'DJSKFJ9@naver.com', 1022); 


-- 주문수량 변경
UPDATE ordertbl SET cnt=3
WHERE no=100006 AND email = 'DJSKFJ2@naver.com' AND menuno = 1014;


-- 주문내역조회
SELECT o.* FROM ordertbl o 
WHERE no=100006 AND email = 'DJSKFJ2@naver.com' AND menuno = 1014;


-- 고객+메뉴+주문을 조인한 뷰 만들기
CREATE OR REPLACE VIEW customerOrderMenuview AS

SELECT o.no orderNo,o.regdate orderRegdate, o.cnt, o.menuno,
m.name, m.price, m.phone,
c.email, c.phone customerPhone, c.address, c.chk  
FROM ordertbl o, menu m, customer c
WHERE c.email = o.email AND m.no = o.menuno;
commit;

--=====================================================
SELECT r.* FROM rider r;
SELECT d.* FROM delivery d;


-- 배달자 등록
INSERT INTO rider( phone, name, regdate, password )
VALUES ( '010-0001-0000', '배달자이름', CURRENT_DATE, '암호'); 
commit;


-- 배달자 1명 조회
SELECT r.* FROM rider r WHERE phone='010-0001-0000';


-- 배달 등록 (기본키, 외래키 2개)
INSERT INTO delivery(no, regdate, phone, orderno)
VALUES ( seq_delivery_no.nextval, CURRENT_DATE, '010-0001-0009',100009);
commit;


-- ordertblview + 식당정보를 inner join한 ordertblview1
SELECT c.* From customerOrderMenuview c;

CREATE OR REPLACE VIEW ordertblview1 AS
SELECT c.*, r.name restName, r.address restAddress FROM customerOrderMenuview c
INNER JOIN restaurant r ON r.phone = c.phone;


-- 배달 + 배달자를 inner join한 deliveryview 생성
CREATE OR REPLACE VIEW deliveryview AS
SELECT d.orderno, d.no, d.regdate, r.phone, r.name FROM delivery d
INNER JOIN rider r ON d.phone = r.phone;

DELETE FROM delivery WHERE phone='010-0001-0009';



-- ordertblview1 + deliveryview를 inner join한 deliveryinfoview생성
CREATE OR REPLACE VIEW deliveryinfoview AS
SELECT ov.*, dv.no, dv.phone riderPhone, dv.name riderName, dv.regdate
FROM ordertblview1 ov
INNER JOIN deliveryview dv ON dv.orderno = ov.orderno;



-- *** deliveryinfoview를 이용한 통계 구하기 ***
-- 메뉴별 전체 주문 수량 및 전체금액 (어떤 메뉴가 잘팔리는가?)
-- 고객별 전체 주문 횟수 및 전체금액 (어떤 고객이 vip인가?)
-- 주문 시간대별 주문횟수 및 전체금액 (어느 시간대에 주문을 많이 하는가?)
-- 배달시간대별 배달 횟수 (어느 시간대에 배달을 많이 하는가?)
-- 배달자별 배달건수 (어느 배달기사가 배달을 많이 했는가?)
-- 식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량? (우리 가게에는 어떤 메뉴가 잘나가나?)
