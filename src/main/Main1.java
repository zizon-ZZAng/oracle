package main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Purchase;
import mapper.PurchaseMapper;

public class Main1 {

	public static void main(String[] args) {
		PurchaseMapper pMapper 
			= MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewList("a");
		
		
    	List<Map<String, Object>> list = pMapper.selectPurchaseGroupByMonth();
    	for (Map<String, Object> map : list) {
    		System.out.println(map.toString());
    	}
		
		
//		for ( Map<String, Object> map: list) {
//			System.out.println(map.toString());
//			System.out.println(map.get("NO"));
//		}

//		for ( Map<String, Object> map: list2) {
//			System.out.println(map.toString());
//			System.out.println(map.get("NO"));
//		}
		
//		//insertPurchase
//		Purchase obj = new Purchase();
//		obj.setUserid("a");
//		obj.setCnt(20L);
//		obj.setCode(15L);
//		
//		int ret = pMapper.insertPurchase(obj);
//		System.out.println(ret);
		
//		//updatePurchaseCnt
//		Purchase obj1 = new Purchase();
//		obj1.setNo(11L);
//		obj1.setCnt(30L);
//
//		int ret1 = pMapper.updatePurchaseCnt(obj1);
//		System.out.println(ret1);

//		//selectPurchaseList
//		String userid = "a";
//		List<Purchase> list3 = pMapper.selectPurchaseList(userid);
//		for(Purchase p : list3 ) {
//		
//			System.out.println(p.toString());
//		}
	
		
	}
}
