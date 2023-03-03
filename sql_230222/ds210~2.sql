
--전체조회
SELECT m.* FROM member m;   --회원
SELECT i.* FROM item i;     --물품
SELECT p.* FROM purchase p; --구매

SELECT pv.* FROM purchaseview pv; --회원+물품+구매 view

INSERT INTO member(userid, userpw, username,userage,userphone,usergender,userdate) VALues('k','k','회원2',18,'010-0000-0008','F',CURRENT_DATE);
INSERT INTO member(userid, userpw, username,userage,userphone,usergender,userdate) VALues('l','l','회원3',31,'010-0000-0009','F',CURRENT_DATE);

INSERT INTO item(code, name,price,quantity,content,regdate) VALUES(seq_item_code.NEXTVAL, '양념치킨', 22000, 10, '맛있음', CURRENT_DATE);

INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 77, CURRENT_DATE, 23 ,'k' );


COMMIT;
ROLLBACK;



-- 문제1) 주문금액을 컬럼추가(total)
SELECT pv.*, TO_CHAR(price*cnt, '999,999,999') total FROM purchaseview pv;


-- 문제2) 남은수량 컬럼추가(quantitycnt)
SELECT pv.*, (quantity-cnt) quantitycnt FROM purchaseview pv;


-- 문제3) 연령대별 컬럼추가 (age) 0~9 => 0, 10~19 =>1, 20~29 => 2

SELECT 
    pv.*,
    FLOOR(userage/10) age
FROM 
    purchaseview pv;

SELECT 
    pv.*,
    CASE
    WHEN(pv.userage >= 0 and pv.userage < 10)  THEN 0
    WHEN(pv.userage >= 10 and pv.userage < 20)  THEN 1
    WHEN(pv.userage >= 20 and pv.userage < 30)  THEN 2
    WHEN(pv.userage >= 30 and pv.userage < 40)  THEN 3
    WHEN(pv.userage >= 40 and pv.userage < 50)  THEN 4
    WHEN(pv.userage >= 50 and pv.userage < 60)  THEN 5
    WHEN(pv.userage >= 60 and pv.userage < 70)  THEN 6
    WHEN(pv.userage >= 70 and pv.userage < 80)  THEN 7
    WHEN(pv.userage >= 80 and pv.userage < 90)  THEN 8
    ELSE 9
    END age
FROM 
    purchaseview pv;
    

--purchaseview1 VIEW 생성
CREATE OR REPLACE VIEW purchaseview1 AS
SELECT 
    pv.*,
    pv.price*pv.cnt total, 
    (pv.quantity - pv.cnt) quantitycnt,
    FLOOR(userage/10) age
FROM 
    purchaseview pv;
    
--전체조회    
SELECT pr1.* FROM purchaseview1 pr1;


--문제1) 연령대별 주문수량 합계
SELECT 
    pr1.age,
    SUM(pr1.cnt) sum 
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.age
ORDER BY 
    pr1.age ASC;


--문제2) 상품별 주문수량
SELECT 
    pr1.code,
    pr1.name,
    pr1.price,
    SUM(pr1.cnt) csum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.code, 
    pr1.name,
    pr1.price
ORDER BY 
    pr1.code ASC;


--문제3) 성별 주문횟수, 주문금액합계
--count는 라인수를 말하는 것

SELECT 
    pr1.usergender,
    COUNT(*) cnt,
    TO_CHAR(sum(pr1.total),'999,999,999' ) psum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.usergender;


--문제4) 시간대별 주문수량 0시 1시 2시 23시

SELECT
    TO_CHAR(pr1.regdate, 'HH24') regdate,
    SUM(pr1.cnt) sum
FROM 
    purchaseview1 pr1
GROUP BY 
    TO_CHAR(pr1.regdate, 'HH24');


--문제5) 주문수량이 2개 이상인 주문의 연령대별 주문수량, 평균 구매금액

SELECT 
    pr1.age,
    SUM(pr1.cnt) cnt,
    AVG(pr1.total) avg
FROM 
    purchaseview1 pr1
WHERE
    pr1.cnt >= 2
GROUP BY 
    pr1.age
ORDER BY 
    pr1.age ASC;


--문제6) 상품별 주문수량 개수가 3개 이상인 것 주문수량합계
SELECT 
    pr1.code,
    pr1.name,
    sum(pr1.cnt) quantitysum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.code,
    pr1.name
HAVING 
    SUM(cnt) >=3
ORDER BY
    pr1.code ASC;


    

