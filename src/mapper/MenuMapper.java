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
	
	

	// 메뉴등록
	@Insert
	({ "INSERT INTO MENU( menuno, name, price, content, retaurantphone ) " , 
		 "VALUES( seq_MENU_no.NEXTVAL, #{name}, #{price}, #{content}, #{retaurantphone} ) "
		
		})
	
	public int insertMenu(Menu obj);
	
	@Select
	({"SELECT m.* FROM MENU m ORDER BY m.menuno ASC"
	})
	public List<Menu> selectMenu();
	
	
	@Update({
		"UPDATE MENU SET name=#{name}, price=#{price}, content=#{content} "
		+ "WHERE menuno = #{menuno} AND retaurantphone=#{retaurantphone} "

		
	})
	public int updateMenu(Menu menu);
	
	@Delete({
		" DELETE FROM menu WHERE menuno = #{menuno} AND retaurantphone=#{retaurantphone} "

	})
	public int deleteMenu(Menu menu);
	
	
	//-------------------------------------------------------------------
	
	// 2/23

	//4. 해당식당의 메뉴 전체조회(가격 3자리마다 ,찍기, 등록일은 년월일만)
	//오라클에서 clob는 byte
	@Results({
		@Result( column = "CONTENT", property = "CONTENT",
				javaType = String.class, jdbcType = JdbcType.CLOB)
	}) // Map<S , O> 에서 column의 CLOB을 String으로 바꿔준다
	@Select
	({
		" SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, '999,999,999') strPrice "
		, " FROM MENU m WHERE m.retaurantphone = '051-153-3226' ORDER BY menuno ASC "
	})
	public List<Map<String, Object>> selectMenuPriceComma(String phone);

	// 5. 할인률 0.3을 전달하면 4번 조회항목에 할인가격~컬럼을 추가하고 조회
	@Select
	({
		" SELECT m.*, TO_CHAR(m.regdate, 'YYYY-MM-DD') strRegdate, TO_CHAR(m.price, 'FM999,999,999') strPrice, FLOOR(m.price-(m.price*#{disRate})) disPrice"
		, "FROM MENU m WHERE m.retaurantphone = #{retaurantphone} ORDER BY menuno ASC "
	})
	public List<Menu> selectMenuPriceDis(
			@Param("disRate") float dR, 
			@Param("retaurantphone") String p);
}
