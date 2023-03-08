package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member2;

@Mapper
public interface MemberMapper {
	
	
<<<<<<< Updated upstream
	//회원가입
	@Insert({" INSERT INTO member2(id, name, gender, password, address) ", 
			 " VALUES(#{obj.id},#{obj.name},#{obj.gender},#{obj.password},#{obj.address}) "})
	public int signUpMember(@Param("obj") Member member);
	
=======
	@Results(id = "memberMap")
	@ResultType(Member2.class)
	@Select({
		" { call proc_member_select( ",
			" #{map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{map.cursor, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet, resultMap=memberMap } ",
		" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberSelect( @Param("map") Map<String, Object> map );
	
	// for(Member obj : list) { }
	@Insert({
		" <script> ",
	    " INSERT ALL ",
	    	" <foreach collection='list' item='obj' separator=' '> ", // foreach == 반복문
	    		" INTO member(userid, userpw, username, userage, userphone, usergender, userdate) ",
	    		" VALUES (#{obj.userid}, #{obj.userpw}, #{obj.username}, #{obj.userage}, #{obj.userphone}, #{obj.usergender}, CURRENT_DATE) ",
	        " </foreach> ",
	    " SELECT * FROM DUAL ",
	    " </script> "
	})
	public int memberInsertBatch(@Param("list") List<Member2> list);
>>>>>>> Stashed changes
	
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
		" WHERE id=#{obj.id} AND password=#{obj.password} ",
		" </script> "
	})
<<<<<<< Updated upstream
	public int updateMember(@Param("obj") Member member);
	
	
	//비밀번호변경
	@Update({"UPDATE member2 SET password=#{obj.newpw} WHERE id=#{obj.id} AND password=#{obj.password}"})
	public int updatePWMember(@Param("obj") Member member);
	
	
	//회원탈퇴
	@Update({" UPDATE member2 SET name=null, password=null, address=null, gender=null, chk=0, regdate=null ", 
			 " WHERE id=#{obj.id} AND password=#{obj.newpw} "})
	public int unMember(@Param("obj") Member member);
=======
	public int memberUpdateBatch(@Param("list") List<Member2> list);
	
	@Insert({
		" MERGE INTO member USING DUAL ",
	    " ON (userid=#{obj.userid}) ",
	    " WHEN MATCHED THEN ",
	        " UPDATE SET username = #{obj.username}, userage = #{obj.userage} ",
	    " WHEN NOT MATCHED THEN ",
	       " INSERT (userid, userpw, username, userage, userphone, usergender, userdate) ",
	            " VALUES (#{obj.userid}, #{obj.userpw}, #{obj.username}, #{obj.userage}, #{obj.userphone}, #{obj.usergender}, CURRENT_DATE) "
	})
	public int memberUpsert(@Param("obj") Member2 obj);
	
	@Update({
		" <script> ",
		" UPDATE member SET username = #{obj.username} ",
			" <if test = 'obj.userage !=0'> ",
				" , userage = #{obj.userage} ",
			" </if> ",
			
			" <if test = 'obj.userphone !=null'> ",
				" , userphone = #{obj.userphone} ",
			" </if> ",
			
			" <if test = 'obj.usergender !=null'> ",
				" , usergender = #{obj.usergender} ",
			" </if> ",
		"WHERE userid = #{obj.userid} ",
		" </script> "
			
	})
	public int memberUpdateOne (@Param("obj") Member2 obj);
	
	@Select({
		" SELECT m.* FROM member m WHERE ${map.column} ",
		" LIKE '%' || #{map.txt} || '%' "
	})
	public List<Member2> memberLikeList(
			@Param("map") Map<String, Object> map);


	public int unMember(Member2 member);
>>>>>>> Stashed changes
}
