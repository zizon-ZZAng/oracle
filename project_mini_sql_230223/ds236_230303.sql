
-- WEATHER1 테이블 새로 만듦
-- 수정사항: 컬럼변경 TEMPHIGH, TEMPROW -> temperature
-- 수정사항: 제약조건 TEMPHIGH_HOTTER_THEN_TEMPROW X
-- 수정사항: temperature NUMBER -> FLOAT
-- 수정사항: 오타 고침 : func_SEQ_WEATHER1_CODE_nextval (naxt->next)

-- 수정사항: 


SELECT * FROM WEATHER1 ;

DROP SEQUENCE SEQ_WEATHER1_CODE;
DROP TABLE WEATHER1 CASCADE CONSTRAINTS;
DROP FUNCTION FUNC_SEQ_WEATHER1_CODE_NAXTVAL;

CREATE TABLE weather1
(
  code        number(10)   NOT NULL,
  regdate     TIMESTAMP    NOT NULL,
  weather     VARCHAR2(50),
  temperature NUMBER(10)  ,
  no          NUMBER(5)    NOT NULL,
  CONSTRAINT PK_weather1 PRIMARY KEY (code)
);

ALTER TABLE recommend1
  ADD CONSTRAINT FK_weather1_TO_recommend1
    FOREIGN KEY (code)
    REFERENCES weather1 (code);




--- 제약조건
-- 외래키
ALTER TABLE weather1
  ADD CONSTRAINT FK_location1_TO_weather1
    FOREIGN KEY (no)
    REFERENCES location1 (no);
-- 외래키 삭제하기
ALTER TABLE weather1 DROP CONSTRAINT FK_location1_TO_weather1;


--- WEATHER1 데이터 입력

-- 시퀀스
CREATE SEQUENCE SEQ_WEATHER1_CODE INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCACHE;
-- 삭제
DROP SEQUENCE SEQ_WEATHER1_CODE;

-- 함수
CREATE OR REPLACE FUNCTION func_SEQ_WEATHER1_CODE_nextval RETURN NUMBER
IS
BEGIN
    RETURN SEQ_WEATHER1_CODE.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/
-- 삭제
DROP FUNCTION func_SEQ_WEATHER1_CODE_nextval;



-- 일괄입력

INSERT ALL 
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '맑음', 9.8, 30)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '구름조금', 14.0, 31)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '구름많음', 14.1, 32)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '흐림', 7.1, 33)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '비', 13.1, 34)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '눈', 12.0, 35)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '비 또는 눈', 13.6, 36)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '천둥번개', 9.8, 37)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '안개', 9.2, 38)
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE+1 , '황사', 11.2, 39)
SELECT * FROM DUAL;
-- TEMPERATURE는 '현재기온'으로 (현재: 로그인(접속시점)시점)
-- weather도 '현재날씨'겠지

-- WEATHER1 편집에서 TEMPERATURE를 NUMBER -> FLOAT으로 바꿈
-- 소숫점 기온이 안들어갔음

-- 소숫점 들어가는지 보려고 한개 넣기 시도
INSERT
INTO WEATHER1 (code, regdate, weather, TEMPERATURE, NO)
VALUES(func_SEQ_WEATHER1_CODE_nExtval, CURRENT_DATE+1 , '구름많음', 10.4, 32);

SELECT * FROM WEATHER1;

ROLLBACK;

COMMIT;


