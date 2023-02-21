package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
