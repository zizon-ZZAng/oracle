package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Menu;

@Mapper
public interface MenuMapper {

	//메뉴 등록
	@Insert({ " INSERT INTO menu(no, name, price, content, phone) ", 
			" VALUES(seq_menu_no.NEXTVAL, #{name}, #{price}, #{content}, #{phone}) " }) 
	public int insertMenu(Menu menu);
	
	//메뉴 출력
	@Select({" SELECT m.* FROM menu m ORDER BY name ASC "})
	public List<Menu> selectMenuList();
	
	
	//메뉴 수정
	@Update({" UPDATE menu SET name=#{name}, price=#{price}, content=#{content} ", 
			 " WHERE no=#{no} AND phone=#{phone} "})
	public int updateMenu(Menu menu);
	
	//메뉴 삭제
	@Delete({" DELETE FROM menu WHERE no=#{no} AND phone=#{phone} "})
	public int deleteMenu(Menu Menu);

	
	//메뉴 해당 식당 전체 메뉴 조회
	@Select({" SELECT m.no, m.name, TO_CHAR(m.price,'999,999')price,m.content, TO_CHAR(m.regdate, 'YYYY-MM-DD') regdate FROM menu m ", 
			 " WHERE m.phone=#{phone} "})
	public List<Map<String, Object>> selectRestaurantMenu(String phone);
	
	
	//  30% 할인율을 전달하면 discountPrice컬럼이 추가된 전체 메뉴 조회
	@Select({" SELECT m.no, m.name, TO_CHAR(m.price,'999,999')price,m.content, TO_CHAR(m.regdate, 'YYYY-MM-DD') regdate m.phone,(m.price*(discountprice=#{discountprice})) discountPrice FROM menu m ", 
	 		 " WHERE m.phone=#{phone} "})
	public List<Map<String, Object>> selectDiscountPrice(long discountprice, String phone);
	
	
}
