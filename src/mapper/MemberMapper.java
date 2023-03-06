package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member;

public interface MemberMapper {
	// 고객 회원가입
	@Insert({" INSERT INTO Member(id, name, password, sex, address, chk) ",
			 " VALUES(#{id}, #{name}, #{password}, #{sex}, #{address}, #{chk}) "})
	public int insertMember(Member m);
	
	// 고객 로그인
	@Select({" SELECT m.* ",
			 " FROM member m ",
			 " WHERE id = #{id} AND password = #{password} "})
	public Member loginMember(@Param("id") String id, @Param("password") String password);
	
	// 고객 회원정보수정
	@Update({" UPDATE member ",
			 " SET phone = #{phone}, address = #{address} ",
			 " WHERE id = #{id} AND password = #{password} "})
	public int updateMember(Member m);
	
	// 고객 암호 변경
	@Update({" UPDATE member ",
			 " SET password = #{newPassword} ",
			 " WHERE id = #{id} AND password = #{password} "})
	public int updateMemberPW(Member m);	
}
