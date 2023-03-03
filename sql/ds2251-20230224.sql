SELECT c.* FROM customer c;
SELECT o.* FROM ordertbl o;
SELECT m.* FROM menu m;
SELECT d.* FROM delivery d;
commit;

-- �⺻Ű�� ������ ����, ����� 1���̰ų� ���ų�
-- �ؽ� ��й�ȣ �ܹ��� : �ٽ� �ǵ��� �� �� ����...?
SELECT c.* FROM customer c WHERE c.email='javaid';


-- �ֹ��ϱ�(�⺻Ű, �����̵�+�޴��� ����)
INSERT INTO ordertbl(no, cnt, email, menuno)
VALUES(seq_ordertbl_no.NEXTVAL, 1,'DJSKFJ9@naver.com', 1022); 


-- �ֹ����� ����
UPDATE ordertbl SET cnt=3
WHERE no=100006 AND email = 'DJSKFJ2@naver.com' AND menuno = 1014;


-- �ֹ�������ȸ
SELECT o.* FROM ordertbl o 
WHERE no=100006 AND email = 'DJSKFJ2@naver.com' AND menuno = 1014;


-- ��+�޴�+�ֹ��� ������ �� �����
CREATE OR REPLACE VIEW customerOrderMenuview AS

SELECT o.no orderNo,o.regdate orderRegdate, o.cnt, o.menuno,
m.name, m.price, m.phone,
c.email, c.phone customerPhone, c.address, c.chk  
FROM ordertbl o, menu m, customer c
WHERE c.email = o.email AND m.no = o.menuno;
commit;

--=====================================================
SELECT r.* FROM rider r;
SELECT d.* FROM delivery d;


-- ����� ���
INSERT INTO rider( phone, name, regdate, password )
VALUES ( '010-0001-0000', '������̸�', CURRENT_DATE, '��ȣ'); 
commit;


-- ����� 1�� ��ȸ
SELECT r.* FROM rider r WHERE phone='010-0001-0000';


-- ��� ��� (�⺻Ű, �ܷ�Ű 2��)
INSERT INTO delivery(no, regdate, phone, orderno)
VALUES ( seq_delivery_no.nextval, CURRENT_DATE, '010-0001-0009',100009);
commit;


-- ordertblview + �Ĵ������� inner join�� ordertblview1
SELECT c.* From customerOrderMenuview c;

CREATE OR REPLACE VIEW ordertblview1 AS
SELECT c.*, r.name restName, r.address restAddress FROM customerOrderMenuview c
INNER JOIN restaurant r ON r.phone = c.phone;


-- ��� + ����ڸ� inner join�� deliveryview ����
CREATE OR REPLACE VIEW deliveryview AS
SELECT d.orderno, d.no, d.regdate, r.phone, r.name FROM delivery d
INNER JOIN rider r ON d.phone = r.phone;

DELETE FROM delivery WHERE phone='010-0001-0009';



-- ordertblview1 + deliveryview�� inner join�� deliveryinfoview����
CREATE OR REPLACE VIEW deliveryinfoview AS
SELECT ov.*, dv.no, dv.phone riderPhone, dv.name riderName, dv.regdate
FROM ordertblview1 ov
INNER JOIN deliveryview dv ON dv.orderno = ov.orderno;



-- *** deliveryinfoview�� �̿��� ��� ���ϱ� ***
-- �޴��� ��ü �ֹ� ���� �� ��ü�ݾ� (� �޴��� ���ȸ��°�?)
-- ���� ��ü �ֹ� Ƚ�� �� ��ü�ݾ� (� ���� vip�ΰ�?)
-- �ֹ� �ð��뺰 �ֹ�Ƚ�� �� ��ü�ݾ� (��� �ð��뿡 �ֹ��� ���� �ϴ°�?)
-- ��޽ð��뺰 ��� Ƚ�� (��� �ð��뿡 ����� ���� �ϴ°�?)
-- ����ں� ��ްǼ� (��� ��ޱ�簡 ����� ���� �ߴ°�?)
-- �Ĵ翬��ó�� ���޵Ǹ� �ش� �Ĵ��� �޴��� ��ü �ֹ� ����? (�츮 ���Կ��� � �޴��� �߳�����?)
