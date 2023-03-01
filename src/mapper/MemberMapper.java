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
	
}
