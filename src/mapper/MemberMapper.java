package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member;

public interface MemberMapper {
	
	// 고객 회원가입
	@Insert({" INSERT INTO member1(id, name, password, sex, address, chk) ",
			 " VALUES(#{id}, #{name}, #{password}, #{sex}, #{address}, #{chk}) "})
	public int insertMember(Member m);
	
	// 고객 로그인
	@Select({" SELECT m.* ",
			 " FROM member1 m ",
			 " WHERE id = #{id} AND password = #{password} "})
	public Member loginMember(Member m);
	
	// 고객 회원정보수정
	@Update({
		" <script> ",
		" UPDATE member1 SET name=#{obj.name} ",
			" <if test='obj.password != null'> ",
				" ,password=#{obj.password} ",
			" </if> ",
			
			" <if test='obj.sex != null '> ",
				" , sex=#{obj.sex} ",
			" </if> ",
			" <if test='obj.address != null '> ",
				" , address=#{obj.address} ",
			" </if> ",
			" WHERE id=#{obj.id} ",
			" </script> "
		
	})
	public int member1UpdateOne(@Param("obj") Member obj);
	
	// 고객 암호 변경
	@Update({" UPDATE member1 ",
			 " SET password = #{newPassword} ",
			 " WHERE id = #{id} AND password = #{password} "})
	public int updateMemberPW(Member m);	
}
