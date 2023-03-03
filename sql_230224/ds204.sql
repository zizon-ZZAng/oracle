SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m ORDER BY menuno ASC;
COMMIT; 

--�ֹ��ϱ�(�⺻Ű, �����̵�+�޴�������)
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 2 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 5 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 1 ,'eret@dfd.com', 1004);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 3 ,'ktng', 1023);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 8 ,'ktng', 1001);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 9 ,'eret@poi.com', 1022);
INSERT INTO ordertbl (ortherno, regdate, cnt, email, menuno)
VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, 9 ,'eret@poi.com', 1023);

--�ֹ���������
UPDATE ordertbl
SET cnt = 7
WHERE ortherno = 10001 AND email = 'eret@dfd.com';

--�ֹ�������ȸ
SELECT o.* FROM ordertbl o WHERE email = 'ktng';

--��+�޴�+�ֹ��� ������ �� �����
--CREATE OR REPLACE VIEW ordertblview AS
SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m;
-- �ϴ� �� Ȯ���غ���

CREATE OR REPLACE VIEW ordertblview AS
SELECT 
m.menuno mn , m.name menuname , m.price menuprice, m.retaurantphone rp, 
c.email cusemail,  c.phone cusphone, c.address cusadrs, 
o.ortherno odrno , o.regdate orrdate , o.cnt odrcnt
FROM menu m, customer c, ordertbl o 
WHERE c.email = o.email AND m.menuno = o.menuno;
-- view�� ������
-- SELECT �� FROM �� WHERE �� AND �� �Ἥ �ϴ� �޴� �� �ֹ��� �� ������ ���̺��� �̾ƺ�
-- �� ���� ���ʿ���(�ߺ��̳� �׷�)�� �����Ұǵ���
-- CREATE OR REPLACE VIEW ������ AS ����
-- SELECT�� view�� �ְ� ���� ���� ��(�̶� o���� c���� m���� �� ������)
--      FROM menu m, customer c, ordertbl o 
--      WHERE c.email = o.email AND m.menuno = o.menuno;
-- �־���

SELECT ov.* FROM ordertblview ov;
--�並 Ȯ���� (�ڹٷ� ����)



-------------------------
SELECT o.* FROM ordertbl o;
SELECT c.* FROM customer c;
SELECT m.* FROM menu m ORDER BY menuno ASC;
SELECT r.* FROM rider r;
SELECT d.* FROM delivery d;

COMMIT;

--����� ���
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1454-5323','�ھ�','djkls', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-6234-4243','�̾�','djkf111', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-2434-7894','����','dsf999', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-7234-6523','�Ѿ�','fghzh000', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1234-4589','�达','tyud99', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-1934-1211','����','ghs33', CURRENT_DATE );
INSERT INTO rider ( riderphone, name, password, regdate )
VALUES ( '010-8234-1848','R����','rgf44', CURRENT_DATE );


--��� ���
CREATE SEQUENCE seq_delivery_no INCREMENT BY 1 START WITH 10 NOMAXVALUE NOCACHE;

INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1454-5323', 10017 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-6234-4243', 10019 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-2434-7894', 10008 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-7234-6523', 10010 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1234-4589', 10011 );
INSERT INTO delivery ( deliveryno, regdate, riderphone, ortherno )
VALUES ( seq_delivery_no.NEXTVAL, CURRENT_DATE,'010-1934-1211', 10012 );
--����� �Ѹ� ��ȸ
SELECT r.* FROM rider r WHERE riderphone = '010-9234-1848';

--ordertblview + �Ĵ������� inner join�� ordertblview1 ����
CREATE OR REPLACE VIEW ordertblview1 AS
SELECT 
ov.*, r.name rtrname, r.address rtradr
FROM ordertblview ov, restaurant r WHERE ov.rp = r.retaurantphone;

-- ��� + ����ڸ� inner join�� deliveryview ����
CREATE OR REPLACE VIEW deliveryview AS
SELECT 
r.riderphone ridphone, r.name ridname, r.regdate ridregdate,
d.deliveryno dlvrno , d.regdate dlvrregdate, d.ortherno dlvrodrno 
FROM rider r, delivery d WHERE r.riderphone=d.riderphone ;


SELECT o.* FROM ordertblview o;
SELECT o1.* FROM ordertblview1 o1;
SELECT d.* FROM deliveryview d;
SELECT * FROM deliveryinfoview;
-- ordertblview1 + deliveryview�� inner join�� deliveryinfoview ����
CREATE OR REPLACE VIEW deliveryinfoview AS
SELECT ov1.*, dv.* FROM ordertblview1 ov1, deliveryview dv WHERE dv.dlvrodrno= ov1.odrno;

-----------------------------------------------------------
SELECT * FROM deliveryinfoview;
-- *deliveryinfoview�� �̿��� ��� ���ϱ�*
-- �޴��� ��ü �ֹ����� �� ��ü�ݾ�(� �޴��� �� �ȸ��°�?)
SELECT menuname, SUM(odrcnt), (menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY menuprice, menuname, odrcnt;

-- ���� ��ü �ֹ�Ƚ�� �� ��ü�ݾ�(� ���� vip�ΰ�?)
SELECT cusemail, SUM(odrcnt), SUM(menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY cusemail;

-- ��� �ð��뺰 �ֹ�Ƚ�� �� ��ü�ݾ�(��� �ð��뿡 �ֹ��� ���� �ϴ°�?)
SELECT TO_CHAR(dlvrregdate, 'HH24'), COUNT(*) odrcnt, SUM(menuprice * odrcnt) 
FROM deliveryinfoview GROUP BY TO_CHAR(dlvrregdate, 'HH24');

-- ����ں� ��ްǼ�(� ���̴��� ����� ���� �ߴ°�?)
SELECT ridphone, COUNT(*) odrcnt
FROM deliveryinfoview GROUP BY ridphone;

-- �Ĵ翬��ó�� ���޵Ǹ� �ش� �Ĵ��� �޴��� ��ü �ֹ� ����?(�츮���Կ��� � �޴��� ���� �߳�����)
SELECT menuname, SUM(odrcnt)
FROM deliveryinfoview WHERE rp = '051-153-3226' GROUP BY menuname;

-- ���Ϻ� �ֹ�Ƚ��
SELECT TO_CHAR( orrdate, 'day' ), COUNT(*) odrcnt
FROM deliveryinfoview GROUP BY TO_CHAR( orrdate, 'day' );


