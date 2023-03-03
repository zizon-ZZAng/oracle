--��ü ��ȸ
--SELECT �÷��� FROM ���̺�� ��Ī;

SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;
SELECT i.* FROM item i;
SELECT p.* FROM purchase p;

--SEQUENCE �����
--CACHE 1001 1002 1003     �� ���������� .. 1021�� �� => NOCACHE�� ����
CREATE SEQUENCE seq_memberaddr_no INCREMENT BY 1 START WITH 1001 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_item_code INCREMENT BY 2 START WITH 1 NOMAXVALUE NOCACHE;
CREATE SEQUENCE seq_purchase_no INCREMENT BY 1 START WITH 10001 NOMAXVALUE NOCACHE;


--ȸ���ּҵ��
INSERT INTO memberaddr(userno , useraddr, userpostcode, userdate, userid)
VALUES(seq_memberaddr_no.NEXTVAL,'�λ�� ������',41257,CURRENT_DATE,'e');


--ȸ������
--INSERT INTO ���̺��(�÷����...) VALUES(�����߰��� ����);
INSERT INTO member(userid, userpw, username, userage, userphone, usergender, userdate)
VALUES( 'e','abcdef','��',5,'010-1456-4528','F',CURRENT_DATE);   --�����Լ� CURRENT_DATE

--��ǰ ���
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'����',1300,30,'����',CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'���ݸ�',15000,100,'����',CURRENT_DATE);
INSERT INTO item(code, name, price, quantity, content, regdate)VALUES(seq_item_code.NEXTVAL,'����',10000,3,'�ȳ�',CURRENT_DATE);

--�ֹ��ϱ�
--code�� �⺻����, userid�� member�� �⺻Ű
INSERT INTO purchase(no,cnt,regdate,code,userid)VALUES(seq_purchase_no.NEXTVAL,115,CURRENT_DATE,5,'c');


--�����ϱ�
--UPDATE ���̺�� SET �÷���=���氪 WHERE ����;
UPDATE member SET username='�󸶹�' WHERE userid='a';

--�̸��� ����, ���� ����
UPDATE item SET name='���̽�ũ��', price =13200, quantity =11 WHERE code=5;

--������ 3000�����ΰ͸� �������� 5000���� ����
UPDATE item SET quantity=5000 WHERE quantity<=3000;


--�߿��� ������ �������ٴ� null������ �����
UPDATE member SET userpw='', username='', userage=0, 
userphone='',usergender=null, userdate=null WHERE userid='a';

--�����ϱ�
--DELETE FROM ���̺� WHERE ����;
DELETE FROM item WHERE code =17;


COMMIT; --����(Ŀ�Ա����ؾ� ��Ȯ�� �� ����)
ROLLBACK; --�ǵ�����
