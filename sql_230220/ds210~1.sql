--테이블 전체 삭제
DROP TABLE purchase CASCADE CONSTRAINTS;
DROP TABLE memberaddr CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;


CREATE TABLE item (
    code     NUMBER NOT NULL,
    name     VARCHAR2(200),
    price    NUMBER,
    quantity NUMBER,
    content  CLOB,
    regdate  TIMESTAMP,
    CONSTRAINT pk_item PRIMARY KEY ( code )
);

COMMENT ON TABLE item IS '물품테이블';

COMMENT ON COLUMN item.code IS '물품코드(PK)';

COMMENT ON COLUMN item.name IS '물품명';

COMMENT ON COLUMN item.regdate IS '등록일자';

CREATE TABLE member (
    userid     VARCHAR2(30) NOT NULL,
    userpw     VARCHAR2(200),
    username   VARCHAR2(15),
    userphone  VARCHAR2(15),
    usergender VARCHAR2(1),
    userdate   DATE,
    CONSTRAINT pk_member PRIMARY KEY ( userid )
);

COMMENT ON TABLE member IS '회원테이블';

COMMENT ON COLUMN member.userid IS '회원아이디';

COMMENT ON COLUMN member.userpw IS '회원비밀번호';

COMMENT ON COLUMN member.username IS '회원이름';

COMMENT ON COLUMN member.userphone IS '회원전화번호';

COMMENT ON COLUMN member.usergender IS '회원성';

COMMENT ON COLUMN member.userdate IS '가입일자';

CREATE TABLE memberaddr (
    userno       NUMBER NOT NULL,
    useraddr     VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate     TIMESTAMP,
    userid       VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_memberaddr PRIMARY KEY ( userno )
);

COMMENT ON TABLE memberaddr IS '주소테이블';

COMMENT ON COLUMN memberaddr.useraddr IS '주소';

COMMENT ON COLUMN memberaddr.userid IS '회원아이디';

CREATE TABLE purchase (
    no      NUMBER NOT NULL,
    cnt     NUMBER,
    regdate TIMESTAMP,
    code    NUMBER NOT NULL,
    userid  VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_purchase PRIMARY KEY ( no )
);

COMMENT ON TABLE purchase IS '주문테이블';

COMMENT ON COLUMN purchase.no IS '주문번호';

COMMENT ON COLUMN purchase.cnt IS '주문수량';

COMMENT ON COLUMN purchase.regdate IS '주문일자';

COMMENT ON COLUMN purchase.code IS '물품코드(PK)';

COMMENT ON COLUMN purchase.userid IS '회원아이디';

ALTER TABLE memberaddr
    ADD CONSTRAINT fk_member_to_memberaddr FOREIGN KEY ( userid )
        REFERENCES member ( userid );

ALTER TABLE purchase
    ADD CONSTRAINT fk_item_to_purchase FOREIGN KEY ( code )
        REFERENCES item ( code );

ALTER TABLE purchase
    ADD CONSTRAINT fk_member_to_purchase FOREIGN KEY ( userid )
        REFERENCES member ( userid );