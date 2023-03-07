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
			 " VALUES(#{obj.id},#{obj.name},#{obj.gender},#{obj.password},#{obj.address}) "})
	public int signUpMember(@Param("obj") Member member);
	
	
	//로그인
//	@Select({" SELECT id, name  FROM member2 WHERE id=#{id} AND password=#{password} "})
//	public Member loginMember(@Param("id") String id, @Param("password") String password);
	
	//로그인
	@Select({" SELECT *  FROM member2 WHERE id=#{id} AND password=#{password} "})
	public Member loginMember(Member member);
	
	
	//회원정보변경
	@Update({
		
//		"UPDATE member2 SET name=#{obj.name}, address=#{obj.address}, gender=#{obj.gender} WHERE id=#{obj.id} AND password=#{obj.password}"
		
		" <script> ",
		" UPDATE member2 SET name=#{obj.name} ",
			" <if test='obj.address != null'> ",
				" , address=#{obj.address} ",	
			" </if> ",
			
			" <if test='obj.gender != null'> ",
				" , gender=#{obj.gender}  ",	
			" </if> ",
		" WHERE id=#{obj.id} AND password=#{obj.password} ",
		" </script> "
		
	})
	public int updateMember(@Param("obj") Member member);
	
	
	//비밀번호변경
	@Update({"UPDATE member2 SET password=#{obj.newpw} WHERE id=#{obj.id} AND password=#{obj.password}"})
	public int updatePWMember(@Param("obj") Member member);
	
	
	//회원탈퇴
	@Update({" UPDATE member2 SET name=null, password=null, address=null, gender=null, chk=0, regdate=null ", 
			 " WHERE id=#{obj.id} AND password=#{obj.newpw} "})
	public int unMember(@Param("obj") Member member);
}
