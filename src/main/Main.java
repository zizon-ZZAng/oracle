package main;

import java.util.List;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;
import mapper.RestaurantMapper;

public class Main {

	public static void main(String[] args) {
		RestaurantMapper rMapper = MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
		
//		List<Restaurant> list = rMapper.selectRestaurantList();
//		for(Restaurant obj : list) {
//			System.out.println(obj.toString());
//		}
		
//		Restaurant obj = new Restaurant();
//		obj.setPhone("051-111-6666"); //기본키 제약조건
//		obj.setName("여우정");
//		obj.setPassword("kk");
//		obj.setAddress("부산 해운대구");
//		
//		int ret = rMapper.insertRestaurant(obj);
//		System.out.println(ret);
//		//1이 나오면 성공
		
		MenuMapper mMapper = MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
		
//		Menu obj = new Menu();
//		obj.setName("돼지국밥");
//		obj.setPrice(8500L);
//		obj.setContent("돼지국+ 밥");
//		obj.setPhone("055-111-2222");
//	
//		int menu1 = mMapper.insertMenu(obj);
//		System.out.println(menu1);
		
		
//		List<Menu> list = mMapper.selectMenuList();
//		for(Menu obj : list) {
//			System.out.println(obj.toString());
//		}
		
		
//		Menu menu = new Menu();
//		menu.setNo(1008L);
//		menu.setName("짬뽕");
//		menu.setContent("홍합짬뽕");
//		menu.setPrice(8000L);
//		menu.setPhone("055-111-4444");
//		
//		int ret = mMapper.updateMenu(menu);
//		System.out.println(ret);
		
		List<Menu> list =  mMapper.selectMenuList();
		for(Menu menu : list) {
			System.out.println(menu.toString());
		}
		}
		
	}


