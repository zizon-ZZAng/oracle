package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Menu;
import dto.Restaurant;
import mapper.MenuMapper;
import mapper.RestaurantMapper;

class MenuMapperTest {
	
	MenuMapper mapper = MyBatisContext.getSqlSession().getMapper(MenuMapper.class);	
	
	//4. 해당식당의 메뉴 전체조회(가격 3자리마다 ,찍기, 등록일은 년월일만)
		@Test
		void selectMenuPriceComma() {
			List<Map<String, Object>> list = mapper.selectMenuPriceComma("051-153-3226");
			for (Map<String, Object> m : list) {
			System.out.println(m);
			
		}
		}
			
	//5. 할인률 0.3을 전달하면 4번 조회항목에 할인가격~컬럼을 추가하고 조회
	// 할인가격에는 소숫점이 있으면 버림으로 표시	
		@Test
		void selectMenuPriceDis() {
			List<Menu> list = mapper.selectMenuPriceDis(0.3f, "051-153-3226");
			for (Menu m : list) {
				System.out.println(m.toString());
				//
			
			
			
		}

}
}


