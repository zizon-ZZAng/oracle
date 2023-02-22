package main;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import mapper.PurchaseMapper;

public class Pmain {

	public static void main(String[] args) {
		PurchaseMapper pMapper = MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
//		List<Map<String, Object>> list = pMapper.selectPurchaseviewList("k");
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			System.out.println(map.get("NO"));
//		}
		
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByGender("F");
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			
//		}
		
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByUserid();
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			
//		}
		
		
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByCode();
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			
//		}
		
		
		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByMonth();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
			
			
		}
		
		
		
		
// 내방식
//		PurchaseMapper pMapper = MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);

//		Purchase obj = new Purchase();
//		
//		obj.setCode(9L);
//		obj.setUserid("k");
//		obj.setCnt(999L);
//	
//		int ret= pMapper.insertPurchase(obj);
//		System.out.println(ret);

//		obj.setCnt(11111L);
//		obj.setNo(10013L);
//
//		int ret = pMapper.updatePurchaseCnt(obj);
//		System.out.println(ret);

//		List<Purchase> list = pMapper.selectPurchaseList("k");
//		for(Purchase obj1: list) {
//			System.out.println(obj1.toString());
//		}

		
		
		
	}
}
