-- 확인용
SELECT r.* FROM restaurant r ORDER BY phone ASC;

-- 식당등록
INSERT INTO restaurant(phone, name, address, password)
    VALUES('051-000-1000','일식','부산해운대구','d');
COMMIT;


-- 식당정보변경(식당이름, 주소)
UPDATE restaurant r SET r.name='이름변경', r.address='주소변경' 
WHERE r.phone='051-110-1236';


-- 식당비밀번호변경(변경할 비밀번호) + 조건 : 기본키, 현재암호 일치할 경우 비밀번호 변경 가능
UPDATE restaurant SET password = '비밀번호변경'
WHERE password='연어덮밥맛집' AND phone='051-110-1236';


-- 식당 1개 조회(기본키)
SELECT r.* FROM restaurant r WHERE phone='051-110-1200'; 


-- 식당 로그인(기본키, 암호) => 식당정보 가져오기
SELECT r.* FROM restaurant r WHERE phone='051-000-1234' AND password ='b';

--===========================================================================
-- 제약조건 : no시퀀스사용, phone은 식당에 있는 것만
-- 외래키가 있는 것은 로그인 해서 해야함.
-- 식당이 로그인되어서 연락처를 알 수 있는 상황이 되어야 메뉴 등록이 가능.
SELECT m.* FROM menu m ORDER BY no ASC;
commit;
rollback;

-- 1. 메뉴등록
INSERT INTO menu(no, name, price, content, regdate, phone)
    values(seq_menu_no.NEXTVAL, '스시세트', 13000, '점심특선', CURRENT_DATE,'051-110-1200');
    

-- 2. 메뉴 변경
UPDATE menu SET name='메뉴변경', price=3000, content='변경변경변경'
WHERE no = 1032 AND phone='051-000-1234';


-- 3. 메뉴 삭제
DELETE FROM menu WHERE no = 1032 AND phone = '051-000-1234';


-- 4. 해당 식당의 전체 메뉴 조회(가격에 3자리 콤마 추가, 등록일은 년월일만 표시)
SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999') price, 
TO_CHAR(m.regdate,'YYYY-MM-DD') regdate
FROM menu m 
WHERE phone = '051-110-1234'
ORDER BY m.no ASC;


-- 5. 할인률 0.3을 전달하면 4번 조회항목에서 discountPrice컬럼이 추가된 메뉴 조회
-- discountPrice는 소수점이 있으면 버림으로 표시
CREATE OR REPLACE VIEW discountmenu AS

SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999') price, 
TO_CHAR(m.regdate,'YYYY-MM-DD') regdate, TO_CHAR(TRUNC(m.price*(1-0.3)),'FM999,999') discountPrice
FROM menu m 
WHERE phone = '051-110-1234'
ORDER BY m.no ASC;


SELECT c.* FROM customer c;


-- 고객 회원가입
INSERT INTO customer(email, password, phone, address, chk)
    VALUES('DJSKFJ10@naver.com', '나는암호입니다.', '010-0000-0001','주소입니다.',1);
COMMIT;


-- 고객 로그인
SELECT c.*
FROM customer c
WHERE c.email = 'DJSKFJ10@naver.com' AND c.password = '나는암호입니다.';


-- 고객 회원정보수정
UPDATE customer SET phone = '011-0000-0001', address = '조소변경했음'
WHERE email = 'DJSKFJ10@naver.com' AND password = '나는암호입니다.';


-- 고객 암호 변경
UPDATE customer SET password = '암호를변경하겠어요.'
WHERE email = 'DJSKFJ10@naver.com' AND password = '나는암호입니다.'; 

COMMIT;
