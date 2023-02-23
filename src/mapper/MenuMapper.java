package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

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
	@Select({" SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999,999')price,m.content, TO_CHAR(m.regdate, 'YYYY-MM-DD') regdate FROM menu m ", 
			 " WHERE m.phone=#{phone} "})
	public List<Map<String, Object>> selectRestaurantMenu(String phone);
	
	
	//  N% 할인율을 전달하면 discountPrice컬럼이 추가된 전체 메뉴 조회(2)
	@Select({" SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999,999')price, m.content, TO_CHAR(m.regdate, 'YYYY-MM-DD') regdate, m.phone, FLOOR((m.price * #{discountRate})) discountPrice FROM menu m ", 
	 		 " WHERE m.phone=#{phone} "})
	public List<Map<String, Object>> selectDiscountPrice(@Param("discountRate") float discountprice, @Param("phone") String phone);
	
	//param은 하나일 땐 자기가 어디들어갈지 아니깐 안해도 되는데 값이 두개가 들어가야할 경우 어디 들어갈지 명시해주는 느낌?임 그래서 들어갈 값이랑 똑같은 이름으로 명시해줘야함
	
	
	//오라클에서 CLOB는 바이트를 의미함
	//FM붙이면 앞에 여백없앨 수 있음
	//N% 할인율을 전달하면 discountPrice컬럼이 추가된 전체 메뉴 조회 (2)
	@Results({
		@Result(column="CONTENT", property="content",javaType=String.class, jdbcType=JdbcType.CLOB)}) // 오라클 CLOB를 이클립스에선 String으로 바꿔서 출력
	@Select({" SELECT m.no, m.name, TO_CHAR(m.price,'FM999,999,999')strprice, m.content, TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate, m.phone, TRUNC((m.price * #{discountRate})) discountPrice FROM menu m ", 
		 " WHERE m.phone=#{phone} "})
	public List<Map<String, Object>> selectDiscountPrice2(@Param("discountRate") float discountRate, @Param("phone") String phone);
	
	
	
}
