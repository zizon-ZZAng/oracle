-- 강의실, 부서
SELECT c.* FROM CLASSROOM c;
SELECT c.* FROM CLASSROOM1 c;

-- 학생, 사원
SELECT s.* FROM STUDENT s;
SELECT s.* FROM STUDENT1 s;


-- 1. 테이블 생성 CLASSROOM1
-- CODE CHAR(1) PK, ROOM VARCHAR2(20)


CREATE TABLE CLASSROOM1(
    code CHAR(1) PRIMARY KEY,
    room VARCHAR2(20)
);


-- 2. 테이블에 자료 추가
-- CODE A,B,C,D // ROOM 301호, 302호, 303호, 304호
INSERT ALL INTO CLASSROOM1(code,room)
VALUES ( 'A', '301호')
INTO CLASSROOM1(code,room)
VALUES ( 'B', '302호')
INTO CLASSROOM1(code,room)
VALUES ( 'C', '303호')
INTO CLASSROOM1(code,room)
VALUES ( 'D', '304호')
SELECT*FROM DUAL;
COMMIT;


-- 3. 학생테이블 생성 STUDENT1
-- NO NUMBER PK
-- NAME VARCHAR2(20) NOT NULL
-- CODE CHAR(1)
-- KOR, ENG, MATH NUMBER(3)
-- GRADE VARCHAR2(10) 1학년, 2학년, 3학년, 4학년만 가능


CREATE TABLE STUDENT1(
    no NUMBER PRIMARY KEY, 
    name VARCHAR2(20) NOT NULL,
    code CHAR(1),
    kor NUMBER(3),
    eng NUMBER(3),
    math NUMBER(3),
    grade VARCHAR2(10)
);

ALTER TABLE student1 ADD CONSTRAINT CHK_student1_grade  
    CHECK (grade IN ('1학년','2학년','3학년','4학년'));   
    
    
-- 4. 학생테이블의 CODE는 강의실 CODE의 외래키 제약조건 (제약조건명 : fk_student1_code)
ALTER TABLE student1
  ADD CONSTRAINT fk_student1_code
    FOREIGN KEY (code)
    REFERENCES  classroom1(code);
    
    
-- 5. 시퀀스 생성 seq_student1_no 시작값101 증가값1
CREATE SEQUENCE seq_student1_no
INCREMENT BY 1
START WITH 101 NOMAXVALUE NOCACHE;


-- 6. 시퀀스를 이용한 학생 5명 추가
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'이름1','A',30,30,30,'1학년');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'이름2','B',50,60,70,'2학년');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'이름3','C',60,70,70,'3학년');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'이름4','D',70,80,90,'4학년');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'이름5','D',90,90,100,'4학년');
COMMIT;


-- 7. 학생들의 정보 조회, 각 학생의 총합이 250~300 A, 200~249 B, 나머지는 C (학번, 이름, 국어, 영어, 수학, 총합)
DECLARE
    total_grade CHAR(1):= null;
BEGIN
    SELECT no, name, code, kor, eng, math, total = kor+eng+math 
    FROM student1
    GROUP BY no;
    
    IF total >= 250  AND total <= 300 THEN
        total_grade := 'A';
    ELSIF total >= 200 AND total <= 249 THEN
        total_grade := 'B';
    ELSE
        total_grade := 'C';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(total_grade);
END;
/


SELECT s1.*, (kor+eng+math) total,
    CASE
        when( (kor+eng+math) >= 250  AND (kor+eng+math) <= 300) THEN 'A'
        when( (kor+eng+math) >= 200  AND (kor+eng+math) <= 249) THEN 'B'
        ELSE 'C'
    END total_grade
FROM student1 s1;



-- 8. 학년별 수학점수 합계 구하기
SELECT grade, SUM(math) 
FROM student1
GROUP BY grade ORDER BY grade ASC;


-- 삭제, 수정
DELETE FROM student1 WHERE no=106;
rollback;

UPDATE student1 SET name = '변경이름' WHERE no = 106;
select * from student1;
commit;


-- 9. 함수생성, 시퀀스번호를 반환하는 함수 생성하기 FUNC_NEXTVAL
CREATE OR REPLACE FUNCTION func_nextval RETURN NUMBER
IS
BEGIN
    RETURN seq_student1_no.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

SELECT func_nextval FROM DUAL;


--=======================================================================================
-- 업데이트 시킬 때 전부 다 말고 하나씩 업데이트 시킬 때...? 자바에서 이프문 써서 업데이트 시키면 됨.
select * from member;

UPDATE member SET username='a', userage=111, userphone='010', usergender = 'M' WHERE userid='a';
UPDATE member SET username='a', userage=111, userphone='010' WHERE userid='a';
UPDATE member SET username='a', userage=111 WHERE userid='a';
UPDATE member SET username='a' WHERE userid='a';
COMMIT;


-- 테이블명이나 컬럼명도 변수로 할 수 있음.(자바에서)
SELECT m.* FROM member m WHERE userid LIKE '%' || 'a' || '%';
SELECT m.* FROM member m WHERE username LIKE '%' || 'a' || '%';
SELECT m.* FROM member m WHERE userpw LIKE '%' || 'a' || '%';




