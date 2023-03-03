
--학생테이블
-- 학번, 이름, 강의실, 국어, 영어, 수학, 등록일자
CREATE TABLE student (
    no      NUMBER PRIMARY KEY,
    name    VARCHAR2(20),
    class   CHAR(1),    --A,B,C (CHAR은 무조건 길이에 맞게 1,2,3  3으로 지정하고 A만 입력했을 땐 나머지 두자리 공백임 그니깐 잘보고해)
    kor     NUMBER(3),
    eng     NUMBER(3),
    math    NUMBER(3),
    regdate TIMESTAMP DEFAULT CURRENT_DATE
);

--전체조회
SELECT s.* FROM student s;

--학생테이블 no(번호) 시퀀스 생성 
CREATE SEQUENCE seq_student_no START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;

-- 등록
INSERT INTO student(no, name, class, kor, eng, math) VALUES(seq_student_no.NEXTVAL, '김철수철수', 'K', 100, 100, 100);


COMMIT; --적용시키기
ROLLBACK; --되돌리기

--------------------------------------------------------------------------------

--강의실 테이블
CREATE TABLE classroom (
    code    CHAR(1) PRIMARY KEY,    --A,B,C,D,E..등등
    room    VARCHAR2(10),
    teacher VARCHAR2(20),
    regdate TIMESTAMP DEFAULT CURRENT_DATE
);

--전체조회
SELECT c.* FROM classroom c;

COMMIT;
ROLLBACK;

--등록
-- 하나하나 등록하고 commit하고 
INSERT INTO classroom(code, room, teacher) VALUES('A','201호','교사1');
INSERT INTO classroom(code, room, teacher) VALUES('B','301호','교사2');
INSERT INTO classroom(code, room, teacher) VALUES('C','202호','교사3');
INSERT INTO classroom(code, room, teacher) VALUES('D','401호','교사4');
INSERT INTO classroom(code, room, teacher) VALUES('E','503호','교사5');

-- 3개를 일괄적으로 추가한 후 commit (여러개 넣을 때 이 방법을 써야함)
INSERT ALL
    INTO classroom(code, room, teacher) VALUES('F','201호','교사6')
    INTO classroom(code, room, teacher) VALUES('G','203호','교사7')
    INTO classroom(code, room, teacher) VALUES('H','105호','교사8')
SELECT * FROM DUAL;

-------------------------------------------------------------------------------

-- JOIN
-- 표준으로 되어있는걸 쓰자 

--inner join => 교집합
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code; -- 모든 SQL에선 수행이 불가능할 수 있음
SELECT s.*, c.* FROM student s INNER JOIN classroom c ON s.class=c.code; -- 모든 SQL에선 수행 가능함, ANSI 표준

-- 국어 80점이상인 사람만 출력
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code AND s.kor >= 80;
SELECT s.*, c.* FROM student s INNER JOIN classroom c ON s.class=c.code WHERE s.kor >= 80; -- 얜 where이 없어서 where적어줬음

--right outer join => classroom의 모든 것 = student는 일치하는 것 없으면 null
SELECT s.*, c.* FROM student s, classroom c WHERE s.class(+)=c.code;
SELECT s.*, c.* FROM student s RIGHT OUTER JOIN classroom c ON s.class=c.code; --표준

-- left outer join => student의 모든 것 = classroom은 일치하는 것이 없으면 null
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code(+);
SELECT s.*, c.* FROM student s LEFT OUTER JOIN classroom c ON s.class=c.code; -- 표준

-- full outer join => student와 classroom 둘 다 모든 값을 가져옴
-- 표준만 가능하다
SELECT s.*, c.* FROM student s, classroom c WHERE s.class(+)=c.code(+); -- 오류
SELECT s.*, c.* FROM student s FULL OUTER JOIN classroom c ON s.class=c.code; -- 표준


