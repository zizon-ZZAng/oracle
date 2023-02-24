package test;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Delivery;
import mapper.DeliveryMapper;

class DeliveryTest {

	
	DeliveryMapper mapper = MyBatisContext.getSqlSession().getMapper(DeliveryMapper.class);
	
	@Test
	void insertDelivery() {
		Delivery delivery = new Delivery();
		
		delivery.setPhone("010-5555");
		delivery.setOrderno(100001L);
		
		int ret = mapper.insertDelivery(delivery);
		System.out.println(ret);
		
	}

}
