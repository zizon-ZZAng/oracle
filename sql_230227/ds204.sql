






-- 콘솔에 출력환경 변경
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO WORLD'||'!!'); --SYSO (DJwJRN+DJwJRN)
END;
/ --위에거랑 밑에꺼 실행구분선 (안하면 다출력됨)

--변수선언
DECLARE
    t_str VARCHAR2(20):= 'AAA';
    t_num NUMBER(4):= 1234;
BEGIN
    DBMS_OUTPUT.PUT_LINE(t_str || ',' || t_num); 
END;
/

--조건문
DECLARE
    t_score NUMBER(3) := 67;
    t_grade VARCHAR(2);
BEGIN
    IF t_score >=90 AND t_score <=98 THEN -- if(t_score >= 90 && t_score <=98)
        t_grade := 'A'; -- else if () 
    ELSIF t_score >=80 THEN 
        t_grade := 'B';
    ELSIF t_score >=70 THEN
        t_grade := 'C';
    ELSE 
        t_grade := 'D';
    END IF;
    DBMS_OUTPUT.PUT_LINE(t_grade || '등급');
END;
/

-- 반복문
DECLARE
    t_num NUMBER :=3;
BEGIN
    FOR i IN 1..9 LOOP  -- for(int i=1, i<=9; i++){
        DBMS_OUTPUT.PUT_LINE(t_num || '*' || i || '=' || (t_num*i));
    END LOOP;
END;
/
--PL/SQL 종료선언 ('/' 뒤에 주석문 넣으니 오류남)


-- 홀수 짝수 반복문
DECLARE
    t_num NUMBER :=10;
BEGIN
    FOR i IN 1..t_num LOOP  -- for(int i=1, i<=9; i++){
        IF MOD(i, 2) = 0 THEN -- if ( i%2 ==0 ) {  //MOD 오라클 내장함수 나머지구하기
            DBMS_OUTPUT.PUT_LINE(i || '짝수'); 
        ELSE
            DBMS_OUTPUT.PUT_LINE(i || '홀수');
        END IF; --IF 가정문 종결
    END LOOP; --FOR 반복문 종결
END;
/

--오라클 내장함수 실행방법
SELECT MOD(15,2) FROM DUAL; -- MOD나머지
SELECT CURRENT_DATE FROM DUAL; --CURRENT_DATE현재날짜
-- 이건 '/' 필요없넹



----------------------------



SELECT m.* FROM member m;

DECLARE
    t_mum NUMBER (2) := 25;  
BEGIN
    FOR i IN 1..t_mum LOOP
        INSERT INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('a' || i, 'pw', 'name', 11+i, '010', CURRENT_DATE, 'M');
    END LOOP;
    COMMIT;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;
/
-- 수정이나 추가에는 EXCEPTION WHEN OTHERS THEN ROLLBACK; 쓰지만 조회에는 굳이 쓸필요 없음

DECLARE
BEGIN
    FOR tmp IN(SELECT m.* FROM member m ORDER BY userid  ASC) LOOP 
        DBMS_OUTPUT.PUT_LINE(tmp.userid || tmp.username);
    END LOOP;
END;
/

-- 조회하기 : cursor를 이용
DECLARE
    CURSOR cur IS SELECT m.* FROM member m ORDER BY userid ASC;
BEGIN
    FOR mem IN cur() LOOP
        DBMS_OUTPUT.PUT_LINE(mem.userid || mem.username);
    END LOOP;
END;
/

---------------------------------------

--함수만들기
--public string func() {
CREATE OR REPLACE FUNCTION func_tochar_today RETURN VARCHAR2
IS
    t_date VARCHAR2(30); 
BEGIN
    -- 내장함수 => CURRENT_DATE, TO_CHAR(변경날짜,포멧)
    SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') INTO t_date FROM DUAL;
    RETURN t_date;
EXCEPTION WHEN OTHERS THEN
    RETURN '';
END;
/

SELECT func_tochar_today FROM DUAL;


--일괄추가
SELECT m.* FROM member m;

INSERT ALL
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B', 'pw', 'name', 11, '010--', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B1', 'pw1', 'name1', 11, '010---', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B2', 'pw2', 'name2', 11, '010----', CURRENT_DATE, 'F')
INTO member( userid, userpw, username, userage, userphone, userdate, usergender)
        VALUES('B3', 'pw3', 'name3', 12, '010-----', CURRENT_DATE, 'F')
SELECT * FROM DUAL;
COMMIT;

-- 시퀀스(seq_item_code)의 현재숫자를 가져오는 함수를 만들어보자
CREATE OR REPLACE FUNCTION func_seq_item_code_naxtval RETURN NUMBER
IS
BEGIN
    RETURN seq_item_no.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--시간대별 판매수량을 반환화는 함수를 만들어보자
CREATE OR REPLACE FUNCTION func_purchase_group_hour(in_hour NUMBER) RETURN NUMBER
IS
    tmp_total NUMBER := 0;
BEGIN
    SELECT SUM(p.cnt) INTO tmp_total FROM purchase p WHERE EXTRACT(HOUR FROM p.regdate) = in_hour
    GROUP BY EXTRACT(HOUR FROM p.regdate) ;
    RETURN tmp_total;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

-- 함수 테스트

SELECT func_purchase_group_hour(14) FROM DUAL;





--일괄추가
INSERT ALL
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, '품명', 123, 456, '내용------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, '품명', 123, 456, '내용------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, '품명', 123, 456, '내용------', CURRENT_DATE )
INTO item (code, name, price, quantity, content, regdate )
    VALUES ( FUNC_SEQ_ITEM_CODE_NAXTVAL, '품명', 123, 456, '내용------', CURRENT_DATE )
SELECT * FROM DUAL;


SELECT m.* FROM member m;
SELECT i.* FROM item i;
SELECT i.* FROM item i;
SELECT p.* FROM purchase p;

-- EXTRACT(파라미터1) CURRNET_TIMESTEMP
SELECT EXTRACT(MINUTE FROM CURRENT_TIMESTAMP)FROM DUAL;

-- item 테이블의 물품번호별 재고수량을 반환하는 함수(func_item_group_quantity)
CREATE OR REPLACE FUNCTION func_item_group_quantity(in_code NUMBER) RETURN NUMBER
IS
    tmp_quantity NUMBER:= 0;
BEGIN
    SELECT quantity INTO tmp_quantity FROM item WHERE item.code = in_code;
    RETURN tmp_quantity;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--함수 테스트 물품번호가 1인 재고수량 출력됨


commit;

------------

--회원관련 테이블 생성
CREATE TABLE nnn(
id VARCHAR2(20) CONSTRAINT PK_nnn PRIMARY KEY , 
name VARCHAR2 (20), 
pw VARCHAR2(200), 
age NUMBER(3), 
mdate TIMESTAMP
);
--DROP TABLE nnn; -- nnn지우고 새로 올리기
DROP TABLE nnn;
--회원등록 (PL/SQL을 이용한 회원 5명 등록)
INSERT ALL
INTO nnn( id, pw, name, age, mdate)
        VALUES('B432', 'pw4', 'name4', 13, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B4D5', 'pw5', 'name5', 24, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B23G', 'pw6', 'name6', 25, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B895D', 'pw7', 'name7', 18, CURRENT_DATE)
INTO nnn( id, pw, name, age, mdate)
        VALUES('B3', 'pw0', 'name8', 21, CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;
--PL/SQL을 이용한 회원 5명 등록(DKDKKDKDKKK)(교수님 DID)
DECLARE
BEGIN
    FOR i IN 1..5 LOOP
        INSERT INTO nnn( id, name, pw, age, userdate )
        VALUES( 'B' || i  ,'name'|| i, 'pw' || i, 11+i , CURRENT_DATE);   
    END LOOP;
    COMMIT;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;    
END;
/
COMMIT;

SELECT * FROM nnn;

--회원수정
UPDATE nnn
set name = 'A', age= 15
WHERE id = 'B';

--회원탈퇴(UPDATE를 이용한 데이터 변경)
UPDATE nnn
set name = '', pw = '', age= 0, mdate=''
WHERE id = 'B4';

--회원 1명 조회 (암호와 가입일자는 조회시 제거)
SELECT n.id, n.age, n.name FROM nnn n WHERE n.id = 'B1';

--회원이름에 검색어가 포함된 항목조회
SELECT n.* FROM nnn n WHERE n.name like '%'||'1'||'%';
COMMIT;

--등록시간항목에서 분이 일치하는 회원조회
SELECT n.* FROM nnn n WHERE TO_CHAR(n.mdate,'MI') = 15;

--문자를 입력받으면 3자리만 반환하는 함수생성( 함수명: FUNC_STR, 내부함수 : SUBSTR)
CREATE OR REPLACE FUNCTION FUNC_STR(in_id VARCHAR2) RETURN VARCHAR2
IS
    tmp_STR VARCHAR2(20) := '';
BEGIN
   SELECT SUBSTR(in_id, 1, 3) INTO tmp_STR FROM nnn WHERE nnn.id = in_id ;
   RETURN tmp_STR;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

--FUNC_STR을 이용하여 회원아이디를 3자리까지만 표시하여 조회 EX) A1234 => A12
SELECT nnn.name, nnn.id, FUNC_STR(nnn.id) FROM nnn;
SELECT * FROM nnn;

--검색어와 페이지를 전달하면 검색어에 해당하는 회원만 조회(페이지당 5씩)
SELECT * FROM (
    SELECT ROWNUM AS RNUM, n.* FROM nnn n WHERE n.name LIKE '%n%'
)WHERE RNUM BETWEEN 10 AND 15;
--교수님did
SELECT * FROM (
    SELECT n.*, ROW_NUMBER() OVER (ORDER BY id ASC) ROWN FROM nnn n WHERE n.id LIKE '%' || 'B' || '%'
) WHERE ROWN BETWEEN 4 AND 10;


