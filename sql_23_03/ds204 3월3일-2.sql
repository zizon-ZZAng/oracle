SELECT * FROM MEMBER;
--- ���̺� �÷��� ���� ������ ������Ʈ�ϰ� ������ ������Ʈ ���� �ʱ�
-- �����κ��� if���� ���� ó�� ��
UPDATE member SET username='a',  userage=12,  userphone='M',  userid='a'; 
UPDATE member SET username='a',  userage=12,  userphone='M'; 
UPDATE member SET username='a',  userage=12; 
UPDATE member SET username='a'; 
-- (�ڹ� ����)

--- ���̺� �÷����� ������ �� ��� �˻��ϱ�
-- member1,2,3 �̷��� username userage �̷��� ���� ������ ���  '%'||'A'||'%' ��� ���ڳ� ���ڰ� �ִ��� �˻��ϱ�
-- ${map.colunmn} ?
SELECT * FROM member m where userid LIKE  '%'||'A'||'%'  
