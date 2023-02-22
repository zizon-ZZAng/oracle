package main;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import mapper.PurchaseMapper;

public class MainPurchase {

	public static void main(String[] args) {
		
		// mapper 객체 생성
		PurchaseMapper pMapper 
		= MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		// 주문하기
//		Purchase obj = new Purchase();
//		obj.setCnt(5);
//		obj.setCode(23);
//		obj.setUserid("s");
//		obj.setRegdate(new Date());
//		
//		int ret = pMapper.insertPurchase(obj);
//		System.out.println(ret);
		
		
		// 수정하기
//		Purchase obj = new Purchase();
//		obj.setNo(10008);
//		obj.setCnt(155);
//		obj.setCode(11);
//		obj.setUserid("f");
//		
//		int ret = pMapper.updatePurchase(obj);
//		System.out.println(ret);
		
		
		// 조회하기
//		List<Purchase> list = pMapper.selectPurchaseList("f");
//		for( Purchase purchase : list ) {
//			System.out.println(purchase.toString());
//		}
		
		// 삭제하기
//		Purchase obj = new Purchase();
//		obj.setNo(10012);
//		obj.setCode(19);
//		obj.setUserid("a");
//		int ret = pMapper.deletePurchase(obj);
//		System.out.println(ret);
		
		
		// 조회하기
		// Map으로 반환시키면 컬럼명이 키값으로 됨.
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewList("a");
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			System.out.println(map.get("NO"));
//		}
		
		// 성별에 따른 구매수량 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByGender();
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		// 고객별 구매수량, 총구매금액 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByUserid();
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		// 물품별 구매수량, 구매횟수, 총구매금액
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByCode();
//		for(Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByMonth();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

}
