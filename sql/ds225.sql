-- Ȯ�ο�
SELECT r.* FROM restaurant r ORDER BY phone ASC;

-- �Ĵ���
INSERT INTO restaurant(phone, name, address, password)
    VALUES('051-000-1000','�Ͻ�','�λ��ؿ�뱸','d');
COMMIT;


-- �Ĵ���������(�Ĵ��̸�, �ּ�)
UPDATE restaurant r SET r.name='�̸�����', r.address='�ּҺ���' 
WHERE r.phone='051-110-1236';


-- �Ĵ��й�ȣ����(������ ��й�ȣ) + ���� : �⺻Ű, �����ȣ ��ġ�� ��� ��й�ȣ ���� ����
UPDATE restaurant SET password = '��й�ȣ����'
WHERE password='��������' AND phone='051-110-1236';


-- �Ĵ� 1�� ��ȸ(�⺻Ű)
SELECT r.* FROM restaurant r WHERE phone='051-110-1200'; 


-- �Ĵ� �α���(�⺻Ű, ��ȣ) => �Ĵ����� ��������
SELECT r.* FROM restaurant r WHERE phone='051-000-1234' AND password ='b';

--===========================================================================
-- �������� : no���������, phone�� �Ĵ翡 �ִ� �͸�
-- �ܷ�Ű�� �ִ� ���� �α��� �ؼ� �ؾ���.
-- �Ĵ��� �α��εǾ ����ó�� �� �� �ִ� ��Ȳ�� �Ǿ�� �޴� ����� ����.
SELECT m.* FROM menu m ORDER BY no ASC;
commit;
rollback;

-- 1. �޴����
INSERT INTO menu(no, name, price, content, regdate, phone)
    values(seq_menu_no.NEXTVAL, '���ü�Ʈ', 13000, '����Ư��', CURRENT_DATE,'051-110-1200');
    

-- 2. �޴� ����
UPDATE menu SET name='�޴�����', price=3000, content='���溯�溯��'
WHERE no = 1032 AND phone='051-000-1234';


-- 3. �޴� ����
DELETE FROM menu WHERE no = 1032 AND phone = '051-000-1234';


-- 4. �ش� �Ĵ��� ��ü �޴� ��ȸ(���ݿ� 3�ڸ� �޸� �߰�, ������� ����ϸ� ǥ��)
SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999') price, 
TO_CHAR(m.regdate,'YYYY-MM-DD') regdate
FROM menu m 
WHERE phone = '051-110-1234'
ORDER BY m.no ASC;


-- 5. ���η� 0.3�� �����ϸ� 4�� ��ȸ�׸񿡼� discountPrice�÷��� �߰��� �޴� ��ȸ
-- discountPrice�� �Ҽ����� ������ �������� ǥ��
CREATE OR REPLACE VIEW discountmenu AS

SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999') price, 
TO_CHAR(m.regdate,'YYYY-MM-DD') regdate, TO_CHAR(TRUNC(m.price*(1-0.3)),'FM999,999') discountPrice
FROM menu m 
WHERE phone = '051-110-1234'
ORDER BY m.no ASC;


SELECT c.* FROM customer c;


-- �� ȸ������
INSERT INTO customer(email, password, phone, address, chk)
    VALUES('DJSKFJ10@naver.com', '���¾�ȣ�Դϴ�.', '010-0000-0001','�ּ��Դϴ�.',1);
COMMIT;


-- �� �α���
SELECT c.*
FROM customer c
WHERE c.email = 'DJSKFJ10@naver.com' AND c.password = '���¾�ȣ�Դϴ�.';


-- �� ȸ����������
UPDATE customer SET phone = '011-0000-0001', address = '���Һ�������'
WHERE email = 'DJSKFJ10@naver.com' AND password = '���¾�ȣ�Դϴ�.';


-- �� ��ȣ ����
UPDATE customer SET password = '��ȣ�������ϰھ��.'
WHERE email = 'DJSKFJ10@naver.com' AND password = '���¾�ȣ�Դϴ�.'; 

COMMIT;
