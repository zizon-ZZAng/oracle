
--�л����̺�
-- �й�, �̸�, ���ǽ�, ����, ����, ����, �������
CREATE TABLE student (
    no      NUMBER PRIMARY KEY,
    name    VARCHAR2(20),
    class   CHAR(1),    --A,B,C (CHAR�� ������ ���̿� �°� 1,2,3  3���� �����ϰ� A�� �Է����� �� ������ ���ڸ� ������ �״ϱ� �ߺ�����)
    kor     NUMBER(3),
    eng     NUMBER(3),
    math    NUMBER(3),
    regdate TIMESTAMP DEFAULT CURRENT_DATE
);

--��ü��ȸ
SELECT s.* FROM student s;

--�л����̺� no(��ȣ) ������ ���� 
CREATE SEQUENCE seq_student_no START WITH 1 INCREMENT BY 1 NOMAXVALUE NOCACHE;

-- ���
INSERT INTO student(no, name, class, kor, eng, math) VALUES(seq_student_no.NEXTVAL, '��ö��ö��', 'K', 100, 100, 100);


COMMIT; --�����Ű��
ROLLBACK; --�ǵ�����

--------------------------------------------------------------------------------

--���ǽ� ���̺�
CREATE TABLE classroom (
    code    CHAR(1) PRIMARY KEY,    --A,B,C,D,E..���
    room    VARCHAR2(10),
    teacher VARCHAR2(20),
    regdate TIMESTAMP DEFAULT CURRENT_DATE
);

--��ü��ȸ
SELECT c.* FROM classroom c;

COMMIT;
ROLLBACK;

--���
-- �ϳ��ϳ� ����ϰ� commit�ϰ� 
INSERT INTO classroom(code, room, teacher) VALUES('A','201ȣ','����1');
INSERT INTO classroom(code, room, teacher) VALUES('B','301ȣ','����2');
INSERT INTO classroom(code, room, teacher) VALUES('C','202ȣ','����3');
INSERT INTO classroom(code, room, teacher) VALUES('D','401ȣ','����4');
INSERT INTO classroom(code, room, teacher) VALUES('E','503ȣ','����5');

-- 3���� �ϰ������� �߰��� �� commit (������ ���� �� �� ����� �����)
INSERT ALL
    INTO classroom(code, room, teacher) VALUES('F','201ȣ','����6')
    INTO classroom(code, room, teacher) VALUES('G','203ȣ','����7')
    INTO classroom(code, room, teacher) VALUES('H','105ȣ','����8')
SELECT * FROM DUAL;

-------------------------------------------------------------------------------

-- JOIN
-- ǥ������ �Ǿ��ִ°� ���� 

--inner join => ������
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code; -- ��� SQL���� ������ �Ұ����� �� ����
SELECT s.*, c.* FROM student s INNER JOIN classroom c ON s.class=c.code; -- ��� SQL���� ���� ������, ANSI ǥ��

-- ���� 80���̻��� ����� ���
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code AND s.kor >= 80;
SELECT s.*, c.* FROM student s INNER JOIN classroom c ON s.class=c.code WHERE s.kor >= 80; -- �� where�� ��� where��������

--right outer join => classroom�� ��� �� = student�� ��ġ�ϴ� �� ������ null
SELECT s.*, c.* FROM student s, classroom c WHERE s.class(+)=c.code;
SELECT s.*, c.* FROM student s RIGHT OUTER JOIN classroom c ON s.class=c.code; --ǥ��

-- left outer join => student�� ��� �� = classroom�� ��ġ�ϴ� ���� ������ null
SELECT s.*, c.* FROM student s, classroom c WHERE s.class=c.code(+);
SELECT s.*, c.* FROM student s LEFT OUTER JOIN classroom c ON s.class=c.code; -- ǥ��

-- full outer join => student�� classroom �� �� ��� ���� ������
-- ǥ�ظ� �����ϴ�
SELECT s.*, c.* FROM student s, classroom c WHERE s.class(+)=c.code(+); -- ����
SELECT s.*, c.* FROM student s FULL OUTER JOIN classroom c ON s.class=c.code; -- ǥ��


