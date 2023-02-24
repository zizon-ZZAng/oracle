package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Ordertbl;

@Mapper
public interface OrdertblMapper {

	@Insert({
		" INSERT INTO ordertbl(no, cnt, email, menuno, regdate) ",
		" VALUES(seq_ordertbl_no.NEXTVAL, #{cnt}, #{email}, #{menuno}, CURRENT_DATE) "	
	})
	public int insertOrdertbl(Ordertbl obj);
	
	@Select({
		" SELECT o.* FROM ordertbl o ", 
		" WHERE no=#{obj.no} AND email=#{obj.email} AND menuno=#{obj.menuno} "
	})
	public Ordertbl selectOrdertbl(@Param("obj") Ordertbl obj);
	
	@Update({
		" UPDATE ordertbl SET cnt=#{obj.cnt} ",
		" WHERE no=#{obj.no} AND email=#{obj.email} AND menuno=#{obj.menuno} "
	})
	public int updateOrdertbl(@Param("obj") Ordertbl obj);
	
	@Select({
		" SELECT * FROM customerOrderMenuview "
	})
	public List<Map<String, Object>> selectOrdertblView();
}
