
SET SERVEROUTPUT ON;

--- LOCATION1 테이블
CREATE TABLE location1
(
  no   NUMBER(5)    NOT NULL,
  name VARCHAR2(20),
  CONSTRAINT PK_location1 PRIMARY KEY (no)
);
DROP TABLE weather1 CASCADE CONSTRAINTS;

--- LOCATION1 데이터 추가

-- 시퀀스 만듦
CREATE SEQUENCE SEQ_LOCATION1_NO INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- 삭제
DROP SEQUENCE SEQ_LOCATION1_NO

-- 함수 만듦
CREATE OR REPLACE FUNCTION func_seq_location1_no_naxtval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_LOCATION1_NO.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- 삭제
DROP FUNCTION func_seq_location1_no_naxtval;

-- 칼럼사이즈 넘 작다고 해서 칼럼사이즈 변경
ALTER TABLE location1 MODIFY(name varchar2(100));

-- 드디어 일괄 추가 ㄱㄱ
INSERT ALL
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '서울특별시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '부산광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '대구광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '인천광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '광주광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '대전광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '울산광역시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '세종특별자치시')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '경기도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '강원도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '충청북도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '충청남도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '전라북도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '전라남도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '경상북도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '경상남도')
INTO location1 (no, name)
    VALUES(FUNC_SEQ_LOCATION1_NO_NAXTVAL, '제주특별자치도')
    SELECT * FROM DUAL;
 
-- 데이터 삭제  
DELETE FROM location1
WHERE no = 4;


ROLLBACK;

COMMIT;

SELECT * FROM location1;

--------------------------------------------------

-- WEATHER1 테이블(수차례의 수정끝에)
DROP TABLE weather1 CASCADE CONSTRAINTS;
CREATE TABLE weather1
(
  code     number(10)   NOT NULL,
  regdate  TIMESTAMP    NOT NULL,
  weather  VARCHAR2(50),
  TEMPHIGH NUMBER(2)   ,
  TEMPROW  NUMBER(2)   ,
  no       NUMBER(5)    NOT NULL,
  CONSTRAINT PK_weather1 PRIMARY KEY (code)
);

--- 제약조건
-- 외래키
ALTER TABLE weather1
  ADD CONSTRAINT FK_location1_TO_weather1
    FOREIGN KEY (no)
    REFERENCES location1 (no);
-- weather1 :최저기온은 최고기온보다 큰 값을 가질 수 없다
ALTER TABLE weather1 
ADD CONSTRAINT TEMPHIGH_HOTTER_THEN_TEMPROW 
CHECK(TEMPROW<=TEMPHIGH);

ALTER TABLE weather1 DROP CONSTRAINT FK_location1_TO_weather1;
ALTER TABLE weather1 DROP CONSTRAINT TEMPHIGH_HOTTER_THEN_TEMPROW;



--- WEATHER1 데이터 입력

-- 시퀀스
CREATE SEQUENCE SEQ_WEATHER1_CODE INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- 삭제
DROP SEQUENCE SEQ_WEATHER1_CODE;

-- 함수
CREATE OR REPLACE FUNCTION func_SEQ_WEATHER1_CODE_naxtval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_WEATHER1_CODE.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- 삭제
DROP FUNCTION func_SEQ_WEATHER1_CODE_naxtval;

-- 일괄입력
-- 데이터 어디서 가져오지 :https://www.weather.go.kr/w/weather/forecast/mid-term.do

INSERT ALL 
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '맑음', 8, 25, 30)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '구름조금', 2, 14, 31)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '구름많음', 10.4, 32)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '흐림', 12.0, 33)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '비', 9.8, 34)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '눈(전북전주)', 11.3, 35)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '비 또는 눈(전남여수)', 11.6, 36)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '천둥번개(경북안동)', 14.1, 37)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '안개(경남창원)', 13.5, 38)
INTO WEATHER1 (code, regdate, weather, TEMPROW, TEMPHIGH, NO)
VALUES(func_SEQ_WEATHER1_CODE_naxtval, CURRENT_DATE+1 , '황사', 11.2, 39)
SELECT * FROM DUAL;

DELETE FROM WEATHER1
WHERE TEMPROW = 3;

-- ORA-00001: 무결성 제약 조건(DS236.PK_WEATHER1)에 위배됩니다
-- DK .. tL. .

-- WEATHER1에 코드 추가
-- 다시 삭제 생성 제약제약
--- 시퀀스 생성 SEQ_WEATHER1_CODE
-- 함수 생성 func_SEQ_WEATHER1_CODE_naxtval 
-- 일괄 수정 code, func_SEQ_WEATHER1_CODE_naxtval 추가

--10개 행 이(가) 삽입되었습니다.
--커밋 완료.


SELECT * FROM WEATHER1;

ROLLBACK;

COMMIT;
