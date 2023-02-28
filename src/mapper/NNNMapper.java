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
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.apache.ibatis.mapping.StatementType;

import dto.NNN;
@Mapper
public interface NNNMapper {

	@Select({
		" { call proc_nnn_insert( ",
			" #{ map.id, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.name, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.pw, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.age, mode=IN, jdbcType=NUMERIC, javaType=Integer }, ",
			" #{ map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer } ",
			" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcNNNInsert(@Param("map") Map<String, Object> map);
	//반환값이 없음
	
	@Select({
		" { call proc_nnn_upsert( ",
			" #{ map.id, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.name, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.pw, mode=IN, jdbcType=VARCHAR, javaType=String }, ",
			" #{ map.age, mode=IN, jdbcType=NUMERIC, javaType=Integer }, ",
			" #{ map.ret, mode=OUT, jdbcType=NUMERIC, javaType=Integer } ",
			" )} "
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcNNNUpsert(@Param("map") Map<String, Object> map);
	//반환값이 없음
	
	
	
	@Results(id = "NNNMap")
	@ResultType(NNN.class)
	@Select({
		" { call proc_nnn_select( ",
			" #{ map.name, mode=IN, jdbccType=VARCHAR, javaType=String }, ",
			" #{ map.cursor, mode=OUT, jdbType=CURSOR, javaType=java.sql.ResultSet } ",
			" resultMap=nnnMap } ",
			" )} "
			//오라클에서 보이는 건 대문자 OUT CURSOR ...
	})
	@Options(statementType = StatementType.CALLABLE)
	public void callProcNNNSelect(@Param("map") Map<String, Object> map);
	//반환값이 없음
	
	
	
	
	@Insert({
		//for(nnn obj : list)
		" <script> " 
		, " INSERT ALL "
			, " <foreach collection = 'list' item = 'obj' separator=' '> " //separator 구분자 쿼리문과 쿼리문 사이에 뭐가 있나
				, " INTO nnn(id, name, pw, age, mdate) "
				, " VALUES (#{obj.id},#{obj.name},#{obj.pw},#{obj.age},CURRENT_DATE) "
			, " </foreach> "
		, " SELECT * FROM DUAL "
		, " </script> "
	})
	public int nnnInsertBatch(@Param ("list") List<NNN> list);
}
