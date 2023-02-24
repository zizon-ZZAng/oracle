package test;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Delivery;
import mapper.DeliveryMapper;

class DeliveryMapperTest {
	DeliveryMapper dMapper 
		= MyBatisContext.getSqlSession().getMapper(DeliveryMapper.class);
	
	// 배달 등록(기본키, 외래키 2개)
	@Test
	void insertDelivery() {
		Delivery d = new Delivery();
		d.setPhone("010-0000-0005");
		d.setOrderno(100003);
		
		System.out.println(dMapper.insertDelivery(d));
	}

}
