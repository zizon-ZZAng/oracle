--SELECT �÷��� FROM ���̺�� ��Ī;
SELECT i.*  FROM item i;
SELECT i.code, i.name, i.quantity FROM item i;
SELECT i.* FROM item i ORDER BY i.code DESC;
SELECT i.* FROM item i WHERE i.price >=10000 AND i.price <=20000 ORDER BY i.code DESC;




SELECT i.* FROM item i WHERE i.code in(1,3,15) ORDER BY i.code DESC;
SELECT i.* FROM item i WHERE i.code=1 OR i.code=3 OR i.code=15 ORDER BY i.code DESC;




--WHERE �÷��� LIKE '�ƹ��ų�'|| '�����Ұ�'|| '�ƹ��ų�';
SELECT i.* FROM item i WHERE i.name LIKE '%' || '��' || '%';
SELECT i.*, i.price*i.quantity FROM item i WHERE i.name LIKE '%' || '��' || '%';



--ȸ�����̺��� ���̰� 10~30�� ���̵� ���������ؼ� ��ȸ
select m.* from member m;
SELECT m.* FROM member m WHERE m.userage>=10 AND m.userage<=30 ORDER BY m.userid ASC;




--1.��ü��ȸ (���̺��� ������ �������)
--2. ����(where)���� �ʿ��� ���� 
--3. ���ϴ� ���� �������� ��ȸ(order by)
--4. ������ �����͸� �ʿ��� ���·� ����(to_char�� ���� �����Լ�)




--�Լ��� ����� �׽�Ʈ�Ҷ� ��� SELECT * FROM ���̺�
--���밪 �Լ�
SELECT ABS(-2) FROM DUAL;
SELECT CURRENT_DATE FROM DUAL;



--�������� �Լ� TO_CHAR
SELECT TO_CHAR(123456789,'999,999,999') FROM DUAL;
SELECT TO_CHAR(i.price*i.quantity,'999,999,999') total FROM item i WHERE i.name LIKE '%' || '��' || '%';
SELECT TO_CHAR(CURRENT_DATE,'DL HH:MI:SS') FROM DUAL;



SELECT m.* FROM member m;
SELECT m.* FROM member m WHERE m.userage >= 6 ORDER BY m.username ASC;

SELECT m.userid, m.username, m.userphone, m.userdate, m.userage, m.usergender,
    TO_CHAR(m.userdate, 'YYYY-DD-MM HH') userdate1 
    FROM member m WHERE m.userage >= 6 
    ORDER BY m.username ASC;
  
  
  
    
--8�� �� �ִ� 1�� ���ϱ�
SELECT i.*, MAX(i.price) FROM item i ORDER BY i.code DESC; -- ������
SELECT MAX(i.price) FROM item i ORDER BY i.code DESC; --1���� ����

SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) FROM item i ORDER BY i.code DESC;




--select�� ������ ������ ������ ���̺�
SELECT m.* FROM member m;
SELECT a.* FROM memberaddr a;

SELECT m.*, a.* FROM member m, memberaddr a WHERE m.userid = a.userid;


CREATE OR REPLACE VIEW memberaddrview AS
SELECT m.*, a.userno, a.useraddr, a.userpostcode
    FROM member m, memberaddr a WHERE m.userid = a.userid;


DELETE FROM memberaddr WHERE userno=1001;
COMMIT;

SELECT mav.* FROM memberaddrview mav;


--���̺� �ΰ��� ��ġ�� VIEW ���餩��
SELECT m.* FROM member m;
SELECT p.* FROM purchase p;

SELECT m.*, p.no, p.cnt, p.code, p.regdate FROM member m, purchase p
    WHERE m.userid = p.userid;


CREATE OR REPLACE VIEW purchaseview AS
SELECT i.name,i.price,i.quantity, i.content, mp.* FROM item i, 
(
    SELECT 
            m.userid, m.username, m.userage, m.userphone, m.usergender,
            p.no, p.cnt, p.regdate, p.code
    FROM member m, purchase p
        WHERE m.userid= p.userid
    ) mp WHERE  i.code= mp.code;
    
    
SELECT pv.* FROM purchaseview pv WHERE cnt>=100 ORDER BY regdate DESC;
    

--������ ����
 --������ �����Ϳ� �߰������� ���� �Ұ���
SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i WHERE i.rown>=1 ORDER BY i.code DESC;





--������ �����͸� �ٽ� ������ �� ������ ���̺� ����
SELECT i1.* FROM(
    SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i ORDER BY i.code DESC )i1 WHERE i1.rown>=1 AND i1.rown<=5;
    
    
    

--view �����ϱ�( �����Ҷ� ���)
--VIEW�� SELECT�� ���������� DELETE �Ұ���(����(item)���� ��������)
--view�� ������ ���������ۿ� �ȵ�
CREATE OR REPLACE VIEW itemview AS
SELECT i.*, ROW_NUMBER() OVER (ORDER BY i.code ASC) rown
    FROM item i ORDER BY i.code DESC;
    

SELECT iv.* FROM itemview iv WHERE iv.rown>=1 AND iv.rown<=5;




--���Ἲ ��������(DS228.FK_ITEM_TO_PURCHASE)�� ����Ǿ����ϴ�- �ڽ� ���ڵ尡 �߰ߵǾ����ϴ�
DELETE FROM item i WHERE i.code = 3;






