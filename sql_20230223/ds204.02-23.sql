SELECT r.* FROM restaurant r ORDER BY retaurantphone ASC;
COMMIT;

INSERT INTO restaurant(retaurantphone, name, address, password)
    VALUES('051-4561-7891', 'SNRN', 'DJEL', 'QWERTYU'); 
    
--식당정보 변경(식당이름이랑 주소를 바꿀수 있음) + 기본키(phone)
UPDATE restaurant 
SET name = '기장해물탕', address = '기장군' 
WHERE retaurantphone = '051-4561-7891';

--식당비밀번호변경(변경할 비번)+ (기본키+현재암호)
UPDATE restaurant 
SET password = '5674'
WHERE retaurantphone ='051-4895-6123' AND password ='*****';

--식당로그인 (기본키, 암호) => 식당정보가져오기
SELECT r.* FROM restaurant r 
WHERE retaurantphone = '051-4895-6123' AND password = '5674';

--식당1개조회(기본키)
SELECT r.* FROM restaurant r 
WHERE retaurantphone = '051-4895-6123';

----------------------
--제약조건 : no시퀀스 사용 retaurantphone은 식당에 있는것만
--식당이 로그인되어 식당연락처 알때만 메뉴등록이 가능
SELECT m.* FROM menu m ORDER BY menuno ASC;
COMMIT;

--1. 메뉴등록
INSERT INTO MENU(menuno, name, price, content, retaurantphone)
VALUES(seq_menu_no.NEXTVAL, '조개탕', 45000, '맛있고신선해요', '051-4561-7891');

--2. 메뉴변경
UPDATE MENU 
SET name = '원조밀면', price = 5000, content = '맛있음'
WHERE menuno = 1025 AND retaurantphone = '051-002-3926';

--3. 메뉴삭제
DELETE FROM MENU WHERE menuno =1026 AND retaurantphone = '051-002-3926';

--4. 해당식당의 메뉴 전체조회(가격 3자리마다 ,찍기, 등록일은 년월일만)
SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, '999,999,999') strPrice
FROM MENU m 
WHERE m.retaurantphone = '051-153-3226' ORDER BY menuno ASC;

--5. 할인률 0.3을 전달하면 4번 조회항목에 할인가격~컬럼을 추가하고 조회
-- 할인가격에는 소숫점이 있으면 버림으로 표시
SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, '999,999,999') strPrice, FLOOR(m.price-(m.price* 0.3)) disPrice  
FROM MENU m WHERE m.retaurantphone = '051-153-3226' ORDER BY menuno ASC;

-----------------------------------------------------------------------
SELECT c.* FROM customer c;
COMMIT; 

--고객 회원가입
INSERT INTO customer(email, password, phone, address, chk)
VALUES('2wuiw', 'pw', '010-1230-9562', 'wheee', 1);

--고객 로그인
SELECT c.* FROM customer c
WHERE email = '2wuiw' AND password = 'pw' ;

--고객 회원정보수정
UPDATE customer
SET phone= '010-5698-1234', address= '사하구'
WHERE email = '2wuiw' AND password = 'pw' ;

--고객 암호변경
UPDATE customer
SET password = 'pEW' 
WHERE email = '2wuiw' AND password = 'pw' ;

--고객 삭제
DELETE FROM customer WHERE email = 'eret@poii.com';

--고객 삭제 업데이트 이용
UPDATE customer



