package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member;
import dto.Member2;

@Mapper
public interface Member2Mapper {
	
	
	//회원가입
	@Insert({" INSERT INTO member2(id, name, gender, password, address) ", 
			 " VALUES(#{obj.id},#{obj.name},#{obj.gender},#{obj.password},#{obj.address}) "})
	public int signUpMember(@Param("obj") Member2 member);
	
	
	//로그인
//	@Select({" SELECT id, name  FROM member2 WHERE id=#{id} AND password=#{password} "})
//	public Member loginMember(@Param("id") String id, @Param("password") String password);
	
	//로그인
	@Select({" SELECT *  FROM member2 WHERE id=#{id} AND password=#{password} "})
	public Member2 loginMember(Member2 member);
	
	
	//회원정보변경
	@Update({
		
//		"UPDATE member2 SET name=#{obj.name}, address=#{obj.address}, gender=#{obj.gender} WHERE id=#{obj.id} AND password=#{obj.password}"
		
		" <script> ",
		" UPDATE member2 SET name=#{obj.name} ",
			" <if test='obj.address != null'> ",
				" , address=#{obj.address} ",	
			" </if> ",
		" WHERE id=#{obj.id} AND password=#{obj.password} ",
		" </script> "
	})
	public int updateMember(@Param("obj") Member2 member);
	
	
	//비밀번호변경
	@Update({"UPDATE member2 SET password=#{obj.newpw} WHERE id=#{obj.id} AND password=#{obj.password}"})
	public int updatePWMember(@Param("obj") Member2 member);
	
	
	//회원탈퇴
	@Update({" UPDATE member2 SET name=null, password=null, address=null, gender=null, chk=0, regdate=null ", 
			 " WHERE id=#{obj.id} AND password=#{obj.newpw} "})
	public int unMember(@Param("obj") Member2 member);
}
