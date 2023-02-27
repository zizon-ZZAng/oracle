package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Ordertbl;

public interface OrdertblMapper {

	@Insert({
		" INSERT INTO ordertbl ( no, cnt, email, menuno) ",
		" VALUES(seq_ordertbl_no.NEXTVAL, #{cnt}, #{email}, #{menuno}) "
	})
	public int insertOrdertbl (Ordertbl obj);
	
	@Update({
		" UPDATE ordertbl SET cnt = #{cnt} ",
		" WHERE no = #{no} AND email = #{email} AND menuno = #{menuno}"
	})
	public int updateOrdertbl (Ordertbl obj);
	
	@Select({
		" SELECT ov.* FROM ordertblview ov WHERE email = #{email} "
	})
	public List<Ordertbl> selectOrdertblList (String email);
	
	
}
