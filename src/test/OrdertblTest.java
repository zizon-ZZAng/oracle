package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Ordertbl;
import mapper.OrdertblMapper;

class OrdertblTest {

	OrdertblMapper oMapper 
	 = MyBatisContext.getSqlSession().getMapper(OrdertblMapper.class);
	
	@Test
	void insertOrdertbl() {
		Ordertbl obj = new Ordertbl();
		obj.setCnt(2);
		obj.setEmail("cscs2@naver.com");
		obj.setMenuno(1010);
		
		int ret = oMapper.insertOrdertbl(obj);
		System.out.println(ret);
	}
	
	@Test
	void updateOrdertbl() {
		Ordertbl obj = new Ordertbl();
		obj.setNo(100003);
		obj.setCnt(3);
		obj.setEmail("aaa@naver.com");
		obj.setMenuno(1001);
		
		int ret = oMapper.updateOrdertbl(obj);
		System.out.println(ret);
		
	}
	
	@Test
	void selectOrdertblList() {
		List<Ordertbl> list = oMapper.selectOrdertblList("abab1@naver.com");
		for( Ordertbl obj : list ) {
			System.out.println(obj.toString());
		}
		
	}

	
}
