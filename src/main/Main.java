package main;

import java.util.List;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;

public class Main {

	public static void main(String[] args) {
		
		
		
		
//==================================================================================================
		
	 	//메뉴
		MenuMapper mMapper = MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
	 	
		//메뉴 등록
//	 	for(int i=0; i<5; i++) {
//	 		Menu menu = new Menu();
//	 		
//	 		menu.setName("이름");
//	 		menu.setPrice((long)3600+i);
//	 		menu.setContent("내용");
//	 		menu.setPhone("051-000-0000");
//	 	
//	 		int ret = mMapper.insertMenu(menu);
//	 		System.out.println(ret);
//	 		
//	 	}
	 	
//	 	List<Menu> list = mMapper.selectMenuList();
	 	
	 	
//	 	//메뉴 수정
//	 	Menu menu = new Menu();
//	 	menu.setNo(1012L);
//	 	menu.setName("만두");
// 		menu.setPrice(9500L);
// 		menu.setContent("맛있어!");
// 		menu.setPhone("051-000-0000");
//	 
// 		int ret = mMapper.updateMenu(menu);
// 		System.out.println(ret);
		
		
		
//		//메뉴 삭제
//		Menu menu = new Menu();
//		menu.setNo(1016L);
//		menu.setPhone("051-000-0000");
//		
//		int ret = mMapper.delteMenu(menu);
//		System.out.println(ret);
		
		
	 	
//==================================================================================================	 	
	 	
//	 	//식당테이블
//	 	RestaurantMapper rMapper = MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
	 	
//	 	List<Restaurant> list = rMapper.selectRestaurantList();
	 	
//	 	for(Restaurant obj : list) {
//	 		System.out.println(obj.toString());
//	 	}
//	
//	 	//오라클 sql에 등록하기
//	 	Restaurant obj = new Restaurant();
//	 	obj.setPhone("051-000-0010");	//기본키 제약조건 중복X
//	 	obj.setName("김치찌개");
//	 	obj.setPassword("ddab");
//	 	obj.setAddress("부산 북구");
//	 	
//	 	int ret = rMapper.insertRestaurant(obj);
//	 	System.out.println(ret);
	 	

	}

}
