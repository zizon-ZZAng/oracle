
--��ü��ȸ
SELECT r.* FROM restaurant r;
SELECT m.* FROM menu m;
SELECT c.* FROM customer c;


--����
UPDATE menu SET name='�ຸ��', price=14000, content='���ֳ�?' WHERE no=1010 AND phone='051-000-0000' ;

--���
COMMIT; -- �����Ű��
ROLLBACK; -- �ǵ�����

INSERT INTO restaurant(phone, name, address, password) VALUES('051-000-0000','�߱���','�λ� ����', 'a');

INSERT INTO menu(no, name, price, content, phone) VALUES(seq_menu_no.NEXTVAL, '��������', 8000, '����','051-001-0002');

--INSERT INTO customer(email, password, phone, address, chk) VALUES('a123@naver.com','a123', '010-0000-0001', '�λ� ����', 0);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('b123@gmail.com','a5a5', '010-0000-0002', '�λ� ������', 1);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('c123@gmail.com','48sa2', '010-0000-0003', '�λ� ���', 0);
--INSERT INTO customer(email, password, phone, address, chk) VALUES('d123@daum.com','77d8', '010-0000-0004', '�λ� ����', 0);


--������ ����, ���������� �����ؾ���
CREATE SEQUENCE seq_menu_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_ordertbl_no INCREMENT BY 1 START WITH 100001 NOMAXVALUE NOCACHE;



--------------------------------------------------------------------------------
--�Ĵ�
CREATE TABLE restaurant
(
  phone    VARCHAR2(15)  NOT NULL,
  name     VARCHAR2(100),
  address  VARCHAR2(200),
  password VARCHAR2(200),
  regdate  TIMESTAMP DEFAULT CURRENT_DATE,
  CONSTRAINT PK_restaurant PRIMARY KEY (phone)
);

--�޴�
CREATE TABLE menu
(
  no      NUMBER        NOT NULL,
  name    VARCHAR2(100) NOT NULL,
  price   NUMBER        DEFAULT 0 NOT NULL,
  content CLOB,
  regdate TIMESTAMP     DEFAULT CURRENT_DATE,
  phone   VARCHAR2(15)  NOT NULL,
  CONSTRAINT PK_menu PRIMARY KEY (no)
);

--��
CREATE TABLE customer
(
  email    VARCHAR2(200) NOT NULL,
  password VARCHAR2(200),
  phone    VARCHAR2(15) ,
  address  VARCHAR2(200),
  chk    NUMBER(1),--check�� 0 �Ǵ� 1
  regdate  TIMESTAMP     DEFAULT CURRENT_DATE,
  CONSTRAINT PK_customer PRIMARY KEY (email)
);

--�ֹ�
CREATE TABLE ordertbl
(
  no      NUMBER        NOT NULL,
  regdate TIMESTAMP     DEFAULT CURRENT_DATE,
  cnt     NUMBER        DEFAULT 1,
  email   VARCHAR2(200) NOT NULL,
  menuno  NUMBER        NOT NULL,
  CONSTRAINT PK_ordertbl PRIMARY KEY (no)
);

--�����
CREATE TABLE rider
(
  phone    VARCHAR2(15)  NOT NULL,
  name     VARCHAR2(20) ,
  regdate  TIMESTAMP DEFAULT CURRENT_DATE,
  password VARCHAR2(200),
  CONSTRAINT PK_rider PRIMARY KEY (phone)
);


--���
CREATE TABLE delivery
(
  no      NUMBER       NOT NULL,
  regdate TIMESTAMP    DEFAULT CURRENT_DATE,
  phone   VARCHAR2(15) NOT NULL,
  orderno NUMBER       NOT NULL,
  CONSTRAINT PK_delivery PRIMARY KEY (no)
);

-- menu ���̺��� phone�� �Ĵ����̺��� �⺻Ű�� �ܷ�Ű ����
ALTER TABLE menu
  ADD CONSTRAINT FK_restaurant_TO_menu
    FOREIGN KEY (phone)
    REFERENCES restaurant (phone);

--�ֹ� ���̺��� email�� �����̺� �⺻Ű�� email�� �ܷ�Ű ����
ALTER TABLE ordertbl
  ADD CONSTRAINT FK_customer_TO_ordertbl
    FOREIGN KEY (email)
    REFERENCES customer (email);

--�ֹ� ���̺��� menuno�� �޴����̺��� �⺻Ű no�� �ܷ�Ű ����
ALTER TABLE ordertbl
  ADD CONSTRAINT FK_menu_TO_ordertbl
    FOREIGN KEY (menuno)
    REFERENCES menu (no);


-- ������̺��� phone�� ����� ���̺� �⺻Ű�� phone���� �ܷ�Ű ����
ALTER TABLE delivery
  ADD CONSTRAINT FK_rider_TO_delivery
    FOREIGN KEY (phone)
    REFERENCES rider (phone);

-- ������̺��� orderno�� �ֹ� ���̺��� �⺻Ű no�� �ܷ�Ű ����
ALTER TABLE delivery
  ADD CONSTRAINT FK_ordertbl_TO_delivery
    FOREIGN KEY (orderno)
    REFERENCES ordertbl (no);

--------------------------------------------------------------------------------

-- ��ü ��ȸ
-- SELECT �÷��� FROM ���̺�� ��Ī;
SELECT i.* FROM item i;
SELECT i.code, i.name, i.quantity FROM item i;

-- ������ ���� order by �����÷� ASC|DESC;
-- ������ ������ �����͸� ���� �����ͼ� ������ �� �� �ִ� ������ ���� ó���� �� �� ����.
SELECT i.* FROM item i ORDER BY i.code DESC;

--���ǵ� �����͸� ���� �����ͼ� ������ �ٿ������
SELECT i.* FROM item i WHERE i.price >= 10000 ORDER BY i.code DESC;

-- AND
SELECT i.* FROM item i WHERE i.price >= 5000 AND i.price <=10000 ORDER BY i.code DESC;

-- ȸ�� ���̺��� ���̰� 10~30�ΰ͸� ��ȸ (���̵� ��������)
SELECT m.* FROM member m WHERE m.userage>=10 AND m.userage<=30 ORDER BY m.userid ASC;

-- OR�� �ϳ��ϳ� �� ���� ���
SELECT i.* FROM item i WHERE i.code=1 OR i.code=3 OR i.code=13 ORDER BY i.code DESC;
-- IN�� �ִ� ���
SELECT i.* FROM item i WHERE i.code IN(1,3,13) ORDER BY i.code DESC;

-- WHERE �÷��� LIKE '�ƹ��ų�' || '�����Ұ�' || '�ƹ��ų�'; => �����ϴ°� ���� ���
-- WHERE �÷��� LIKE '�ƹ��ų�' || '�����Ұ�'; => �����ϴ°ɷ� ������ �� ã��
-- WHERE �÷��� LIKE '�����Ұ�' || '�ƹ��ų�'; => �����ϴ°ɷ� �����ϴ� �� ã��
-- %�� �ƹ��ų���
SELECT i.*, i.price*i.quantity total FROM item i WHERE i.name LIKE '%' || '��' || '%';


--�Լ��� ��ɸ� Ȯ���ϰ���� �� SELECT * FROM ���̺��; => ���̺�� �ִ� ��ġ�� DUAL���� ��
SELECT ABS(-2) FROM DUAL;
SELECT CURRENT_DATE FROM DUAL; --�ڹٿ��� new Date() ���� 

-- TO_CHAR(���� or ��¥, 'format') -> format���� ���� ���ϴ� ���� �־������
SELECT TO_CHAR(123456789, '999,999,999') fROM DUAL; 

SELECT i.*, TO_CHAR(i.price*i.quantity, '999,999,999') total FROM item i;

SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD HH:MI:SS') ��¥ FROM DUAL;

-- 1. ��ü��ȸ ���̺� ������ ������� ��ȸ�� ��
-- 2. ���� �������� �ʿ��� �͸� ���� (WHERE)
-- 3. ���� (ORDER BY �������÷� ASC|DESC)
-- 4. ������ �����͸� �ʿ��� ���·� ����(�����Լ�) SELECT �÷���, �����Լ� FROM ���̺�� ��Ī;


SELECT m.userid, m.username, m.userage, m.userphone, m.userdate, TO_CHAR(m.userdate, 'YYYY-MM-DD HH') userdate1 
FROM member m WHERE m.userage >= 10 ORDER BY m.userid ASC;

-- ���� ��ü ������ ������ 5���� �� ���� ���� �����°Ÿ� ������ �ȳ��µ�
-- ������ �ϳ�¥���� ���ϴ� �� ������ ������ ����.
-- ������ �� �� ������ �߿��ϴ�
SELECT COUNT(*), MAX(i.price), MAX(i.quantity) FROM item i ORDER BY i.code DESC;


-- SELECT���� ������ ������ ������ ���̺�
-- ROW_NUMBER() OVER(ORDER BY �÷��� ASC|DESC) => ��ȣ�� �Ű���
SELECT i1.* FROM ( --������ ���̺� ����
    SELECT i.*, ROW_NUMBER() OVER(ORDER BY i.price ASC) rown    -- ()���̿� �ִ°͵��� ���� ���̺�� �ڸ������Ŷ�� ����
    FROM item i ORDER BY i.code DESC) i1 WHERE i1.rown >=1 AND i1.rown <=5;
    --��װ� ���� �����ƴµ� ������ ���̺� �־���� ��Ȳ?

-- view(=������ ���̺�) ����� CREATE OR REPLACE VIEW ���̸� AS ����
-- view�� selet�� �ȴ� insert, update �� �ȵ�
-- view�� ���� �־���� �� �����Ѱ͵��� 
CREATE OR REPLACE VIEW itemview AS 
SELECT i.*, ROW_NUMBER() OVER(ORDER BY i.price ASC) rown
    FROM item i ORDER BY i.code DESC;
      

-- �����Ȱ� ���̺�ó�� ������
SELECT iv.* FROM itemview iv;


SELECT iv.* FROM itemview iv WHERE iv.rown >=1 and iv.rown <=3;

DELETE FROM itemview iv WHERE iv.code = 13; --�ȵ�
DELETE FROM item i WHERE i.code=13; -- ������ �� ���� ���̺��� �����ؾ���
ROLLBACK;
COMMIT;

SELECT p.* FROM purchase p ORDER BY p.no ASC;

SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;

--ȸ�� ���̺�� ȸ�� �ּ����̺��� ������
SELECT m.*, a.userno, a.useraddr, a.userpostcode FROM member m, memberaddr a WHERE m.userid=a.userid;

-- ������ ��ģ�� view�� ���� �������
CREATE OR REPLACE VIEW memberaddrview AS
SELECT m.*, a.userno, a.useraddr, a.userpostcode FROM member m, memberaddr a WHERE m.userid=a.userid;

-- �������̺� ��ȸ
SELECT mav.* FROM memberaddrview mav;

-- �����Ϸ��� ������ �������̺�ΰ��� ����
DELETE FROM memberaddr WHERE userno=1004;

SELECT p.* FROM purchase p;

-- ȸ�����̺�� ���ų��� ���̺� ��ħ
-- EQUI-JOIN(=���� ����=>�Ϲ����� ���ι��)
SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid;

-- ������ ��ģ ���̺��� �������̺�� �ϴ� �������
SELECT i.name, i.price, i.quantity, i.content, mp.* FROM item i, ( SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid)mp WHERE i.code = mp.code;

-- �����ִ°� ��� �� �� ���⶧���� view�� �������
-- view�� ��Ȱ���� �����ϴϱ� ���� �������̺��� ��Ȱ�� �Ұ�����
-- �̷��� �� �ڵ带 �ؼ��ϴ� ����� ���ʺ��� �ؼ��ϰ� ���� �ؼ�
-- INNER JOIN
CREATE OR REPLACE VIEW purchaseview AS
SELECT i.name, i.price, i.quantity, i.content, mp.* FROM item i, ( SELECT m.userid, m.username, m.userage, m.userphone, m.usergender, p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p WHERE m.userid = p.userid)mp WHERE i.code = mp.code;

SELECT pv.* FROM purchaseview pv WHERE cnt >= 5 ORDER BY regdate DESC;


