
--전체조회
SELECT r.* FROM restaurant r;
SELECT m.* FROM menu m;
SELECT c.* FROM customer c;


--수정
UPDATE menu SET name='멘보샤', price=14000, content='맛있나?' WHERE no=1010 AND phone='051-000-0000' ;

--등록
COMMIT; -- 적용시키기
ROLLBACK; -- 되돌리기

INSERT INTO restaurant(phone, name, address, password) VALUES('051-000-0000','중국집','부산 남구', 'a');

INSERT INTO menu(no, name, price, content, phone) VALUES(seq_menu_no.NEXTVAL, '돼지국밥', 8000, '진함','051-001-0002');

--INSERT INTO customer(email, password, phone, address, chk) VALUES('a123@naver.com','a123', '010-0000-0001', '부산 남구', 0);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('b123@gmail.com','a5a5', '010-0000-0002', '부산 수영구', 1);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('c123@gmail.com','48sa2', '010-0000-0003', '부산 사상구', 0);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('d123@daum.com','77d8', '010-0000-0004', '부산 서구', 0);


--시퀀스 생성, 시퀀스명은 고유해야함
CREATE SEQUENCE seq_menu_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_ordertbl_no INCREMENT BY 1 START WITH 100001 NOMAXVALUE NOCACHE;



--------------------------------------------------------------------------------
--식당
CREATE TABLE restaurant
(
  phone    VARCHAR2(15)  NOT NULL,
  name     VARCHAR2(100),
  address  VARCHAR2(200),
  password VARCHAR2(200),
  regdate  TIMESTAMP DEFAULT CURRENT_DATE,
  CONSTRAINT PK_restaurant PRIMARY KEY (phone)
);

--메뉴
CREATE TABLE menu
(
  no      NUMBER        NOT NULL,
  name    VARCHAR2(100) NOT NULL,
  price   NUMBER        DEFAULT 0 NOT NULL,
  content CLOB,
  regdate TIMESTAMP     DEFAULT CURRENT_DATE,
  phone   VARCHAR2(15)  NOT NULL,
  CONSTRAINT PK_menu PRIMARY KEY (no)
);

--고객
CREATE TABLE customer
(
  email    VARCHAR2(200) NOT NULL,
  password VARCHAR2(200),
  phone    VARCHAR2(15) ,
  address  VARCHAR2(200),
  chk    NUMBER(1),--check임 0 또는 1
  regdate  TIMESTAMP     DEFAULT CURRENT_DATE,
  CONSTRAINT PK_customer PRIMARY KEY (email)
);

--주문
CREATE TABLE ordertbl
(
  no      NUMBER        NOT NULL,
  regdate TIMESTAMP     DEFAULT CURRENT_DATE,
  cnt     NUMBER        DEFAULT 1,
  email   VARCHAR2(200) NOT NULL,
  menuno  NUMBER        NOT NULL,
  CONSTRAINT PK_ordertbl PRIMARY KEY (no)
);

--배달자
CREATE TABLE rider
(
  phone    VARCHAR2(15)  NOT NULL,
  name     VARCHAR2(20) ,
  regdate  TIMESTAMP DEFAULT CURRENT_DATE,
  password VARCHAR2(200),
  CONSTRAINT PK_rider PRIMARY KEY (phone)
);


--배달
CREATE TABLE delivery
(
  no      NUMBER       NOT NULL,
  regdate TIMESTAMP    DEFAULT CURRENT_DATE,
  phone   VARCHAR2(15) NOT NULL,
  orderno NUMBER       NOT NULL,
  CONSTRAINT PK_delivery PRIMARY KEY (no)
);

-- menu 테이블의 phone은 식당테이블의 기본키로 외래키 생성
ALTER TABLE menu
  ADD CONSTRAINT FK_restaurant_TO_menu
    FOREIGN KEY (phone)
    REFERENCES restaurant (phone);

--주문 테이블의 email은 고객테이블 기본키인 email로 외래키 생성
ALTER TABLE ordertbl
  ADD CONSTRAINT FK_customer_TO_ordertbl
    FOREIGN KEY (email)
    REFERENCES customer (email);

--주문 테이블의 menuno는 메뉴테이블의 기본키 no로 외래키 생성
ALTER TABLE ordertbl
  ADD CONSTRAINT FK_menu_TO_ordertbl
    FOREIGN KEY (menuno)
    REFERENCES menu (no);


-- 배달테이블의 phone은 배달자 테이블 기본키인 phone으로 외래키 생성
ALTER TABLE delivery
  ADD CONSTRAINT FK_rider_TO_delivery
    FOREIGN KEY (phone)
    REFERENCES rider (phone);

-- 배달테이블의 orderno는 주문 테이블의 기본키 no의 외래키 생성
ALTER TABLE delivery
  ADD CONSTRAINT FK_ordertbl_TO_delivery
    FOREIGN KEY (orderno)
    REFERENCES ordertbl (no);

--------------------------------------------------------------------------------

-- 전체 조회
-- SELECT 컬럼명 FROM 테이블명 별칭;
SELECT i.* FROM item i;
SELECT i.code, i.name, i.quantity FROM item i;

-- 데이터 정렬 order by 정렬컬럼 ASC|DESC;
-- 데이터 정렬은 데이터를 먼저 가져와서 정렬을 할 수 있다 정렬이 제일 처음에 올 순 없다.
SELECT i.* FROM item i ORDER BY i.code DESC;

--조건도 데이터를 먼저 가져와서 조건을 붙여줘야함
SELECT i.* FROM item i WHERE i.price >= 10000 ORDER BY i.code DESC;

-- AND
SELECT i.* FROM item i WHERE i.price >= 5000 AND i.price <=10000 ORDER BY i.code DESC;

-- 회원 테이블에서 나이가 10~30인것만 조회 (아이디 오름차순)
SELECT m.* FROM member m WHERE m.userage>=10 AND m.userage<=30 ORDER BY m.userid ASC;

-- OR로 하나하나 다 적는 방법
SELECT i.* FROM item i WHERE i.code=1 OR i.code=3 OR i.code=13 ORDER BY i.code DESC;
-- IN에 넣는 방법
SELECT i.* FROM item i WHERE i.code IN(1,3,13) ORDER BY i.code DESC;

-- WHERE 컬럼명 LIKE '아무거나' || '포함할것' || '아무거나'; => 포함하는거 모든걸 출력
-- WHERE 컬럼명 LIKE '아무거나' || '포함할것'; => 포함하는걸로 끝나는 것 찾기
-- WHERE 컬럼명 LIKE '포함할것' || '아무거나'; => 포함하는걸로 시작하는 것 찾기
-- %가 아무거나임
SELECT i.*, i.price*i.quantity total FROM item i WHERE i.name LIKE '%' || '제' || '%';


--함수의 기능만 확인하고싶을 땐 SELECT * FROM 테이블명; => 테이블명 넣는 위치에 DUAL쓰면 됨
SELECT ABS(-2) FROM DUAL;
SELECT CURRENT_DATE FROM DUAL; --자바에선 new Date() 였음 

-- TO_CHAR(숫자 or 날짜, 'format') -> format에는 내가 원하는 형태 넣어줘야함
SELECT TO_CHAR(123456789, '999,999,999') fROM DUAL; 

SELECT i.*, TO_CHAR(i.price*i.quantity, '999,999,999') total FROM item i;

SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD HH:MI:SS') 날짜 FROM DUAL;

-- 1. 전체조회 테이블 개수와 상관없이 조회할 것
-- 2. 필터 조건으로 필요한 것만 필터 (WHERE)
-- 3. 정렬 (ORDER BY 정렬할컬럼 ASC|DESC)
-- 4. 가져온 데이터를 필요한 형태로 가공(내장함수) SELECT 컬럼명, 내장함수 FROM 테이블명 별칭;


SELECT m.userid, m.username, m.userage, m.userphone, m.userdate, TO_CHAR(m.userdate, 'YYYY-MM-DD HH') userdate1 
FROM member m WHERE m.userage >= 10 ORDER BY m.userid ASC;

-- 지금 전체 데이터 개수는 5갠데 그 값을 전부 들고오는거면 오류가 안나는데
-- 개수를 하나짜리만 구하는 걸 넣으면 오류가 생김.
-- 가공을 할 땐 개수가 중요하다
SELECT COUNT(*), MAX(i.price), MAX(i.quantity) FROM item i ORDER BY i.code DESC;


-- SELECT으로 가공된 내역은 가상의 테이블
-- ROW_NUMBER() OVER(ORDER BY 컬럼명 ASC|DESC) => 번호를 매겨줌
SELECT i1.* FROM ( --가상의 테이블 생성
    SELECT i.*, ROW_NUMBER() OVER(ORDER BY i.price ASC) rown    -- ()사이에 있는것들이 원래 테이블명 자리였던거라고 생각
    FROM item i ORDER BY i.code DESC) i1 WHERE i1.rown >=1 AND i1.rown <=5;
    --얘네가 지금 가공됐는데 가상의 테이블에 넣어버린 상황?

-- view(=가상의 테이블) 만들기 CREATE OR REPLACE VIEW 뷰이름 AS 쿼리
-- view는 selet만 된다 insert, update 등 안됨
-- view를 만들어서 넣어버림 그 복잡한것들을 
CREATE OR REPLACE VIEW itemview AS 
SELECT i.*, ROW_NUMBER() OVER(ORDER BY i.price ASC) rown
    FROM item i ORDER BY i.code DESC;
      

-- 가공된게 테이블처럼 나왔음
SELECT iv.* FROM itemview iv;


SELECT iv.* FROM itemview iv WHERE iv.rown >=1 and iv.rown <=3;

DELETE FROM itemview iv WHERE iv.code = 13; --안됨
DELETE FROM item i WHERE i.code=13; -- 수정할 땐 원본 테이블에서 수정해야함
ROLLBACK;
COMMIT;

SELECT p.* FROM purchase p ORDER BY p.no ASC;

SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;

--회원 테이블과 회원 주소테이블을 합쳤음
SELECT m.*, a.userno, a.useraddr, a.userpostcode FROM member m, memberaddr a WHERE m.userid=a.userid;

-- 위에서 합친걸 view로 새로 만들었음
CREATE OR REPLACE VIEW memberaddrview AS
SELECT m.*, a.userno, a.useraddr, a.userpostcode FROM member m, memberaddr a WHERE m.userid=a.userid;

-- 가상테이블 조회
SELECT mav.* FROM memberaddrview mav;

-- 삭제하려면 무조건 원본테이블로가서 삭제
DELETE FROM memberaddr WHERE userno=1004;

SELECT p.* FROM purchase p;

-- 회원테이블과 구매내역 테이블 합침
-- EQUI-JOIN(=동등 조인=>일반적인 조인방법)
SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid;

-- 위에서 합친 테이블을 가상테이블로 일단 만들었음
SELECT i.name, i.price, i.quantity, i.content, mp.* FROM item i, ( SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid)mp WHERE i.code = mp.code;

-- 위에있는건 계속 쓸 수 없기때문에 view를 만들었음
-- view는 재활용이 가능하니깐 위의 가상테이블은 재활용 불가능함
-- 이렇게 긴 코드를 해석하는 방법은 안쪽부터 해석하고 밖을 해석
-- INNER JOIN
CREATE OR REPLACE VIEW purchaseview AS
SELECT i.name, i.price, i.quantity, i.content, mp.* FROM item i, ( SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid)mp WHERE i.code = mp.code;

SELECT pv.* FROM purchaseview pv WHERE cnt >= 5 ORDER BY regdate DESC;


