SELECT r.* FROM restaurant r ORDER BY retaurantphone ASC;
COMMIT;

INSERT INTO restaurant(retaurantphone, name, address, password)
    VALUES('051-4561-7891', 'SNRN', 'DJEL', 'QWERTYU'); 
    
--�Ĵ����� ����(�Ĵ��̸��̶� �ּҸ� �ٲܼ� ����) + �⺻Ű(phone)
UPDATE restaurant 
SET name = '�����ع���', address = '���屺' 
WHERE retaurantphone = '051-4561-7891';

--�Ĵ��й�ȣ����(������ ���)+ (�⺻Ű+�����ȣ)
UPDATE restaurant 
SET password = '5674'
WHERE retaurantphone ='051-4895-6123' AND password ='*****';

--�Ĵ�α��� (�⺻Ű, ��ȣ) => �Ĵ�������������
SELECT r.* FROM restaurant r 
WHERE retaurantphone = '051-4895-6123' AND password = '5674';

--�Ĵ�1����ȸ(�⺻Ű)
SELECT r.* FROM restaurant r 
WHERE retaurantphone = '051-4895-6123';

----------------------
--�������� : no������ ��� retaurantphone�� �Ĵ翡 �ִ°͸�
--�Ĵ��� �α��εǾ� �Ĵ翬��ó �˶��� �޴������ ����
SELECT m.* FROM menu m ORDER BY menuno ASC;
COMMIT;

--1. �޴����
INSERT INTO MENU(menuno, name, price, content, retaurantphone)
VALUES(seq_menu_no.NEXTVAL, '������', 45000, '���ְ�ż��ؿ�', '051-4561-7891');

--2. �޴�����
UPDATE MENU 
SET name = '�����и�', price = 5000, content = '������'
WHERE menuno = 1025 AND retaurantphone = '051-002-3926';

--3. �޴�����
DELETE FROM MENU WHERE menuno =1026 AND retaurantphone = '051-002-3926';

--4. �ش�Ĵ��� �޴� ��ü��ȸ(���� 3�ڸ����� ,���, ������� ����ϸ�)
SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, '999,999,999') strPrice
FROM MENU m 
WHERE m.retaurantphone = '051-153-3226' ORDER BY menuno ASC;

--5. ���η� 0.3�� �����ϸ� 4�� ��ȸ�׸� ���ΰ���~�÷��� �߰��ϰ� ��ȸ
-- ���ΰ��ݿ��� �Ҽ����� ������ �������� ǥ��
SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, '999,999,999') strPrice, FLOOR(m.price-(m.price* 0.3)) disPrice  
FROM MENU m WHERE m.retaurantphone = '051-153-3226' ORDER BY menuno ASC;

-----------------------------------------------------------------------
SELECT c.* FROM customer c;
COMMIT; 

--�� ȸ������
INSERT INTO customer(email, password, phone, address, chk)
VALUES('2wuiw', 'pw', '010-1230-9562', 'wheee', 1);

--�� �α���
SELECT c.* FROM customer c
WHERE email = '2wuiw' AND password = 'pw' ;

--�� ȸ����������
UPDATE customer
SET phone= '010-5698-1234', address= '���ϱ�'
WHERE email = '2wuiw' AND password = 'pw' ;

--�� ��ȣ����
UPDATE customer
SET password = 'pEW' 
WHERE email = '2wuiw' AND password = 'pw' ;

--�� ����
DELETE FROM customer WHERE email = 'eret@poii.com';

--�� ���� ������Ʈ �̿�
UPDATE customer



