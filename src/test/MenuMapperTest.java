package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;


class MenuMapperTest {

	// 테스트 mapper객체 생성
	MenuMapper mapper 
			= MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
	
	@Test
	void selectNewMenuList() {
		
		// List<Menu> list = mapper.selectNewMenuList("051-110-1234");
		
		List<Menu> list = mapper.selectNewMenuList("051-110-1234");
		
		for(Menu m : list) {
			System.out.println(m.toString());
		}
	}

	@Test
	void selectMenuListDiscount() {
		
		// List<Menu> list = mapper.selectNewMenuList("051-110-1234");
		
		List<Menu> list = mapper.selectMenuListDiscount(0.3f, "051-110-1234");
		
		for(Menu m : list) {
			System.out.println(m.toString());
		}
	}
	
	// 키가 컬럼명(대문자), content의 내용이 확인안됨.
	@Test
	void selectMenuListDiscountMap() {
		
		//List<Map<String, Object> list = mapper.selectNewMenuList("051-110-1234");
		
		List<Menu> list = mapper.selectMenuListDiscount(0.3f, "051-110-1234");
		
		for(Menu m : list) {
			System.out.println(m.toString());
		}
	}
}
