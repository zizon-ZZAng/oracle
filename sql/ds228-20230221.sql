--SELECT 컬럼명 FROM 테이블명 별칭;
SELECT i.*  FROM item i;
SELECT i.code, i.name, i.quantity FROM item i;
SELECT i.* FROM item i ORDER BY i.code DESC;
SELECT i.* FROM item i WHERE i.price >=10000 AND i.price <=20000 ORDER BY i.code DESC;




SELECT i.* FROM item i WHERE i.code in(1,3,15) ORDER BY i.code DESC;
SELECT i.* FROM item i WHERE i.code=1 OR i.code=3 OR i.code=15 ORDER BY i.code DESC;




--WHERE 컬럼명 LIKE '아무거나'|| '포함할것'|| '아무거나';
SELECT i.* FROM item i WHERE i.name LIKE '%' || '초' || '%';
SELECT i.*, i.price*i.quantity FROM item i WHERE i.name LIKE '%' || '초' || '%';



--회원테이블에서 나이가 10~30인 아이디를 오름차순해서 조회
select m.* from member m;
SELECT m.* FROM member m WHERE m.userage>=10 AND m.userage<=30 ORDER BY m.userid ASC;




--1.전체조회 (테이블의 개수와 상관없이)
--2. 조건(where)으로 필요한 필터 
--3. 원하는 정렬 기준으로 조회(order by)
--4. 가져온 데이터를 필요한 형태로 가공(to_char와 같은 내장함수)




--함수의 기능을 테스트할때 사용 SELECT * FROM 테이블
--절대값 함수
SELECT ABS(-2) FROM DUAL;
SELECT CURRENT_DATE FROM DUAL;



--포맷지정 함수 TO_CHAR
SELECT TO_CHAR(123456789,'999,999,999') FROM DUAL;
SELECT TO_CHAR(i.price*i.quantity,'999,999,999') total FROM item i WHERE i.name LIKE '%' || '초' || '%';
SELECT TO_CHAR(CURRENT_DATE,'DL HH:MI:SS') FROM DUAL;



SELECT m.* FROM member m;
SELECT m.* FROM member m WHERE m.userage >= 6 ORDER BY m.username ASC;

SELECT m.userid, m.username, m.userphone, m.userdate, m.userage, m.usergender,
    TO_CHAR(m.userdate, 'YYYY-DD-MM HH') userdate1 
    FROM member m WHERE m.userage >= 6 
    ORDER BY m.username ASC;
  
  
  
    
--8개 중 최댓값 1개 구하기
SELECT i.*, MAX(i.price) FROM item i ORDER BY i.code DESC; -- 오류남
SELECT MAX(i.price) FROM item i ORDER BY i.code DESC; --1개만 나옴

SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) FROM item i ORDER BY i.code DESC;




--select로 가공된 내역은 가상의 테이블
SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;

SELECT m.*, a.* FROM member m, memberaddr a WHERE m.userid = a.userid;


CREATE OR REPLACE VIEW memberaddrview AS
SELECT m.*, a.userno, a.useraddr, a.userpostcode
    FROM member m, memberaddr a WHERE m.userid = a.userid;


DELETE FROM memberaddr WHERE userno=1001;
COMMIT;

SELECT mav.* FROM memberaddrview mav;


--테이블 두개를 합치고 VIEW 만들ㄹ기
SELECT m.* FROM member m;
SELECT p.* FROM purchase p;

SELECT m.*, p.no, p.cnt, p.code, p.regdate FROM member m, purchase p
    WHERE m.userid = p.userid;


CREATE OR REPLACE VIEW purchaseview AS
SELECT i.name,i.price,i.quantity, i.content, mp.* FROM item i, 
(
    SELECT 
            m.userid, m.username, m.userage, m.userphone, m.usergender,
            p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p
        WHERE m.userid= p.userid
    ) mp WHERE  i.code= mp.code;
    
    
SELECT pv.* FROM purchaseview pv WHERE cnt>=100 ORDER BY regdate DESC;
    

--데이터 가공
 --가공된 데이터에 추가적으로 가공 불가능
SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i WHERE i.rown>=1 ORDER BY i.code DESC;





--가공한 데이터를 다시 가공할 때 가상의 테이블 생성
SELECT i1.* FROM(
    SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i ORDER BY i.code DESC )i1 WHERE i1.rown>=1 AND i1.rown<=5;
    
    
    

--view 생성하기( 복잡할때 사용)
--VIEW는 SELECT는 가능하지만 DELETE 불가능(원본(item)에서 지워야함)
--view는 데이터 보관정도밖에 안됨
CREATE OR REPLACE VIEW itemview AS
SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i ORDER BY i.code DESC;
    

SELECT iv.* FROM itemview iv WHERE iv.rown>=1 AND iv.rown<=5;




--무결성 제약조건(DS228.FK_ITEM_TO_PURCHASE)이 위배되었습니다- 자식 레코드가 발견되었습니다
DELETE FROM item i WHERE i.code = 3;






