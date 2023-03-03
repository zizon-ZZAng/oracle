SET SERVEROUTPUT ON;

-- 프로시저 : 자주사용하는 SQL문 작성 -> 필요시 호출 -> 반환값이 없음.
-- 예시) 회원아이디가 있으면 UPDATE 없으면 INSERT 수행.
-- SELECT(아이디확인) -> INSERT OR UPDATE(둘 중 하나 수행)

-- 프로시저 구조
CREATE OR REPLACE PROCEDURE 프로시저명(
파라미터 IN, OUT 사용
)
IS
BEGIN

END;
/

-- 숫자를 받으면 등급을 젅달해주는 프로시저
CREATE OR REPLACE PROCEDURE proc_if_exam(
    in_num IN NUMBER --전달되는 파라미터 :숫자형 INNUM에 담김
)
IS
    tmp_grade VARCHAR(2); -- 등급보관용 문자변수
BEGIN
    IF in_num >=90 THEN
        tmp_grade := 'A';
    ELSIF in_num >=80 THEN
        tmp_grade := 'B';
    ELSE 
        tmp_grade := 'C';
    END IF;
    DBMS_OUTPUT.PUT_LINE('등급은 ' || tmp_grade || '입니다.');
END;
/
-- 프로시저 실행
EXEC proc_if_exam(90);
--
BEGIN
    --생성한 프로시저 호출
    EXEC proc_if_exam(90);
END;
------------------------


--회원가입 프로시저(SQL이 더 편하긴 함 걍 예시)
CREATE OR REPLACE PROCEDURE proc_nnn_insert(
    in_id IN NNN.ID%TYPE,
    in_name IN NNN.NAME%TYPE,
    in_pw IN NNN.PW%TYPE,
    in_age IN NNN.AGE%TYPE,
    out_ret OUT NUMBER -- 완료가 되면 성공과 실패를 확인. NUMBER. 0또는 1로 확인가능
)
IS
BEGIN
    INSERT INTO nnn (id, name, pw, age, mdate)
    VALUES(in_id, in_name, in_pw, in_age, CURRENT_DATE);
    COMMIT;
    out_ret := 1;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    out_ret := 0;
END;
/

-- 반환값이 없을때 프로시저 테스트
DECLARE 
    out_ret NUMBER(1) := -1; -- 프로시저 성공하면 1이 나온다. 왜냐하면 프로시저가 일을 수행했기 때문에
BEGIN
    --생성한 프로시저 호출
    proc_nnn_insert('D1','SBC1','PWPW1',35,out_ret);
    DBMS_OUTPUT.PUT_LINE('결과값은 ' || out_ret);
END;
/
COMMIT;
SELECT * FROM nnn;


-----------------------------
-- SELECT COUNT(*) FROM 테이블
--문제) 회원정보를 전달하면 회원아이디가 존재할 경우 이름 나이 연락처 성별을 업데이트
--존재하지 않으면 추가하기
--프로시저 명칭 proc_nnn_upsert

CREATE OR REPLACE PROCEDURE proc_nnn_upsert(
    in_id IN NNN.ID%TYPE,
    in_name IN NNN.NAME%TYPE,
    in_pw IN NNN.PW%TYPE,
    in_age IN NNN.AGE%TYPE,
    out_ret OUT NUMBER 
)
IS
 tmp_chk NUMBER(1) := 0; --존재유무 확인용 변수
BEGIN
        SELECT COUNT(*) INTO tmp_chk FROM nnn n WHERE n.id = in_id;
    IF tmp_chk = 1 THEN
        UPDATE nnn 
        SET name = in_name, age = in_age, pw =  WHERE id = in_id;
    ELSE 
        INSERT INTO nnn (id, name, pw, age, mdate)
        VALUES(in_id, in_name, in_pw, in_age, CURRENT_DATE);
    END IF;
    COMMIT;
    out_ret := 1;
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;
    out_ret := 0;
END;
/
----------------------------------------
--프로시저로 NNN조회
CREATE OR REPLACE PROCEDURE proc_nnn_select(
    in_name IN VARCHAR2,
    out_cursor OUT SYS_REFCURSOR
    )
IS
BEGIN
    OPEN out_cursor FOR
        SELECT n.* FROM nnn n WHERE n.name = in_name;
END;
/

--proc_nnn_select 프로시저 테스트
-- out_cursor: List<NNN>
-- out_cursor는 자바타입으로 바꾸기 어려움
SELECT * FROM NNN;

DECLARE
    in_name VARCHAR2(20) := 'name2';
    out_cursor SYS_REFCURSOR;
    -- nnn타입 생성(cursor타입은 출력하려면 객체가 필요
    TYPE nnntype IS RECORD ( 
    ID VARCHAR2(20),
    NAME VARCHAR2(20),
    PW VARCHAR2(200),
    AGE NUMBER(3),
    mdate TIMESTAMP
    );
    -- nnn 타입으로 변수생성 --LOMBOK
    nnnobj nnntype;
BEGIN
    --프로시저호출
    proc_nnn_select(in_name, out_cursor);    
    LOOP
        FETCH out_cursor INTO nnnobj;  -- for(nnntype nnnobj : out_cursor)
        EXIT WHEN out_cursor%NOTFOUND; -- 없을때까지 돌린다
        DBMS_OUTPUT.PUT_LINE(nnnobj.ID|| ' ' || nnnobj.NAME);
    END LOOP;
    CLOSE out_cursor;
END;
/

-------------------------
SELECT n.* FROM nnn n;

-- 시퀀스가 업는 경우

INSERT ALL
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A14','name10','pw10',17,CURRENT_DATE)
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A15','name11','pw11',17,CURRENT_DATE)
INTO nnn(id, name, pw, age, mdate)
    VALUES ('A16','name12','pw12',17,CURRENT_DATE)
SELECT * FROM DUAL;
COMMIT;

SELECT r.* FROM restaurant r;
-- 일괄적으로 5개 추가하기

INSERT ALL
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-8','name10','중구','etw17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-9','name11','중구','fef17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-10','name12','서구','dfs17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-11','name13','서구','dts17')
INTO restaurant(retaurantphone, name, address, password)
    VALUES ('010-12','name14','서구','dws17')
SELECT * FROM DUAL;
COMMIT;
------------------------
------------------------


-- 시퀀스가 있는경우

SELECT i.* FROM item i;

-- 시퀀스가 있으면 함수사용x

SELECT seq_item_no.NEXTVAL FROM DUAL;
INSERT INTO item(code, name, price, quantity, content, regdate)
SELECT seq_item_no.NEXTVAL code, T1.* FROM(  --T1은 뭐지?
SELECT  '이름1' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
--UNION ALL : 세로로 붙이는거
UNION ALL
SELECT  '이름2' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
UNION ALL
SELECT  '이름3' name, 1234 price, 4560 quantity, 'sodyd' content, CURRENT_DATE regdate FROM DUAL
) T1;
COMMIT;


--- 오늘 한거 nnnselect 부터 자바에서 오류뜸


