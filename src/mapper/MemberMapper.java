package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member;

@Mapper
public interface MemberMapper {
	
	
	//회원가입
	@Insert({" INSERT INTO member2(id, name, gender, password, address) ", 
			 " VALUES(#{obj.id},#{obj.name},#{objgender},#{obj.password},#{obj.address}) "})
	public int signUpMember(@Param("obj") Member member);
	
	
	//로그인
	@Select({" SELECT m.id, m.password FROM member2 m WHERE id=#{obj.id} AND password=#{password} "})
	public Member loginMember(@Param("obj") Member member);
	
	
	//회원정보변경
	@Update({" UPDATE member2 SET name=#{name}, address=#{address}, gender=#{gender} WHERE id=#{id} AND password=#{password} "})
	public int updateMember(Member member);
	
	
	//비밀번호변경
	@Update({"UPDATE member2 SET password=#{newpw} WHERE id=#{id} AND password=#{password}"})
	public int updatePWMember(Member member);
	
	//회원탈퇴
	@Update({"UPDATE member2 SET name=#{name}, "})
	public int unMember(Member member);
	
	
	
}
