-- ���ǽ�, �μ�
SELECT c.* FROM CLASSROOM c;
SELECT c.* FROM CLASSROOM1 c;

-- �л�, ���
SELECT s.* FROM STUDENT s;
SELECT s.* FROM STUDENT1 s;


-- 1. ���̺� ���� CLASSROOM1
-- CODE CHAR(1) PK, ROOM VARCHAR2(20)


CREATE TABLE CLASSROOM1(
    code CHAR(1) PRIMARY KEY,
    room VARCHAR2(20)
);


-- 2. ���̺� �ڷ� �߰�
-- CODE A,B,C,D // ROOM 301ȣ, 302ȣ, 303ȣ, 304ȣ
INSERT ALL INTO CLASSROOM1(code,room)
VALUES ( 'A', '301ȣ')
INTO CLASSROOM1(code,room)
VALUES ( 'B', '302ȣ')
INTO CLASSROOM1(code,room)
VALUES ( 'C', '303ȣ')
INTO CLASSROOM1(code,room)
VALUES ( 'D', '304ȣ')
SELECT*FROM DUAL;
COMMIT;


-- 3. �л����̺� ���� STUDENT1
-- NO NUMBER PK
-- NAME VARCHAR2(20) NOT NULL
-- CODE CHAR(1)
-- KOR, ENG, MATH NUMBER(3)
-- GRADE VARCHAR2(10) 1�г�, 2�г�, 3�г�, 4�г⸸ ����


CREATE TABLE STUDENT1(
    no NUMBER PRIMARY KEY, 
    name VARCHAR2(20) NOT NULL,
    code CHAR(1),
    kor NUMBER(3),
    eng NUMBER(3),
    math NUMBER(3),
    grade VARCHAR2(10)
);

ALTER TABLE student1 ADD CONSTRAINT CHK_student1_grade  
    CHECK (grade IN ('1�г�','2�г�','3�г�','4�г�'));   
    
    
-- 4. �л����̺��� CODE�� ���ǽ� CODE�� �ܷ�Ű �������� (�������Ǹ� : fk_student1_code)
ALTER TABLE student1
  ADD CONSTRAINT fk_student1_code
    FOREIGN KEY (code)
    REFERENCES  classroom1(code);
    
    
-- 5. ������ ���� seq_student1_no ���۰�101 ������1
CREATE SEQUENCE seq_student1_no
INCREMENT BY 1
START WITH 101 NOMAXVALUE NOCACHE;


-- 6. �������� �̿��� �л� 5�� �߰�
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'�̸�1','A',30,30,30,'1�г�');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'�̸�2','B',50,60,70,'2�г�');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'�̸�3','C',60,70,70,'3�г�');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'�̸�4','D',70,80,90,'4�г�');
INSERT INTO student1(no,name,code,kor,eng,math,grade)
VALUES(seq_student1_no.nextval,'�̸�5','D',90,90,100,'4�г�');
COMMIT;


-- 7. �л����� ���� ��ȸ, �� �л��� ������ 250~300 A, 200~249 B, �������� C (�й�, �̸�, ����, ����, ����, ����)
DECLARE
    total_grade CHAR(1):= null;
BEGIN
    SELECT no, name, code, kor, eng, math, total = kor+eng+math 
    FROM student1
    GROUP BY no;
    
    IF total >= 250  AND total <= 300 THEN
        total_grade := 'A';
    ELSIF total >= 200 AND total <= 249 THEN
        total_grade := 'B';
    ELSE
        total_grade := 'C';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE(total_grade);
END;
/


SELECT s1.*, (kor+eng+math) total,
    CASE
        when( (kor+eng+math) >= 250  AND (kor+eng+math) <= 300) THEN 'A'
        when( (kor+eng+math) >= 200  AND (kor+eng+math) <= 249) THEN 'B'
        ELSE 'C'
    END total_grade
FROM student1 s1;



-- 8. �г⺰ �������� �հ� ���ϱ�
SELECT grade, SUM(math) 
FROM student1
GROUP BY grade ORDER BY grade ASC;


-- ����, ����
DELETE FROM student1 WHERE no=106;
rollback;

UPDATE student1 SET name = '�����̸�' WHERE no = 106;
select * from student1;
commit;


-- 9. �Լ�����, ��������ȣ�� ��ȯ�ϴ� �Լ� �����ϱ� FUNC_NEXTVAL
CREATE OR REPLACE FUNCTION func_nextval RETURN NUMBER
IS
BEGIN
    RETURN seq_student1_no.NEXTVAL;
EXCEPTION WHEN OTHERS THEN
    RETURN null;
END;
/

SELECT func_nextval FROM DUAL;


--=======================================================================================
-- ������Ʈ ��ų �� ���� �� ���� �ϳ��� ������Ʈ ��ų ��...? �ڹٿ��� ������ �Ἥ ������Ʈ ��Ű�� ��.
select * from member;

UPDATE member SET username='a', userage=111, userphone='010', usergender = 'M' WHERE userid='a';
UPDATE member SET username='a', userage=111, userphone='010' WHERE userid='a';
UPDATE member SET username='a', userage=111 WHERE userid='a';
UPDATE member SET username='a' WHERE userid='a';
COMMIT;


-- ���̺���̳� �÷��� ������ �� �� ����.(�ڹٿ���)
SELECT m.* FROM member m WHERE userid LIKE '%' || 'a' || '%';
SELECT m.* FROM member m WHERE username LIKE '%' || 'a' || '%';
SELECT m.* FROM member m WHERE userpw LIKE '%' || 'a' || '%';




