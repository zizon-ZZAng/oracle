package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Purchase;
import mapper.PurchaseMapper;

public class Main230222 {

	public static void main(String[] args) {
		// Purchase
		PurchaseMapper pMapper 
			= MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		Purchase p = new Purchase();
		// 주문하기
//		p.setCnt(40L);
//		p.setCode(7L);
//		p.setUserid("m");
//		System.out.println(pMapper.insertPurchase(p));
		
		// 주문수량 변경
//		p.setNo(10008L);
//		p.setCnt(20L);
//		System.out.println(pMapper.updatePurchaseCnt(p));
		
		// 해당 아이디 주문목록 조회
		// Map으로 반환시키면 컬럼명이 키 값으로 됨
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewList("e");
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//			System.out.println(map.get("NO"));
//		}
		
		// 성별에 따른 구매수량 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByGender();
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		// 고객별 구매수량, 총구매금액 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByUserid();
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		// 물품별 구매수량, 구매횟수, 총구매금액 조회
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByCode();
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		// 월별 구매수량
//		List<Map<String, Object>> list = pMapper.selectPurchaseViewGroupByMonth();
//		for (Map<String, Object> map : list) {
//			System.out.println(map.toString());
//		}
	}

}
