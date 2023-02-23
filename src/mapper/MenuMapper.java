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
//	// 메뉴 등록
//	@Insert({" INSERT INTO menu(no, name, price, content, phone) ",
//			 " VALUES(seq_menu_no.NEXTVAL, #{name}, #{price}, #{content}, #{phone}) "})
//	public int insertMenu(Menu menu);
//	
//	// 전체 조회
//	@Select({" SELECT m.* FROM menu m ORDER BY no "})
//	public List<Menu> selectMenuList();
//	
//	// 수정
//	@Update({" UPDATE menu ",
//			 " SET name = #{name}, price = #{price}, content = #{content} ", 
//			 " WHERE no = #{no} AND phone = #{phone} "})
//	public int updateMenu(Menu menu);
//	
//	// 삭제
//	@Delete({" DELETE FROM menu ",
//			 " WHERE no = #{no} AND phone = #{phone} "})
//	public int deleteMenu(Menu menu);
	
	// -----------------------------------------------------------------------------------
	
	// 1. 메뉴 등록
	@Insert({" INSERT INTO menu(no, name, price, content, phone) ",
			 " VALUES(seq_menu_no.NEXTVAL, #{name}, #{price}, #{content}, #{phone}) "})
	public int insertMenu(Menu menu);
	
	// 2. 메뉴 변경
	@Update({" UPDATE menu ",
			 " SET name = #{name}, price = #{price}, content = #{content} ",
			 " WHERE no = #{no} AND phone = #{phone} "})
	public int updateMenu(Menu menu);
	
	// 3. 메뉴 삭제
	@Delete({" DELETE FROM menu ",
		 	 " WHERE no = #{no} AND phone = #{phone} "})
	public int deleteMenu(Menu menu);
	
	// 4. 해당 식당의 전체 메뉴 조회 (가격에 3자리 콤마 추가, 등록일은 년월일만 표시)
	@Select({" SELECT m.*, TO_CHAR(m.price, 'FM999,999,999') strprice, TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate ",
			 " FROM menu m ",
			 " WHERE phone = #{phone} "})
	public List<Map<String, Object>> selectMenuList(String phone);
	
	// 5. 할인률 ex) 0.3을 전달하면 4번 조회항목에서 discountPrice 컬럼이 추가된 메뉴 조회
	@Select({" SELECT m.*, TO_CHAR(m.price, 'FM999,999,999') strprice, TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate, ",
	 	 	 " TRUNC(m.price*(1-#{percent})) discountPrice ",
	 	 	 " FROM menu m ",
	 	 	 " WHERE phone = #{phone} "})
	public List<Menu> selectDiscountPrice(@Param("phone") String phone, 
									  	  @Param("percent") float percent);
	
	@Results({ // Map으로 출력시 CLOB 데이터타입인 content가 안보이는 문제 해결
		@Result(column = "CONTENT", property = "aaa", jdbcType = JdbcType.CLOB, javaType = String.class)

	})
	@Select({" SELECT m.*, TO_CHAR(m.price, 'FM999,999,999') strprice, TO_CHAR(m.regdate, 'YYYY-MM-DD') strregdate, ",
			 " TRUNC(m.price*(1-#{percent})) discountPrice ",
		 	 " FROM menu m ",
		 	 " WHERE phone = #{phone} "})
	public List<Map<String, Object>> selectDiscountPriceMap(@Param("phone") String phone, 
														 	@Param("percent") float percent);
}
