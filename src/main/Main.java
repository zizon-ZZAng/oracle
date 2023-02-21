package main;

import java.util.List;

import connection.MyBatisContext;
import dto.Menu;
import dto.Restaurant;
import mapper.MenuMapper;

public class Main {

	public static void main(String[] args) {
		MenuMapper mapper 
			= MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
	
		List<Menu> list = mapper.selectMenuList();
		for(Menu obj:list) {
			System.out.println(obj.toString());
		}
		
//		
//		Menu menu = new Menu();
//		menu.setContent("오늘저녁은 너다");
//		menu.setName("변경내용");
//		menu.setNo(1013L);
//		menu.setPhone("051-110-1230");
//		menu.setPrice(5000L);
//		
//		int ret = mapper.deleteMenu(menu);
//		System.out.println(ret);
//		
		
//		
//		Restaurant obj = new Restaurant();
//		obj.setPhone("051-110-1236");		// 기본키 제약조건
//		obj.setName("골목끝에서울집");
//		obj.setPassword("연어덮밥맛집");
//		obj.setAddress("부경대");
//		
//		
//		int ret = rMapper.insertRestaurant(obj);
//		System.out.println(ret);
	}
	
}
