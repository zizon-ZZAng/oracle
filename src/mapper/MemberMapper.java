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

import dto.Member2;

@Mapper
public interface MemberMapper {
	
	@Select({
		" { call proc_member_insert( ",	//프로시저라 select쓰는거임
				" #{ map.userid, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				" #{ map.userpw, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				" #{ map.username, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				" #{ map.userage, mode=IN, jdbcType=NUMERIC, javaType=Integer }, ",
				" #{ map.userphone, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				" #{ map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
				" #{ map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer } ",
		" )} "
		
	})
	
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberInsert(@Param("map") Map<String, Object> map);
	
//===========================================================================================
	
	
	@Results(id="memberMap")
	@ResultType(Member2.class)	
	@Select({
		" { call proc_member_select( ",
			" #{map.usergender, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{map.cursor, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet, resultMap=memberMap } ",
		" )} "
		
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcMemberSelect(@Param("map") Map<String, Object> map);
	
	
//============================================================================================
	
	//시퀀스가 없는 데이터 추가
	//for(Member obj:list){}
	@Insert({
		" <script> ",
		" INSERT ALL ",
			" <foreach collection = 'list' item='obj' separator=' '> ",
			    " INTO member(userid, userpw, username,userage,userphone, usergender, userdate) ",
			    " VALUES(#{obj.userid}, #{obj.userpw}, #{obj.username}, #{obj.userage}, #{obj.userphone}, #{obj.usergender}, CURRENT_DATE) ",
			" </foreach> ",
	    " SELECT * FROM DUAL ",
	    " </script> "
	})
	public int memberInsertBatch(@Param("list") List<Member2> list);
	
	
	//일괄 수정
	@Update({ 
		" <script> ",
		" UPDATE member SET ",
	    " username = (CASE ",
    		" <foreach collection = 'list' item = 'obj' separator=' '> ",
    			" WHEN userid=#{obj.userid} THEN #{obj.username} ",
           	" </foreach> ",
	    " END), ",
	    " userage = (CASE ",
	        " <foreach collection = 'list' item = 'obj' separator=' '> ",
	            " WHEN userid=#{obj.userid} THEN #{obj.userage} ",
	        " </foreach> ",
        " END) ",
        " WHERE userid IN ( ", 
	        " <foreach collection = 'list' item = 'obj' separator=','> ",
	        	" #{obj.userid} ",
	        " </foreach> ",
        " ) ",
        " </script> "
	})
	public int memberUpdateBatch(@Param("list") List<Member2> list);
	
	
	
	//해당 아이디가 있으면 update 없으면 insert 수행
	@Insert ({
		" MERGE INTO  member USING DUAL ",
	        " ON (userid=#{obj.userid}) ",
	        " WHEN MATCHED THEN ",
            	" UPDATE SET username=#{obj.username}, userage=#{obj.userage} ",
	        " WHEN NOT MATCHED THEN ",
	            " INSERT (userid, userpw, username, userage, userphone, usergender, userdate) ",
                " VALUES(#{obj.userid},#{obj.userpw},#{obj.username},#{obj.userage},#{obj.userphone},#{obj.usergender},CURRENT_DATE) "
	           
	})
	public int memberUpsert(@Param("obj") Member2 obj);
	
	
	// 바꾸고 싶지않은 값엔 0이나 null값넣어서 값 유지하기 한 번에 수정하기
	@Update({
		" <script> ",
		" UPDATE member SET username=#{obj.username} ", 
			" <if test='obj.userage != 0'> ",
				" , userage=#{obj.userage} ",	
			" </if> ",
			
			" <if test='obj.userphone != null'> ",
				" , userphone=#{obj.userphone} ",	
			" </if> ",
			
			" <if test='obj.usergender != null'> ",
				" , usergender=#{obj.usergender}  ",	
			" </if> ",
		" WHERE userid=#{obj.userid} ",
		" </script> "
	})
	public int memberUpdateOne(@Param("obj") Member2 obj);
	
	
	
	// 원하는 컬럼명의 값 조회?
	@Select({
		
		"SELECT * FROM member WHERE ${map.column} LIKE '%' || #{map.txt} || '%'"
	})
	public List<Member2> memberLikeList(@Param("map") Map<String, String> map);
	
//	"SELECT * FROM member WHERE ",  "(${map.column} LIKE '%' || #{map.txt} || '%') OR ",  "(${map.column1} LIKE '%' || #{map.txt1} || '%'"
	
}
	
	
	
	
	
	
