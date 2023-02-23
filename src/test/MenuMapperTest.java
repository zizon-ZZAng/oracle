package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;

@Mapper
class MenuMapperTest {

	MenuMapper mapper = MyBatisContext.getSqlSession().getMapper(MenuMapper.class);

	// 메뉴 추가
	@Test
	void insertMenu() {
		Menu menu = new Menu();

		menu.setName("양고기 커리");
		menu.setPrice(38000L);
		menu.setContent("카레");
		menu.setPhone("051-000-0012");

		int ret = mapper.insertMenu(menu);

	}

	// 메뉴 출력
	@Test
	void selectMenuList() {
		List<Menu> list = mapper.selectMenuList();
		for (Menu m : list) {
			System.out.println(m.toString());
		}
	}

	// 메뉴 수정
	@Test
	void updateMenu() {
		Menu menu = new Menu();

		menu.setNo(1010L);
		menu.setPhone("051-000-0000");

		menu.setName("멘보샤");
		menu.setPrice(9000L);
		menu.setContent("아마 맛있어");

		int ret = mapper.updateMenu(menu);
		System.out.println(ret);
	}

	@Test
	void deleteMenu() {
		Menu menu = new Menu();

		menu.setNo(1012L);
		menu.setPhone("051-000-0000");

		int ret = mapper.deleteMenu(menu);
		System.out.println(ret);
	}

	// 해당 식당 메뉴 전체 조회
	@Test
	void selectRestaurantMenu() {

		List<Map<String, Object>> list = mapper.selectRestaurantMenu("051-000-0000");
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 할인율이 추가된 전체 메뉴 조회(1)
	@Test
	void selectDiscountPrice() {
		List<Map<String, Object>> list = mapper.selectDiscountPrice(0.7f,"051-000-0000");
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	//N% 할인율을 전달하면 discountPrice컬럼이 추가된 전체 메뉴 조회 (2)
	// 컬럼명(대문자), CONTENT 이상하게 뜸
	@Test
	void selectDiscountPrice2() {
		List<Map<String, Object>> list = mapper.selectDiscountPrice2(0.7f, "051-000-0000");
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}

	}
}
