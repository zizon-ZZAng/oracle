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

COMMENT ON TABLE member IS 'ȸ�����̺�';

COMMENT ON COLUMN member.userid IS 'ȸ�����̵�';


CREATE TABLE memberaddr(
  userno       NUMBER        NOT NULL,
  useraddr     VARCHAR2(100),
  userpostcode NUMBER(5)    ,
  userdate     TIMESTAMP    ,
  userid       VARCHAR2(50)  NOT NULL,
  CONSTRAINT PK_memberaddr PRIMARY KEY (userno)
);



--���̺� ���� ����, �ܷ�Ű �߰��ϱ�
--���̺� ����� �� �Ŀ� ���������� �߰�
--ALTER TABLE ���̺�� ADD CONSTRAINT �������Ǹ�_���� FOERIGN KEY( �÷���)
--REFERENCES ������ ���̺��;
ALTER TABLE memberaddr ADD CONSTRAINT FK_member_TO_memberaddr
    FOREIGN KEY (userid) REFERENCES member (userid);

--���̺� �����ǰ� ��������
--member���̺��� usergendeere�� M F�� ��밡���ϵ��� �������� �߰�)
--ALTER TABLE ���̺�� ADD CONSTRAINT �������Ǹ�_����
--CHECK (�÷��� IN('�����Ұ�','�����Ұ�');
ALTER TABLE member ADD CONSTRAINT CHK_member_usergender CHECK (usergender IN ('M','F'));


--���̺� memberaddrtbl
CREATE TABLE memberaddrtbl(
    userno NUMBER,
    useraddr VARCHAR2(100),
    userpostcode NUMBER(5),
    userdate TIMESTAMP,
    userid VARCHAR2(50),
    CONSTRAINT memberaddrtbl_pk PRIMARY KEY(userno),  --�⺻Ű
    CONSTRAINT memberaddrtbl_fk FOREIGN KEY(userid) REFERENCES membertbl(userid)
);







--DDL ( ������ ���Ǿ�) => �ڷḦ �����ϱ� ���� �����ϴ� �������� ��  ���̺� ���� .. ��
--ȸ�����̺� ����
--���̺� �� membertbl
CREATE TABLE membertbl(
    userid VARCHAR2(50), --�⺻Ű
    userpw VARCHAR2(200),  -- aaa=> �ִ� 200 ��
    username VARCHAR2(10),
    userage NUMBER(3),
    userphone VARCHAR2(15), --010-0000-0000
    usergender VARCHAR2(1),
    userdate DATE, --���� ���� ( ����?
    
    
    CONSTRAINT membertbl_pk PRIMARY KEY(userid), --�⺻Ű ����
    CONSTRAINT membertbl_gender CHECK(usergender in ('M','F')) -- ���� �������� ���� (M,F�� �߰� ����)

);


--���̺� �÷� �߰�(ADD, MODIFY, DROP)
ALTER TABLE membertbl ADD useremail VARCHAR2(100);

--���̺� ����
DROP TABLE membertbl CASCADE CONSTRAINTS; --���� ������ ������ ���̺� ����




--------------------------------------------------------------------------
--����, �ּ���
--DCL (������ �����) => ����ڸ� �����ϰ� ������ �ο� , �����ϴ� ��ɾ�
--(system ���̵� ������ DBA ���� �ο�)

--  CREATE USER id228 IDENTIFIED BY pw228; ����� ����(�̹� �����Ǿ�����)

--  GRANT CONNECT, RESOURCE, DBA TO ds228;  ���� ���ҽ� ���, DBA ���� �ο�

--   REVOKE CONNECT, RESOURCE, DBA FROM ds228;  ���� ����
--   DROP USER ds228 CASCADE;  ����� ����

-- ALTER USER ds228 ACCOUNT UNLOCK;  ��ȣ 3�� ������ ���� lock Ǯ��




