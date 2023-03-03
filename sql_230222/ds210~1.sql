
--전체조회 (학생 테이블)
SELECT s.* FROM student s;
--전체조회 (강의실 테이블)
SELECT c.* FROM classroom c;


-- INNER JOIN => VIEW로 생성
CREATE OR REPLACE VIEW studentview AS 
    SELECT s.*, c.room, c.teacher FROM student s INNER JOIN classroom c ON s.class=c.code;


--전체 조회 (view로 만든 student+classroom 테이블)    
SELECT sv.* FROM studentview sv;


--문제1) class가 A,B인것만 조회
SELECT sv.* FROM studentview sv WHERE class='A' OR class='B';
SELECT sv.* FROM studentview sv WHERE class IN('A', 'B');

--문제2) total 컬럼이 마지막에 추가되어 점수합계 구하기
-- (+평균 구하기, 학점구하기)
-- CASE END가 if문 느낌
-- +view 생성까지
CREATE OR REPLACE VIEW studentview1 AS
SELECT 
    sv.*, sv.kor+sv.eng+sv.math total, 
    ROUND((sv.kor+sv.eng+sv.math)/3, 1) avg,
    CASE
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3, 1) >= 90)THEN 'a'
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3,1) >= 80)THEN 'b'
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3,1) >= 70)THEN 'c'
        ELSE 'd'
    END grade
FROM 
    studentview sv;
    
--전체조회
SELECT sv1.* FROm studentview1 sv1;


--문제3) 교사1, 교사3가 아닌것만 조회 <> 부정
SELECT sv.* FROM studentview sv WHERE teacher <> '교사1' and teacher <> '교사3';
SELECT sv.* FROM studentview sv WHERE teacher NOT IN('교사1', '교사3');


-- 최대, 최소값 구하기 (이건 전체조회를 하면 안됨)
-- 전체데이터에서 필터하고 그룹수행 where을 써서 필터

SELECT 
    sv1.class,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.class;
    

-- 전체데이터에서 필터하고 그룹수행 후 다시 필터를 쓸 땐 having 쓰기
SELECT 
    sv1.class,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.class
HAVING
    COUNT(sv1.total) > 1;

    
    
--학점으로 나누기
SELECT 
    sv1.grade,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.grade;


