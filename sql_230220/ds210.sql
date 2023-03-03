-- 설명문, 주석문 ctlr + /



--VSCode에서 만들었던거 복붙 했음

CREATE TABLE member(
  userid     VARCHAR2(30)  NOT NULL,    -- 기본키로 잡으면 not null 설정되어 있음
  userpw     VARCHAR2(200),
  username   VARCHAR2(15) ,
  userage    NUMBER(3)    ,
  userphone  VARCHAR2(15) ,
  usergender VARCHAR2(1)  ,
  userdate   DATE         ,
  CONSTRAINT PK_member PRIMARY KEY (userid) --제약조건 걸려있음
);

COMMENT ON TABLE member IS '회원테이블';
COMMENT ON COLUMN member.userid IS '회원아이디';

CREATE TABLE memberaddr(
  userno       NUMBER        NOT NULL,
  useraddr     VARCHAR2(100),
  userpostcode NUMBER(5)    ,
  userdate     TIMESTAMP    ,
  userid       VARCHAR2(30)  NOT NULL,
  CONSTRAINT PK_memberaddr PRIMARY KEY (userno)
);

-- 테이블 구조 변경, 외래키 추가하기 
-- 테이블을 만들고 난 후에 제약조건을 추가
-- ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 FOREIGN KEY (컬럼명) REFERENCES 가져올테이블명 (가져올컬럼명);
ALTER TABLE memberaddr ADD CONSTRAINT FK_member_TO_memberaddr
    FOREIGN KEY (userid) REFERENCES member (userid);

--테이블이 생성되고 제약조건 추가
-- (member테이블의 usergender에 M과 F만 사용가능하도록 제약 조건 추가)
-- ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명_고유 CHECK (컬럼명 IN ('포함할값', '포함할값'));
ALTER TABLE member ADD CONSTRAINT CHK_member_usegender CHECK (usergender IN ('M', 'F'));

--CLOB는 VARCHAR2에 한계가 있을 때 사용







--------------------------------------------------------------------------------------------


-- 테이블 memberaddrtbl 생성
CREATE TABLE memberaddrtbl (
    userno       NUMBER,
    useraddr     VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate     TIMESTAMP,
    userid       VARCHAR2(30),    -- 외래키는 가지고 오는 테이블에서 그대로 가지고 와야함 변형되는게 없어야함

    CONSTRAINT memberaddrtbl_pk PRIMARY KEY ( userno ), --기본키
    CONSTRAINT memberaddrtbl_fk FOREIGN KEY ( userid ) REFERENCES membertbl ( userid ) --외래키

);


--CONSTRAINT 제약명(여기 들어갈 이름은 고유해야함. 중복 절대 안됨)


DROP TABLE memberaddr CASCADE CONSTRAINTS; --테이블 삭제


--DDL (데이터 정의어) => 자료를 저장하기 전에 설정하는 구조적인 것 (테이블 생성)
--회원 테이블 생성
--테이블명 membertbl
CREATE TABLE membertbl (
    userid     VARCHAR2(30),     --String = VARCHAR2
    userpw     VARCHAR2(200),    -- 비밀번호는 aaa그대로 되는게 아니라 aaa가 fargase286aw21f6846awef1zxx65가 될 수 있어서. 그래서 암호는 길게 잡아줘야함
    username   VARCHAR2(15),
    userage    NUMBER(3),       -- int = NUMBER
    userphone  VARCHAR2(15),  -- 010-0000-0000
    usergender VARCHAR2(1),  -- 'M', 'F'만 추가될 수 있음
    userdate   DATE, --가입일자

    CONSTRAINT membertbl_pk PRIMARY KEY ( userid ),-- 기본키 제약조건 (기본키 설정)
    CONSTRAINT membertbl_gender CHECK ( usergender IN ( 'M', 'F' ) ) --성별 제약조건, 성별엔 M과 F만 들어갈 수 있게끔

);


-- 테이블에 컬럼 추가 (ADD 추가, MODIFY 수정, DROP 삭제)
ALTER TABLE membertbl ADD useremail VARCHAR2(100); 


-- 테이블 삭제
DROP TABLE membertbl CASCADE CONSTRAINTS;   -- 제약조건을 포함한 테이블 삭제


-------------------------------------------------------------------------------


--DCL (데이터 제어어) => 사용자를 생성하고 권한을 부여 및 삭제하는 명령어
--system 아이디를 가지는 DBA권한

--CREATE USER 아이디 IDENTIFIED BY 비밀번호; 
--CREATE USER ds210 IDENTIFIED BY pw210; -- 사용자 생성
--GRANT CONNECT, RESOURCE, DBA TO ds210; -- 접근 리소스사용, DBA권한 부여

--REVOKE CONNECT, RESOURCE, DBA FROM ds210; -- 권한 제거
--DROP USER ds210 CASCADE; -- 사용자 삭제

--ALTER USER ds210 ACCOUNT UNLOCK; -- 암호 3번 오류에 따른 lock풀기