package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Purchase;
import mapper.PurchaseMapper;

public class Main {

	public static void main(String[] args) {
		
		
		
//==================================================================================================
		
		//구매
		PurchaseMapper pMapper = MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		
		Purchase obj = new Purchase();	

		//등록
		obj.setCnt(1);
		obj.setCode(19);
		obj.setUserid("l");
		
		
		int ret = pMapper.insertPurchase(obj);
		System.out.println(ret);
		
		
//		//주문수량 변경
//		obj.setNo(10006);
//		obj.setCnt(15);
//		
//		int ret = pMapper.updatePurchaseCnt(obj);
//		System.out.println(ret);
		
		
		//아이디별 주문목록 
//		List<Purchase> list = pMapper.selectPurchaseList("d");
//		
//		for(Purchase pch : list) {
//			System.out.println(pch.toString());
//			
//		}
		
		//아이디별 주문목록(2)
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewList("a");
//		
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			System.out.println(map.get("CNT"));//무조건 대문자로 해야함 소문자로 하면 null나옴 content 타입이 clob으로 되어있어서 대문자로 해야 나옴 (하나라도 타입이 CLOB이 되어있음 대문자로 설정되는가봐)
//		
//		
//		}
		
		// 성별에 따른 구매수량 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByGender("M");
//		
//		for(Map<String, Object> map : list) {
//			System.out.print(map.get("USERGENDER")+" ");
//			System.out.println(map.get("SUM(CNT)"));
//			
//		}
		
		// 고객별 구매수량, 총구매금액 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByUserid("l");
//		for(Map<String, Object> map : list) {
//			System.out.print("고객ID: "+map.get("USERID")+" ");
//			System.out.print("구매수량: "+map.get("SUM(CNT)")+" ");
//			System.out.print("총구매금액: "+map.get("SUM(TOTAL)"));
//		}
		
		
		//물품별 구매수량, 구매횟수, 총구매금액
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByCode(1L);
//		for(Map<String, Object> map : list) {
//			System.out.print("물품코드: "+map.get("CODE")+" ");
//			System.out.print("구매수량: "+map.get("SUM(CNT)")+" ");
//			System.out.print("구매횟수: "+map.get("COUNT(*)")+" ");
//			System.out.print("총구매금액: "+map.get("SUM(TOTAL)"));
//		}
		
		
		
		//월별 구매수량
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByMonth();
//		for(Map<String, Object> map : list) {
//			System.out.print(map.get("MONTH"));
//			System.out.print(map.get("SUM(CNT)"));
//		}
		
		
		//상품별 주문수량 개수가 2개 이상인 것 주문수량합계
		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByCode2();
		for(Map<String, Object> map : list) {
			System.out.print("상품코드: "+map.get("CODE")+" ");
			System.out.print("상품이름: " + map.get("NAME")+" ");
			System.out.println("주문수량합계: "+map.get("SUM(CNT)"));
		}
		
		
//==================================================================================================
		
	 	//메뉴
//		MenuMapper mMapper = MyBatisContext.getSqlSession().getMapper(MenuMapper.class);
	 	
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
//		menu.setNo(1014L);
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
