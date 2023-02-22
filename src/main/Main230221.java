package main;

import java.util.List;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;
import mapper.RestaurantMapper;

public class Main230221 {

	public static void main(String[] args) {
		// Restaurant
		RestaurantMapper rMapper 
			= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
		
		// 데이터 추가
//		Restaurant obj = new Restaurant();
//		obj.setPhone("051-000-0008"); // 기본키
//		obj.setName("샤브샤브");
//		obj.setPassword("f");
//		obj.setAddress("창원 의창구");
//		
//		int ret = rMapper.insertRestaurant(obj);
//		System.out.println(ret);
		
		// 전체 조회
//		List<Restaurant> list = rMapper.selectRestaurantList();
//		for (Restaurant obj : list) {
//			System.out.println(obj.toString());
//		}
		
		// ----------------------------------------------------------
		
		// Menu
		MenuMapper mMapper 
			= MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
		
		// 데이터 추가
//		Menu m = new Menu();
//		m.setName("돼지국밥");
//		m.setPrice(8500L);
//		m.setContent("맛있어요~");
//		m.setPhone("051-000-0005");
//		
//		int ret = mMapper.insertMenu(m);
//		System.out.println(ret);
		
		// 전체 조회
		List<Menu> list = mMapper.selectMenuList();
		for (Menu m : list) {
			System.out.println(m.toString());
		}
		
		// 수정
		Menu menu = new Menu();
//		menu.setNo(1005L);
//		menu.setName("광어초밥");
//		menu.setPrice(14000L);
//		menu.setContent("굿");
//		menu.setPhone("051-000-0003");
//		
//		int ret = mMapper.updateMenu(menu);
//		System.out.println(ret);
		
		// 삭제
//		menu.setNo(1003L);
//		menu.setPhone("051-000-0001");
//		
//		int ret = mMapper.deleteMenu(menu);
//		System.out.println(ret);
	}

}
