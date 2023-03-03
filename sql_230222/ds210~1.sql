
--��ü��ȸ (�л� ���̺�)
SELECT s.* FROM student s;
--��ü��ȸ (���ǽ� ���̺�)
SELECT c.* FROM classroom c;


-- INNER JOIN => VIEW�� ����
CREATE OR REPLACE VIEW studentview AS 
    SELECT s.*, c.room, c.teacher FROM student s INNER JOIN classroom c ON s.class=c.code;


--��ü ��ȸ (view�� ���� student+classroom ���̺�)    
SELECT sv.* FROM studentview sv;


--����1) class�� A,B�ΰ͸� ��ȸ
SELECT sv.* FROM studentview sv WHERE class='A' OR class='B';
SELECT sv.* FROM studentview sv WHERE class IN('A', 'B');

--����2) total �÷��� �������� �߰��Ǿ� �����հ� ���ϱ�
-- (+��� ���ϱ�, �������ϱ�)
-- CASE END�� if�� ����
-- +view ��������
CREATE OR REPLACE VIEW studentview1 AS
SELECT 
    sv.*, sv.kor+sv.eng+sv.math total, 
    ROUND((sv.kor+sv.eng+sv.math)/3, 1) avg,
    CASE
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3, 1) >= 90)THEN 'a'
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3,1) >= 80)THEN 'b'
        WHEN(ROUND((sv.kor+sv.eng+sv.math)/3,1) >= 70)THEN 'c'
        ELSE 'd'
    END grade
FROM 
    studentview sv;
    
--��ü��ȸ
SELECT sv1.* FROm studentview1 sv1;


--����3) ����1, ����3�� �ƴѰ͸� ��ȸ <> ����
SELECT sv.* FROM studentview sv WHERE teacher <> '����1' and teacher <> '����3';
SELECT sv.* FROM studentview sv WHERE teacher NOT IN('����1', '����3');


-- �ִ�, �ּҰ� ���ϱ� (�̰� ��ü��ȸ�� �ϸ� �ȵ�)
-- ��ü�����Ϳ��� �����ϰ� �׷���� where�� �Ἥ ����

SELECT 
    sv1.class,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.class;
    

-- ��ü�����Ϳ��� �����ϰ� �׷���� �� �ٽ� ���͸� �� �� having ����
SELECT 
    sv1.class,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.class
HAVING
    COUNT(sv1.total) > 1;

    
    
--�������� ������
SELECT 
    sv1.grade,
    MAX(sv1.total) max, 
    MIN(sv1.total) min, 
    SUM(sv1.total) sum, 
    round(AVG(sv1.total),1) avg, 
    COUNT(sv1.total) cnt 
FROM 
    studentview1 sv1 
WHERE
    sv1.kor>=70
GROUP BY 
    sv1.grade;


