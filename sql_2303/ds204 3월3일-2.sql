SELECT * FROM MEMBER;
--- 테이블 컬럼에 값이 있으면 업데이트하고 없으면 업데이트 하지 않기
-- 가변부분이 if문에 의해 처리 됨
UPDATE member SET username='a',  userage=12,  userphone='M',  userid='a'; 
UPDATE member SET username='a',  userage=12,  userphone='M'; 
UPDATE member SET username='a',  userage=12; 
UPDATE member SET username='a'; 
-- (자바 오류)

--- 테이블 컬럼명을 변수로 다 잡고 검색하기
-- member1,2,3 이렇고 username userage 이런걸 전부 변수로 잡고  '%'||'A'||'%' 라는 문자나 숫자가 있는지 검색하기
-- ${map.colunmn} ?
SELECT * FROM member m where userid LIKE  '%'||'A'||'%'  
