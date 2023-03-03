--���̺� ��ü ����
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

COMMENT ON TABLE item IS '��ǰ���̺�';

COMMENT ON COLUMN item.code IS '��ǰ�ڵ�(PK)';

COMMENT ON COLUMN item.name IS '��ǰ��';

COMMENT ON COLUMN item.regdate IS '�������';

CREATE TABLE member (
    userid     VARCHAR2(30) NOT NULL,
    userpw     VARCHAR2(200),
    username   VARCHAR2(15),
    userphone  VARCHAR2(15),
    usergender VARCHAR2(1),
    userdate   DATE,
    CONSTRAINT pk_member PRIMARY KEY ( userid )
);

COMMENT ON TABLE member IS 'ȸ�����̺�';

COMMENT ON COLUMN member.userid IS 'ȸ�����̵�';

COMMENT ON COLUMN member.userpw IS 'ȸ����й�ȣ';

COMMENT ON COLUMN member.username IS 'ȸ���̸�';

COMMENT ON COLUMN member.userphone IS 'ȸ����ȭ��ȣ';

COMMENT ON COLUMN member.usergender IS 'ȸ����';

COMMENT ON COLUMN member.userdate IS '��������';

CREATE TABLE memberaddr (
    userno       NUMBER NOT NULL,
    useraddr     VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate     TIMESTAMP,
    userid       VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_memberaddr PRIMARY KEY ( userno )
);

COMMENT ON TABLE memberaddr IS '�ּ����̺�';

COMMENT ON COLUMN memberaddr.useraddr IS '�ּ�';

COMMENT ON COLUMN memberaddr.userid IS 'ȸ�����̵�';

CREATE TABLE purchase (
    no      NUMBER NOT NULL,
    cnt     NUMBER,
    regdate TIMESTAMP,
    code    NUMBER NOT NULL,
    userid  VARCHAR2(30) NOT NULL,
    CONSTRAINT pk_purchase PRIMARY KEY ( no )
);

COMMENT ON TABLE purchase IS '�ֹ����̺�';

COMMENT ON COLUMN purchase.no IS '�ֹ���ȣ';

COMMENT ON COLUMN purchase.cnt IS '�ֹ�����';

COMMENT ON COLUMN purchase.regdate IS '�ֹ�����';

COMMENT ON COLUMN purchase.code IS '��ǰ�ڵ�(PK)';

COMMENT ON COLUMN purchase.userid IS 'ȸ�����̵�';

ALTER TABLE memberaddr
    ADD CONSTRAINT fk_member_to_memberaddr FOREIGN KEY ( userid )
        REFERENCES member ( userid );

ALTER TABLE purchase
    ADD CONSTRAINT fk_item_to_purchase FOREIGN KEY ( code )
        REFERENCES item ( code );

ALTER TABLE purchase
    ADD CONSTRAINT fk_member_to_purchase FOREIGN KEY ( userid )
        REFERENCES member ( userid );