package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import dto.Member;

@Mapper
public interface MemberMapper {

	@Select({
		" { call proc_member_insert( ",
				"#{map.userid, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.userpw, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.username, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.userage, mode=IN, jdbcType=NUMERIC, javaType=Integer},",
				"#{map.userphone, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer}",
		" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberInsert( @Param("map") Map<String, Object> map);
	
	
	@Select({
		" { call proc_member_upsert( ",
				"#{map.userid, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.userpw, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.username, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.userage, mode=IN, jdbcType=NUMERIC, javaType=Integer},",
				"#{map.userphone, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String},",
				"#{map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer}",
		" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberUpsert( @Param("map") Map<String, Object> map);

	
	
	@Results(id = "memberMap")
	@ResultType(Member.class)
	@Select({
		" { call proc_member_select( ",
			" #{map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{map.cursor, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet, resultMap=memberMap }",
		" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberSelect( @Param("map") Map<String, Object> map );
	
	
	@Insert({
		" <script> ",
		" INSERT ALL ",
			// foreach : 반복문
			// separator : 하나의 인설트into 구문이 끝난 후 어떤 문자가 있는지 만약 , 가 있으면 ,를 넣을 것
			" <foreach collection='list' item='obj' separator=' '> ",
	    	" INTO member(userid, userpw, username, userage, userphone, usergender, userdate) ",
	    		" VALUES (#{obj.userid},#{obj.userpw},#{obj.username},#{obj.userage},#{obj.userphone},#{obj.usergender},CURRENT_DATE) ",
	    	" </foreach> ",
	    " SELECT * FROM DUAL ",
	    " </script> "
	})
	public int memberInsertBatch(@Param("list") List<Member> list);
	
	
	
	@Update({
		" <script> ",
		" UPDATE member SET ",
	    " username = (CASE ",
	        " <foreach collection='list' item='obj' separator=' '> ",
	    		" WHEN userid=#{obj.userid} THEN #{obj.username} ",
	        " </foreach> ",
	    " END), ",
	    " userage = (CASE ",
	    	" <foreach collection='list' item='obj' separator=' '> ",
	    		" WHEN userid=#{obj.userid} THEN #{obj.userage} ",
	    	" </foreach> ",
	    " END) ",
	    " WHERE userid IN( ",
	    	" <foreach collection='list' item='obj' separator=','> ",
	    		" #{obj.userid} ",
	    	" </foreach> ",
	    " ) ",
	    " </script> "
	})
	public int memberUpdateBatch( @Param("list") List<Member> list);
	
	
	
	@Insert({
		" MERGE INTO member ",
		" USING DUAL ",
	    " ON (userid=#{obj.userid}) ",
	    " WHEN MATCHED THEN ",
	        " UPDATE SET username=#{obj.username}, userage=#{obj.userage} ",
	    " WHEN NOT MATCHED THEN ",
	        " INSERT (userid, userpw, username, userage, userphone, usergender, userdate) ",
	            " VALUES(#{obj.userid},#{obj.userpw},#{obj.username},#{obj.userage},#{obj.userphone},#{obj.usergender},CURRENT_DATE) "
	})
	public int memberMerge(@Param("obj") Member obj);
	
	
	
	@Update({
		" <script> ",
		" UPDATE member SET username=#{obj.username} ",
			" <if test='obj.userage != 0'> ",
				", userage=#{obj.userage} ",
			" </if> ",
			
			" <if test='obj.userphone != null'> ",
				", userphone=#{obj.userphone} ",
			" </if> ",
			
			" <if test='obj.usergender != null'> ",
				", usergender=#{obj.usergender} ",
			" </if> ",
		" WHERE userid=#{obj.userid} ",
		" </script> "
	})
	public int memberUpdateOne(@Param("obj") Member obj);
	
	
	
	@Select({
		" SELECT m.* FROM member m WHERE ${map.column} ",
		" LIKE '%' || #{map.txt} || '%' "
	})
	public List<Member> memberLikeList( @Param("map") Map<String, String> map);
	
	
	
}
