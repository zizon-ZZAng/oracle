package main;

import java.util.List;

import connection.MyBatisContext;
import dto.Menu;
import mapper.MenuMapper;

public class Main {

	public static void main(String[] args) {
//		RestaurantMapper rMapper 
//		= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
		
		// mapper 객체 생성
		MenuMapper mMapper 
		= MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
		
		
		// 메뉴 삭제
		Menu menu = new Menu();
		menu.setNo(1020L); // 목록에 있는 걸 써야함
		menu.setPhone("051-000-0008"); // 목록에 있는 걸 써야함22
		int ret = mMapper.deleteMenu(menu);
		System.out.println(ret);
		
		// 메뉴 수정하기
//		Menu menu = new Menu();
//		menu.setNo(1019L); // 목록에 있는 거 쓰기
//		menu.setName("변경이름");
//		menu.setPrice(4500L);
//		menu.setContent("변경내용");
//		menu.setPhone("051-000-0008");
//		
//		int ret = mMapper.updateMenu(menu);
//		System.out.println(ret);
		
		
		// 메뉴 추가하기
//		for(int i=0; i<5; i++) {
//			Menu menu = new Menu();
//		menu.setName("돼지국밥");
//		menu.setPrice(8500L);
//		menu.setContent("해장 각");
//		menu.setPhone("051-000-0005");
//		
//		int ret = mMapper.insertMenu(menu);
//		System.out.println(ret);
//	}
		
		// 메뉴 조회
//		List<Menu> list = mMapper.selectMenuList();
//		for(Menu menu : list) {
//			System.out.println(menu);
			
		}
		
		
		//  식당 조회하기
//		List<Restaurant> list = rMapper.selectRestaurantList();
//		for(Restaurant obj : list) {
//			System.out.println(obj.toString());
		}
		
		// 식당 추가하기
//		Restaurant obj = new Restaurant();
//		obj.setPhone("051-000-0011"); // 기본키 제약조건
//		obj.setName("주문진 막국수");
//		obj.setPassword("w");
//		obj.setAddress("부산 사직동");
//		
//		int ret = rMapper.insertRestaurant(obj);
//		System.out.println(ret);
		

	

