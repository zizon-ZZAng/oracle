package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;

class MenuMapperTest {
	MenuMapper mMapper 
		= MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
	
	// 1. 메뉴 등록
	@Test
	void insertMenu() {
		Menu m = new Menu();
		m.setName("음식2");
		m.setPrice(25000L);
		m.setContent("내용2");
		m.setPhone("051-000-0004");
		
		System.out.println(mMapper.insertMenu(m));
	}
	
	// 2. 메뉴 변경
	@Test
	void updateMenu() {
		Menu m = new Menu();
		m.setName("음식11");
		m.setPrice(20000L);
		m.setContent("내용11");
		m.setNo(1009L);
		m.setPhone("051-000-0010");
		
		System.out.println(mMapper.updateMenu(m));
	}
	
	// 3. 메뉴 삭제
	@Test
	void deleteMenu() {
		Menu m = new Menu();
		m.setNo(1009L);
		m.setPhone("051-000-0010");
		System.out.println(mMapper.deleteMenu(m));
	}
	
	// 4. 해당 식당의 전체 메뉴 조회 (가격에 3자리 콤마 추가, 등록일은 년월일만 표시)
	@Test
	void selectMenuList() {
		List<Map<String, Object>> list = mMapper.selectMenuList("055-111-2222");
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 5. 할인률 ex) 0.3을 전달하면 4번 조회항목에서 discountPrice 컬럼이 추가된 메뉴 조회
	@Test
	void selectDiscountPrice() {
		List<Menu> list = mMapper.selectDiscountPrice("051-000-0005", 0.3f);
		for (Menu m : list) {
			System.out.println(m.toString());
		}
	}
	
	@Test
	void selectDiscountPriceMap() { // Map => 키가 컬럼명(대문자), CONTENT의 내용이 확인 X
		List<Map<String, Object>> list = mMapper.selectDiscountPriceMap("051-000-0005", 0.3f);
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
}
