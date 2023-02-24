package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import dto.Menu;

public interface MenuMapper {

	@Insert({
		" INSERT INTO menu (no, name, price, content, phone) ",
		" VALUES (seq_menu_no.NEXTVAL, #{name}, #{price}, #{content}, #{phone}) "
		})
	public int insertMenu (Menu menu);
	
	@Select({
		" SELECT m.* FROM menu m ORDER BY name ASC "
	})
	public List<Menu> selectMenuList();
	
	@Update({
		" UPDATE menu SET name = #{name}, price = #{price}, content = #{content} ", 
		" WHERE no = #{no} AND phone =#{phone} "
	})
	public int updateMenu( Menu menu );
	 
	@Delete({
		" DELETE FROM menu WHERE no = #{no} AND phone =#{phone} "
	})
	public int deleteMenu( Menu menu );
	
	@Select({
		" SELECT m.no, m.name, m.content,",
		" TO_CHAR(m.regdate, 'YYYY-MM-DD') regdate, ", 
		" TRIM (TO_CHAR(m.price, '999,999,999')) price ",
	    " FROM menu m "
	})
	public List <Map<String, Object>> selectMenuGenList();
	
	@Select({
		" SELECT m.*, ",
	    " TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate, ",
	    " TRIM (TO_CHAR(m.price, '999,999,999')) strprice, ",
	    " FLOOR(m.price-(m.price*#{discountRate})) discountprice ",
	    " FROM menu m WHERE phone = #{phone} "
	})
	public List <Menu> selectMenuListDiscount(
			@Param("discountRate") float discountRate,
			@Param("phone") String phone);
		
	// 이걸로 CONTENT내용 확인 가능!
	@Results({ 
		@Result( column = "CONTENT", property = "CONTENT", 
				javaType = String.class, jdbcType = JdbcType.CLOB)
	})
	
	// CLOB == Byte
	@Select({
		" SELECT m.*, ",
	    " TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate, ",
	    " TRIM (TO_CHAR(m.price, '999,999,999')) strprice, ",
	    " FLOOR(m.price -(m.price*#{discountRate})) discountprice ",
	    " FROM menu m WHERE phone = #{phone} "
	})
	public List <Map<String, Object>> selectMenuListDiscountMap(
			@Param("discountRate") float discountRate,
			@Param("phone") String phone);
	
}
