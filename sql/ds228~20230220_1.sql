CREATE TABLE member(
  userid     VARCHAR2(50)  NOT NULL,
  userpw     VARCHAR2(200),
  username   VARCHAR2(10) ,
  userphone  VARCHAR2(15) ,
  usergender VARCHAR2(1)  ,
  userdate   DATE         ,
  userage    NUMBER(3)    ,
  CONSTRAINT PK_member PRIMARY KEY (userid)
);

COMMENT ON TABLE member IS '회원테이블';

COMMENT ON COLUMN member.userid IS '회원아이디';


CREATE TABLE memberaddr(
  userno       NUMBER        NOT NULL,
  useraddr     VARCHAR2(100),
  userpostcode NUMBER(5)    ,
  userdate     TIMESTAMP    ,
  userid       VARCHAR2(50)  NOT NULL,
  CONSTRAINT PK_memberaddr PRIMARY KEY (userno)
);



--테이블 구조 변경, 외래키 추가하기
--테이블 만들고 난 후에 제약조건을 추가
--ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명_고유 FOERIGN KEY( 컬럼명)
--REFERENCES 가져올 테이블명;
ALTER TABLE memberaddr ADD CONSTRAINT FK_member_TO_memberaddr
    FOREIGN KEY (userid) REFERENCES member (userid);

--테이블 생성되고 제약조건
--member테이블의 usergendeere에 M F만 사용가능하도록 제약조건 추가)
--ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명_고유
--CHECK (컬럼명 IN('포함할값','포함할값');
ALTER TABLE member ADD CONSTRAINT CHK_member_usergender CHECK (usergender IN ('M','F'));


--테이블 memberaddrtbl
CREATE TABLE memberaddrtbl(
    userno NUMBER,
    useraddr VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate TIMESTAMP,
    userid VARCHAR2(50),
    CONSTRAINT memberaddrtbl_pk PRIMARY KEY(userno),  --기본키
    CONSTRAINT memberaddrtbl_fk FOREIGN KEY(userid) REFERENCES membertbl(userid)
);







--DDL ( 데이터 정의어) => 자료를 저장하기 전에 설정하는 구조적인 것  테이블 생성 .. 등
--회원테이블 생성
--테이블 명 membertbl
CREATE TABLE membertbl(
    userid VARCHAR2(50), --기본키
    userpw VARCHAR2(200),  -- aaa=> 최대 200 자
    username VARCHAR2(10),
    userage NUMBER(3),
    userphone VARCHAR2(15), --010-0000-0000
    usergender VARCHAR2(1),
    userdate DATE, --가입 일자 ( 연월?
    
    
    CONSTRAINT membertbl_pk PRIMARY KEY(userid), --기본키 설정
    CONSTRAINT membertbl_gender CHECK(usergender in ('M','F')) -- 성별 제약조건 설정 (M,F만 추가 가능)

);


--테이블에 컬럼 추가(ADD, MODIFY, DROP)
ALTER TABLE membertbl ADD useremail VARCHAR2(100);

--테이블 삭제
DROP TABLE membertbl CASCADE CONSTRAINTS; --제약 조건을 포함한 테이블 삭제




--------------------------------------------------------------------------
--설명문, 주석문
--DCL (데이터 제어어) => 사용자를 생성하고 권한을 부여 , 삭제하는 명령어
--(system 아이디를 가지는 DBA 권한 부여)

--  CREATE USER id228 IDENTIFIED BY pw228; 사용자 생성(이미 생성되어있음)

--  GRANT CONNECT, RESOURCE, DBA TO ds228;  접근 리소스 사용, DBA 권한 부여

--   REVOKE CONNECT, RESOURCE, DBA FROM ds228;  권한 제거
--   DROP USER ds228 CASCADE;  사용자 삭제

-- ALTER USER ds228 ACCOUNT UNLOCK;  암호 3번 오류에 따른 lock 풀기




