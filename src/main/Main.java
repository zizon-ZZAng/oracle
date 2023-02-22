package main;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import mapper.PurchaseMapper;

public class Main {

	public static void main(String[] args) {
		PurchaseMapper mapper 
			= MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		// Map으로 반환시키면 컬럼명이 키값으로 됨???
		List<Map<String,Object>> list = mapper.selectPurchaseViewGroupByMonth();
		
		for(Map<String, Object> map:list) {
			System.out.println(map.toString());
		}
		
//		Purchase p = new Purchase();
//		
//		List<Purchase> list = mapper.selectPurchaseList("e");
//		
//		for(Purchase obj:list) {
//			System.out.println(obj.toString());
//		}
		
		
//		p.setCnt(100);
//		p.setCode(15);
//		p.setRegdate(new Date());
//		p.setUserid("d");
//		p.setNo(10013);
//	
		
		
//		int ret = mapper.updatePurchaseCnt(p);
//		
//		System.out.println(ret);
		
//		for(Menu obj:list) {
//			System.out.println(obj.toString());
//		}
		
		
		
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
