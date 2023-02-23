package main;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Purchase;
import mapper.PurchaseMapper;

public class NewMain {
	
	public static void main(String[] args) {
		
	
		// 월별구매수량
		PurchaseMapper pMapper = MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		List<Map<String, Object>> list = pMapper.selectPurchaseGroupByMonth();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

}
