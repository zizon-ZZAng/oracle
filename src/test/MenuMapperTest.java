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
	
	@Test
	void insertMenu() {
		Menu menu = new Menu();
		menu.setName("나가사키 짬뽕");
		menu.setContent("존맛");
		menu.setPrice(12000L);
		menu.setPhone("051-000-0005");
		
		int ret = mMapper.insertMenu(menu);
		System.out.println(menu);
	}
	
	@Test
	void updateMenu() {
		Menu menu = new Menu();
		menu.setNo(1019L);
		menu.setName("닝닝ㄴ");
		menu.setContent("존맛");
		menu.setPrice(15000L);
		menu.setPhone("051-000-0008");
		
		int ret = mMapper.updateMenu(menu);
		System.out.println(ret);
	}
	
	@Test
	void selectMenuGenList() {
		List<Map<String, Object>> list = mMapper.selectMenuGenList();
		for(Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	// content를 type으로 만들면 확인됨.
	@Test
	void selectMenuListDiscount() {
		List<Menu> list = mMapper.selectMenuListDiscount(0.3f, "051-000-0008");
		for(Menu obj : list) {
			System.out.println(obj.toString());
		}
	}

	// 키가 컬럼명(대문자), CONTENT의 내용 확인 불가.
	@Test
	void selectMenuListDiscountMap() {
		List<Map<String, Object>> list = mMapper.selectMenuListDiscountMap(0.3f, "051-000-0008");
		for(Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
}
