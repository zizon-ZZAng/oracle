package test;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Delivery;
import mapper.DeliveryMapper;

class DeliveryTest {

	// 테스트 mapper객체 생성
	DeliveryMapper mapper = MyBatisContext.getSqlSession().getMapper(DeliveryMapper.class);
	
	@Test
	void insertDelivery() {
		
		Delivery obj = new Delivery();

		for(int i=1; i<=9; i++) {
			obj.setOrderno((long)(100000+i));
			obj.setPhone("010-0001-000"+i);
			System.out.println(mapper.insertDelivery(obj));
		}
	}

}
