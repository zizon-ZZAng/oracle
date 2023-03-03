--��ü ��ȸ
--SELECT �÷��� FROM ���̺�� ��Ī;

SELECT m.* FROM member m;   --ȸ��

SELECT ma.* FROM memberaddr ma; --ȸ���ּ�

SELECT i.* FROM item i; --��ǰ

SELECT p.* FROM purchase p; --�ֹ�

COMMIT; 

--�� �߰�(ȸ������)
--INSERT INTO ���̺��(�÷����) VALUES(���� �߰��� ����);
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('a','a','������',12,'010-0000-0000','F',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('b','b','�ٳ���',21,'010-0001-0002','M',CURRENT_DATE);      

INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('c','c','��ѱ�',8,'010-9999-9999','F',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('d','d','������',30,'010-3143-1431','M',CURRENT_DATE);
            
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate) 
            VALUES('e','d','������',30,'010-3143-1431','M',CURRENT_DATE);

-- ���� �߰��ϰ� ���� COMMIT or ROLLBACK�� �� ����� �Ѵ�.

--����
COMMIT;     -- �����ϸ� ������ �غ�

--�ǵ�����
ROLLBACK;   --commit�� �����ؾ� rollback�� �ص� �������� ����

--------------------------------------------------------------------------------

--������ ���� (memberaddr)
CREATE SEQUENCE seq_memberaddr_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;

--�� �߰�(ȸ���ּҵ��)
INSERT INTO memberaddr(userid, userdate, userpostcode, useraddr, userno) 
VALUES('c',CURRENT_DATE, 55789, '�λ���',seq_memberaddr_no.NEXTVAL);

COMMIT; --����

--------------------------------------------------------------------------------

--������ ���� (item)
CREATE SEQUENCE seq_item_code INCREMENT BY 2 START WITH 1 NOMAXVALUE NOCACHE;

--�� �߰�(item ���)
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '���', 15020, 100, '��ŭ', CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '�������', 2000, 10, 'ź��', CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate) VALUES(seq_item_code.NEXTVAL, '����', 18900, 50, '�ż�', CURRENT_DATE);

COMMIT;

--------------------------------------------------------------------------------

--������ ����
CREATE SEQUENCE seq_purchase_no INCREMENT BY 1 START WITH 10001 NOMAXVALUE NOCACHE;

--�� �߰�(purchase ���)
-- code�� item�� �⺻Ű, userid�� member�� �⺻Ű
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 5, CURRENT_DATE,1, 'a');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 10, CURRENT_DATE,3, 'b');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 2, CURRENT_DATE,5, 'c');
INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 1, CURRENT_DATE,1, 'd');

COMMIT;

--------------------------------------------------------------------------------

--�����ϱ� 
--UPDATE ���̺�� SET �÷���=���氪, �÷���=���氪 WHERE ����;
UPDATE member SET username='���콺' WHERE userid='a';

COMMIT; -- ��ȸ�ϰ� �� �ٲ�� commit 

UPDATE member SET username='������' WHERE userid='b';

UPDATE item SET name='û���', price=7800, quantity='7008' WHERE code='1';


-- ������ 3000�����ΰ͸� �������� 5000���� ����
UPDATE item SET quantity=5000 WHERE quantity <= 3000;

--------------------------------------------------------------------------------

--�����ؾ���
--�����ϱ�
--DELETE FROM ���̺� WHERE ����;

DELETE FROM item WHERE code=11;

COMMIT;

--------------------------------------------------------------------------------
--ȸ�� ������ �����ϴ� ��� (����α��ϸ� ��)
UPDATE member SET userpw='', username='', userage=0, userphone='', usergender=null, userdate=null WHERE userid='e';

