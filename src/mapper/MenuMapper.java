package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Menu;

@Mapper
public interface MenuMapper {

	
	@Insert({
		" INSERT INTO menu(no, name, price, content, phone) ",
		" VALUES(seq_menu_no.NEXTVAL,#{name},#{price},#{content},#{phone}) "
	})
	public int insertMenu(Menu m);
	
	
	
	@Select({
		" SELECT m.* FROM menu m "
	})
	public List<Menu> selectMenuList();
	
	
	
	@Select({
		" SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999') strPrice, TO_CHAR(m.regdate,'YYYY-MM-DD') strRegdate ",
		" FROM menu m ",
		" WHERE phone=#{phone} "	
	})
	//public Map<Menu<String, Object>> selectNewMenuList();
	public List<Menu> selectNewMenuList(String phone);
	
	
	
	@Select({
		" SELECT m.no, m.name, TO_CHAR(m.price,'999,999') strprice, ", 
		" TO_CHAR(m.regdate,'YYYY-MM-DD') strregdate, ",
		" TRUNC(m.price*(1-#{discountRate})) discountPrice ",
		" FROM menu m WHERE phone=#{phone} "
	})
	public List<Menu> selectMenuListDiscount(@Param("discountRate") float discountRate, @Param("phone") String p);
	
	
	
	@Update({
		" UPDATE menu SET name=#{name}, price=#{price}, content=#{content} ",
		" WHERE no=#{no} AND phone=#{phone} "
	})
	public int updateMenu(Menu menu);
	
	
	
	@Delete({
		" DELETE FROM menu WHERE no=#{no} AND phone=#{phone} "
	})
	public int deleteMenu(Menu menu);
	
}
