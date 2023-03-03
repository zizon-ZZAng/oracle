
--��ü��ȸ
SELECT m.* FROM member m;   --ȸ��
SELECT i.* FROM item i;     --��ǰ
SELECT p.* FROM purchase p; --����

SELECT pv.* FROM purchaseview pv; --ȸ��+��ǰ+���� view

INSERT INTO member(userid, userpw, username,userage,userphone,usergender,userdate) VALues('k','k','ȸ��2',18,'010-0000-0008','F',CURRENT_DATE);
INSERT INTO member(userid, userpw, username,userage,userphone,usergender,userdate) VALues('l','l','ȸ��3',31,'010-0000-0009','F',CURRENT_DATE);

INSERT INTO item(code, name,price,quantity,content,regdate) VALUES(seq_item_code.NEXTVAL, '���ġŲ', 22000, 10, '������', CURRENT_DATE);

INSERT INTO purchase(no, cnt, regdate, code, userid) VALUES(seq_purchase_no.NEXTVAL, 77, CURRENT_DATE, 23 ,'k' );


COMMIT;
ROLLBACK;



-- ����1) �ֹ��ݾ��� �÷��߰�(total)
SELECT pv.*, TO_CHAR(price*cnt, '999,999,999') total FROM purchaseview pv;


-- ����2) �������� �÷��߰�(quantitycnt)
SELECT pv.*, (quantity-cnt) quantitycnt FROM purchaseview pv;


-- ����3) ���ɴ뺰 �÷��߰� (age) 0~9 => 0, 10~19 =>1, 20~29 => 2

SELECT 
    pv.*,
    FLOOR(userage/10) age
FROM 
    purchaseview pv;

SELECT 
    pv.*,
    CASE
    WHEN(pv.userage >= 0 and pv.userage < 10)  THEN 0
    WHEN(pv.userage >= 10 and pv.userage < 20)  THEN 1
    WHEN(pv.userage >= 20 and pv.userage < 30)  THEN 2
    WHEN(pv.userage >= 30 and pv.userage < 40)  THEN 3
    WHEN(pv.userage >= 40 and pv.userage < 50)  THEN 4
    WHEN(pv.userage >= 50 and pv.userage < 60)  THEN 5
    WHEN(pv.userage >= 60 and pv.userage < 70)  THEN 6
    WHEN(pv.userage >= 70 and pv.userage < 80)  THEN 7
    WHEN(pv.userage >= 80 and pv.userage < 90)  THEN 8
    ELSE 9
    END age
FROM 
    purchaseview pv;
    

--purchaseview1 VIEW ����
CREATE OR REPLACE VIEW purchaseview1 AS
SELECT 
    pv.*,
    pv.price*pv.cnt total, 
    (pv.quantity - pv.cnt) quantitycnt,
    FLOOR(userage/10) age
FROM 
    purchaseview pv;
    
--��ü��ȸ    
SELECT pr1.* FROM purchaseview1 pr1;


--����1) ���ɴ뺰 �ֹ����� �հ�
SELECT 
    pr1.age,
    SUM(pr1.cnt) sum 
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.age
ORDER BY 
    pr1.age ASC;


--����2) ��ǰ�� �ֹ�����
SELECT 
    pr1.code,
    pr1.name,
    pr1.price,
    SUM(pr1.cnt) csum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.code, 
    pr1.name,
    pr1.price
ORDER BY 
    pr1.code ASC;


--����3) ���� �ֹ�Ƚ��, �ֹ��ݾ��հ�
--count�� ���μ��� ���ϴ� ��

SELECT 
    pr1.usergender,
    COUNT(*) cnt,
    TO_CHAR(sum(pr1.total),'999,999,999' ) psum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.usergender;


--����4) �ð��뺰 �ֹ����� 0�� 1�� 2�� 23��

SELECT
    TO_CHAR(pr1.regdate, 'HH24') regdate,
    SUM(pr1.cnt) sum
FROM 
    purchaseview1 pr1
GROUP BY 
    TO_CHAR(pr1.regdate, 'HH24');


--����5) �ֹ������� 2�� �̻��� �ֹ��� ���ɴ뺰 �ֹ�����, ��� ���űݾ�

SELECT 
    pr1.age,
    SUM(pr1.cnt) cnt,
    AVG(pr1.total) avg
FROM 
    purchaseview1 pr1
WHERE
    pr1.cnt >= 2
GROUP BY 
    pr1.age
ORDER BY 
    pr1.age ASC;


--����6) ��ǰ�� �ֹ����� ������ 3�� �̻��� �� �ֹ������հ�
SELECT 
    pr1.code,
    pr1.name,
    sum(pr1.cnt) quantitysum
FROM 
    purchaseview1 pr1
GROUP BY 
    pr1.code,
    pr1.name
HAVING 
    SUM(cnt) >=3
ORDER BY
    pr1.code ASC;


    

