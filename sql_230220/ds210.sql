-- ����, �ּ��� ctlr + /



--VSCode���� ��������� ���� ����

CREATE TABLE member(
  userid     VARCHAR2(30)  NOT NULL,    -- �⺻Ű�� ������ not null �����Ǿ� ����
  userpw     VARCHAR2(200),
  username   VARCHAR2(15) ,
  userage    NUMBER(3)    ,
  userphone  VARCHAR2(15) ,
  usergender VARCHAR2(1)  ,
  userdate   DATE         ,
  CONSTRAINT PK_member PRIMARY KEY (userid) --�������� �ɷ�����
);

COMMENT ON TABLE member IS 'ȸ�����̺�';
COMMENT ON COLUMN member.userid IS 'ȸ�����̵�';

CREATE TABLE memberaddr(
  userno       NUMBER        NOT NULL,
  useraddr     VARCHAR2(100),
  userpostcode NUMBER(5)    ,
  userdate     TIMESTAMP    ,
  userid       VARCHAR2(30)  NOT NULL,
  CONSTRAINT PK_memberaddr PRIMARY KEY (userno)
);

-- ���̺� ���� ����, �ܷ�Ű �߰��ϱ� 
-- ���̺��� ����� �� �Ŀ� ���������� �߰�
-- ALTER TABLE ���̺�� ADD CONSTRAINT �������Ǹ� FOREIGN KEY (�÷���) REFERENCES ���������̺�� (�������÷���);
ALTER TABLE memberaddr ADD CONSTRAINT FK_member_TO_memberaddr
    FOREIGN KEY (userid) REFERENCES member (userid);

--���̺��� �����ǰ� �������� �߰�
-- (member���̺��� usergender�� M�� F�� ��밡���ϵ��� ���� ���� �߰�)
-- ALTER TABLE ���̺�� ADD CONSTRAINT �������Ǹ�_���� CHECK (�÷��� IN ('�����Ұ�', '�����Ұ�'));
ALTER TABLE member ADD CONSTRAINT CHK_member_usegender CHECK (usergender IN ('M', 'F'));

--CLOB�� VARCHAR2�� �Ѱ谡 ���� �� ���







--------------------------------------------------------------------------------------------


-- ���̺� memberaddrtbl ����
CREATE TABLE memberaddrtbl (
    userno       NUMBER,
    useraddr     VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate     TIMESTAMP,
    userid       VARCHAR2(30),    -- �ܷ�Ű�� ������ ���� ���̺��� �״�� ������ �;��� �����Ǵ°� �������

    CONSTRAINT memberaddrtbl_pk PRIMARY KEY ( userno ), --�⺻Ű
    CONSTRAINT memberaddrtbl_fk FOREIGN KEY ( userid ) REFERENCES membertbl ( userid ) --�ܷ�Ű

);


--CONSTRAINT �����(���� �� �̸��� �����ؾ���. �ߺ� ���� �ȵ�)


DROP TABLE memberaddr CASCADE CONSTRAINTS; --���̺� ����


--DDL (������ ���Ǿ�) => �ڷḦ �����ϱ� ���� �����ϴ� �������� �� (���̺� ����)
--ȸ�� ���̺� ����
--���̺�� membertbl
CREATE TABLE membertbl (
    userid     VARCHAR2(30),     --String = VARCHAR2
    userpw     VARCHAR2(200),    -- ��й�ȣ�� aaa�״�� �Ǵ°� �ƴ϶� aaa�� fargase286aw21f6846awef1zxx65�� �� �� �־. �׷��� ��ȣ�� ��� ��������
    username   VARCHAR2(15),
    userage    NUMBER(3),       -- int = NUMBER
    userphone  VARCHAR2(15),  -- 010-0000-0000
    usergender VARCHAR2(1),  -- 'M', 'F'�� �߰��� �� ����
    userdate   DATE, --��������

    CONSTRAINT membertbl_pk PRIMARY KEY ( userid ),-- �⺻Ű �������� (�⺻Ű ����)
    CONSTRAINT membertbl_gender CHECK ( usergender IN ( 'M', 'F' ) ) --���� ��������, ������ M�� F�� �� �� �ְԲ�

);


-- ���̺� �÷� �߰� (ADD �߰�, MODIFY ����, DROP ����)
ALTER TABLE membertbl ADD useremail VARCHAR2(100); 


-- ���̺� ����
DROP TABLE membertbl CASCADE CONSTRAINTS;   -- ���������� ������ ���̺� ����


-------------------------------------------------------------------------------


--DCL (������ �����) => ����ڸ� �����ϰ� ������ �ο� �� �����ϴ� ��ɾ�
--system ���̵� ������ DBA����

--CREATE USER ���̵� IDENTIFIED BY ��й�ȣ; 
--CREATE USER ds210 IDENTIFIED BY pw210; -- ����� ����
--GRANT CONNECT, RESOURCE, DBA TO ds210; -- ���� ���ҽ����, DBA���� �ο�

--REVOKE CONNECT, RESOURCE, DBA FROM ds210; -- ���� ����
--DROP USER ds210 CASCADE; -- ����� ����

--ALTER USER ds210 ACCOUNT UNLOCK; -- ��ȣ 3�� ������ ���� lockǮ��