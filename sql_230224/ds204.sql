SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m ORDER BY menuno ASC;
COMMIT; 

--주문하기(기본키, 고객아이디+메뉴의정보)
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 2 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 5 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 1 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 3 ,'ktng', 1023);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 8 ,'ktng', 1001);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 9 ,'eret@poi.com', 1022);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 9 ,'eret@poi.com', 1023);

--주문수량변경
UPDATE ordertbl
SET cnt = 7
WHERE ortherno = 10001 AND email = 'eret@dfd.com';

--주문내역조회
SELECT o.* FROM ordertbl o WHERE email = 'ktng';

--고객+메뉴+주문을 조인한 뷰 만들기
--CREATE OR REPLACE VIEW ordertblview AS
SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m;
-- 일단 다 확인해보자

CREATE OR REPLACE VIEW ordertblview AS
SELECT 
m.menuno mn , m.name menuname , m.price menuprice, m.retaurantphone rp, 
c.email cusemail,  c.phone cusphone, c.address cusadrs, 
o.ortherno odrno , o.regdate orrdate , o.cnt odrcnt
FROM menu m, customer c, ordertbl o 
WHERE c.email = o.email AND m.menuno = o.menuno;
-- view를 만들자
-- SELECT 와 FROM 과 WHERE 과 AND 를 써서 일단 메뉴 고객 주문을 다 포함한 테이블을 뽑아봄
-- 자 이제 불필요한(중복이나 그런)걸 제거할건데요
-- CREATE OR REPLACE VIEW 무슨뷰 AS 쓰고
-- SELECT에 view에 넣고 싶은 모든걸 씀(이때 o인지 c인지 m인지 잘 봐야함)
--      FROM menu m, customer c, ordertbl o 
--      WHERE c.email = o.email AND m.menuno = o.menuno;
-- 넣어줌

SELECT ov.* FROM ordertblview ov;
--뷰를 확인함 (자바로 ㄱㄱ)



-------------------------
SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m ORDER BY menuno ASC;
SELECT r.* FROM rider r;
SELECT d.* FROM delivery d;

COMMIT;

--배달자 등록
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1454-5323','박씨','djkls', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-6234-4243','이씨','djkf111', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-2434-7894','류씨','dsf999', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-7234-6523','한씨','fghzh000', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1234-4589','김씨','tyud99', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1934-1211','유씨','ghs33', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-8234-1848','R강씨','rgf44', CURRENT_DATE );


--배달 등록
CREATE SEQUENCE seq_delivery_no INCREMENT BY 1 START WITH 10 NOMAXVALUE NOCACHE;

INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1454-5323', 10017 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-6234-4243', 10019 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-2434-7894', 10008 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-7234-6523', 10010 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1234-4589', 10011 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1934-1211', 10012 );
--배달자 한명 조회
SELECT r.* FROM rider r WHERE riderphone = '010-9234-1848';

--ordertblview + 식당정보를 inner join한 ordertblview1 생성
CREATE OR REPLACE VIEW ordertblview1 AS
SELECT 
ov.*, r.name rtrname, r.address rtradr
FROM ordertblview ov, restaurant r WHERE ov.rp = r.retaurantphone;

-- 배달 + 배달자를 inner join한 deliveryview 생성
CREATE OR REPLACE VIEW deliveryview AS
SELECT 
r.riderphone ridphone, r.name ridname, r.regdate ridregdate,
d.deliveryno dlvrno , d.regdate dlvrregdate, d.ortherno dlvrodrno 
FROM rider r, delivery d WHERE r.riderphone=d.riderphone ;


SELECT o.* FROM ordertblview o;
SELECT o1.* FROM ordertblview1 o1;
SELECT d.* FROM deliveryview d;
SELECT * FROM deliveryinfoview;
-- ordertblview1 + deliveryview를 inner join한 deliveryinfoview 생성
CREATE OR REPLACE VIEW deliveryinfoview AS
SELECT ov1.*, dv.* FROM ordertblview1 ov1, deliveryview dv WHERE dv.dlvrodrno= ov1.odrno;

-----------------------------------------------------------
SELECT * FROM deliveryinfoview;
-- *deliveryinfoview를 이용한 통계 구하기*
-- 메뉴별 전체 주문수량 및 전체금액(어떤 메뉴가 잘 팔리는가?)
SELECT menuname, SUM(odrcnt), (menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY menuprice, menuname, odrcnt;

-- 고객별 전체 주문횟수 및 전체금액(어떤 고객이 vip인가?)
SELECT cusemail, SUM(odrcnt), SUM(menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY cusemail;

-- 배달 시간대별 주문횟수 및 전체금액(어느 시간대에 주문을 많이 하는가?)
SELECT TO_CHAR(dlvrregdate, 'HH24'), COUNT(*) odrcnt, SUM(menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY TO_CHAR(dlvrregdate, 'HH24');

-- 배달자별 배달건수(어떤 라이더가 배달을 많이 했는가?)
SELECT ridphone, COUNT(*) odrcnt
FROM deliveryinfoview GROUP BY ridphone;

-- 식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량?(우리가게에는 어떠 메뉴가 가장 잘나가나)
SELECT menuname, SUM(odrcnt)
FROM deliveryinfoview WHERE rp = '051-153-3226' GROUP BY menuname;

-- 요일별 주문횟수
SELECT TO_CHAR( orrdate, 'day' ), COUNT(*) odrcnt
FROM deliveryinfoview GROUP BY TO_CHAR( orrdate, 'day' );


