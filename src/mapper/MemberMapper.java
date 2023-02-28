package mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface MemberMapper {
	// 프로시저 실행
	@Select({" { call proc_member_insert( ",
				 	  "#{ map.userid, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				 	  "#{ map.userpw, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				 	  "#{ map.username, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				 	  "#{ map.userage, mode=IN, jdbcType=NUMERIC, javaType=Integer }, ",
				 	  "#{ map.userphone, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				 	  "#{ map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				 	  "#{ map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer }",
			 " )} "})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberInsert(@Param("map") Map<String, Object> map);
}
