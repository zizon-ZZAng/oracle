package main;

import connection.MyBatisContext;
import dto.Purchase;
import mapper.PurchaseMapper;

public class PurchaseMain {

	public static void main(String[] args) {
		
		//구매
		PurchaseMapper pMapper = MyBatisContext.getSqlSession().getMapper(PurchaseMapper.class);
		
		//등록
		Purchase obj = new Purchase();
				
		int ret = pMapper.insertPurchase(obj);
		System.out.println(ret);		
				
				

	}

}
