package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Menu;

@Mapper
public interface MenuMapper {

	@Insert({ " INSERT INTO menu(no, name, price, content, phone) ", 
			" VALUES(seq_menu_no.NEXTVAL, #{name}, #{price}, #{content}, #{phone}) " }) 
	public int insertMenu(Menu menu);
	
	@Select({" SELECT m.* FROM menu m ORDER BY name ASC "})
	public List<Menu> selectMenuList();

}
