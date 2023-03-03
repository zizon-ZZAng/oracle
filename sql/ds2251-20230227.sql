-- 콘솔에 출력 환경 변경
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('hello world' || '!!');  -- System.out.println("hello world" + "!!");
END;
/   -- 


-- 변수선언
DECLARE
    t_str VARCHAR2(20) := 'aaa';    -- String t_str = "aaa"
    t_num NUMBER(4) := 1234;        -- int t_num = 1234
BEGIN
    DBMS_OUTPUT.PUT_LINE(t_str || ',' || t_num); --Sysout("aaa"+","+1234);
END;
/


-- 조건문
DECLARE
    t_score NUMBER(3) := 97;
    t_grade VARCHAR2(2);
BEGIN
    IF t_score >=90 AND t_score<100 THEN    -- if(t_score>=90 && t_score<100)
        t_grade := 'A';
    ELSIF t_score >=80 THEN                 -- else if()
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
    t_num NUMBER := 3;
BEGIN
    FOR i IN 1..9 LOOP
        DBMS_OUTPUT.PUT_LINE( t_num || '*' || i || '=' || t_num*i );
    END LOOP;
END;
/


-- 홀수 짝수 반복문
DECLARE
    t_num NUMBER := 10;
BEGIN
    FOR i IN 1..t_num LOOP
        IF MOD(i,2) = 0 THEN
            DBMS_OUTPUT.PUT_LINE( i || '짝수');
        ELSE
            DBMS_OUTPUT.PUT_LINE( i || '홀수');
        END IF;
    END LOOP;
END;
/


-- 오라클 내장함수 실행 방법
SELECT MOD(15,2) FROM DUAL;     -- MOD 나머지
SELECT CURRENT_DATE FROM DUAL;  -- CURRENT_DATE 현재날짜

SELECT m.* FROM member m;

-- 20개 데이터 추가
DECLARE
    t_num NUMBER(2) := 20;
BEGIN
    FOR i IN 1..t_num LOOP
        INSERT INTO member( userid, userpw, username, userage, userphone, usergender, userdate )
        VALUES('a' || i, 'pw', 'name', 11+i, '010', 'M', CURRENT_DATE);
    END LOOP;
    COMMIT;
    
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
END;
/


-- 조회하기
DECLARE
BEGIN
    FOR tmp IN (SELECT m.* FROM member m ORDER BY userid ASC) LOOP
        DBMS_OUTPUT.PUT_LINE(tmp.userid || tmp.username);
    END LOOP;
END;
/


-- cursor를 이용한 조회
DECLARE
    CURSOR cur IS SELECT m.* FROM member m ORDER BY userid ASC;
BEGIN
    FOR mem IN cur() LOOP
        DBMS_OUTPUT.PUT_LINE(mem.userid || ' ' || mem.username);
    END LOOP;
END;
/


-- 함수만들기
-- public String funcToCharToday() { }
CREATE OR REPLACE FUNCTION func_tochar_today RETURN VARCHAR2
IS
    t_date VARCHAR2(30);
BEGIN
    SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') INTO t_date FROM DUAL;
    RETURN t_date;
EXCEPTION WHEN OTHERS THEN
    RETURN '';
END;
/

SELECT func_tochar_today FROM DUAL;


-- seq_item_code 시퀀스 현재 숫자 가져오는 함수
-- public String funcToCharToday() { }
CREATE OR REPLACE FUNCTION func_seq_item_code_nextval RETURN NUMBER
IS
BEGIN
    RETURN seq_item_code.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


SELECT i.* FROM item i;

-- 일괄추가 : 시퀀스를 사용하면 같은 숫자가 입력되어서 오류남.
INSERT ALL
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'품명',123,345,'내용',CURRENT_DATE)
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'품명',123,345,'내용',CURRENT_DATE)
    INTO item(code, name, price, quantity, content, regdate)
        VALUES( FUNC_SEQ_ITEM_CODE_NEXTVAL ,'품명',123,345,'내용',CURRENT_DATE)
SELECT * FROM DUAL;


-- 시퀀스 사용 안하는 경우에는 일괄 추가 가능
INSERT ALL
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b1', 'pw1', 'bname', 24, '011', 'F', CURRENT_DATE)
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b2', 'pw2', 'bname', 24, '011', 'F', CURRENT_DATE)
    INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
        VALUES( 'b3', 'pw3', 'bname', 24, '011', 'F', CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;


SELECT P.* FROM purchase p;

-- EXTRACT(파라미터1) CURRENT_TIMESTAMP
SELECT CURRENT_TIMESTAMP FROM DUAL;
SELECT EXTRACT(MINUTE FROM CURRENT_TIMESTAMP) FROM DUAL;
SELECT EXTRACT(HOUR FROM CURRENT_TIMESTAMP) FROM DUAL;
SELECT EXTRACT(DAY FROM CURRENT_TIMESTAMP) FROM DUAL;


-- 시간대별 판매수량을 반환하는 함수(숫자형의 in_hour 파라미터)
CREATE OR REPLACE FUNCTION func_purchase_group_hour(in_hour NUMBER) RETURN NUMBER
IS
    tmp_total NUMBER := 0;
BEGIN
    SELECT SUM(p.cnt) INTO tmp_total FROM purchase p WHERE EXTRACT(HOUR FROM p.regdate) = in_hour
    GROUP BY EXTRACT(HOUR FROM p.regdate);
    RETURN tmp_total;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


-- 함수 테스트
SELECT func_purchase_group_hour(14) FROM DUAL;


-- item 테이블의 재고수량을 반환하는 함수(func_item_group_quantity)
CREATE OR REPLACE FUNCTION func_item_group_quantity (in_code NUMBER) RETURN NUMBER
IS
    tmp_quan NUMBER :=0;
BEGIN
    SELECT i.quantity INTO tmp_quan FROM item i WHERE i.code = in_code;
    -- GROUP BY i.code;
    RETURN tmp_quan;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/

-- 함수 테스트 물품번호가 1인 재고수량이 출력됨.
SELECT func_item_group_quantity(3) FROM DUAL;

--=============================================================================

-- 회원관련 테이블 생성
CREATE TABLE membertest(
    username VARCHAR2(30),
    userpassword VARCHAR2(20),
    userid NUMBER(10),
    userage NUMBER(10),
    userphone VARCHAR2(20),
    userdate 
);

SELECT m.* FROM membertest m;
COMMIT;
rollback;

-- 회원등록 (PL/SQL을 이용한 회원 5명 등록)
INSERT INTO membertest(username, userpassword, userid, userage, userphone, userdate)
VALUES('이름5','비밀번호5',, 15,'010-0000-0005',CURRENT_DATE);



-- 회원수정
UPDATE membertest
SET userid = '아이디5'
WHERE username = '이름5';


-- 회원탈퇴(Update를 이용한 데이터 변경)
UPDATE membertest
SET username = NULL, userpassword = NULL, userage=null, userphone=NULL, userdate=NULL
WHERE username = '이름4';


-- 회원 이름에 검색어가 포함된 항목 조회
SELECT *
FROM membertest
WHERE username LIKE '%' || '름' || '%';


-- 등록시간에서 분이 일치하는 항목 회원 조회
SELECT *
FROM membertest
WHERE TO_CHAR(userdate,'MI') = 9;


-- 회원 아이디를 3자리까지만 표시하여 조회
SELECT SUBSTR(userid,1,3) AS userid3
FROM membertest;


-- 문자를 입력받으면 3자리만 반환하는 함수 생성 (함수명 : FUNC_STR, 내부함수 SUBSTR 사용)
-- FUNC_STR을 이용하여 회원 아이디를 3자리까지만 표시하여 조회 ex) a12345 => a12
CREATE OR REPLACE FUNCTION FUNC_STR (in_userid VARCHAR2) RETURN VARCHAR2
IS
    tmp_userid VARCHAR2(20) := NULL;
BEGIN
    SELECT SUBSTR(in_userid,1,3) INTO tmp_userid FROM DUAL;
    RETURN tmp_userid;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/


SELECT FUNC_STR(userid) FROM membertest;


-- 교수님 버전 ===============================================================
CREATE OR REPLACE FUNCTION func_str( in_str VARCHAR2 ) RETURN VARCHAR2
IS
    tmp_str VARCHAR2(6);
BEGIN
    SELECT SUBSTR( in_str, 1, 3) INTO tmp_str FROM DUAL;
    RETURN tmp_str;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

SELECT func_str('12345')  FROM DUAL;
SELECT m.*, func_str(m.userid) FROM member m;
--===========================================================================




-- 검색어와 페이지를 전달하면 검색어에 해당하는 회원만 조회(페이지당 5개씩)
CREATE OR REPLACE FUNCTION FUNC_STR5 (in_search VARCHAR2, in_page NUMBER) RETURN VARCHAR2
IS
    tmp_userid VARCHAR2(20) := NULL;
BEGIN
    SELECT userid INTO tmp_userid FROM membertest WHERE username LIKE '%' || in_search || '%';
    RETURN tmp_userid;
EXCEPTION WHEN OTHERS THEN
    RETURN NULL;
END;
/

SELECT FUNC_STR5('름',5) FROM membertest;


-- 정답 =========================== 함수로는 만들 수 없음!
SELECT m.*, ROW_NUMBER() OVER (ORDER BY userid ASC) ROWN  FROM membertest m WHERE m.userid LIKE '%' || '이' || '%' ;

SELECT * FROM (
SELECT m.*, ROW_NUMBER() OVER (ORDER BY userid ASC) ROWN  FROM membertest m WHERE m.userid LIKE '%' || '이' || '%'
) WHERE rown BETWEEN 1 AND 5;



