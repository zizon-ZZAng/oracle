package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.RiderDelivery;
import mapper.RiderDeliveryMapper;

class RiderDeliveryTest {

	RiderDeliveryMapper rdMapper 
	= MyBatisContext.getSqlSession().getMapper(RiderDeliveryMapper.class);
	
	@Test
	void insertRider() {
		RiderDelivery rider = new RiderDelivery();
		rider.setPhone("010-0000-0010");
		rider.setName("이동휘");
		rider.setPassword("tttt");
		
		int ret = rdMapper.insertRider(rider);
		System.out.println(ret);
	}
	
	@Test
	void selectRider() {
		RiderDelivery rider = new RiderDelivery();
		rider.setPhone("010-0000-0002");
		System.out.println(rdMapper.selectRider(rider));
	}
	
	@Test
	void insertdelivery() {
		RiderDelivery obj = new RiderDelivery();
		obj.setNo(11);
		obj.setPhone("010-0000-0007");
		obj.setOrderno(100013);
		
		int ret = rdMapper.insertdelivery(obj);
		System.out.println(ret);
		
	}
	
	@Test
	void selectMenu() {
		List<Map<String, Object>> list = rdMapper.selectMenu();
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	void selectCustomer() {
		List<Map<String, Object>> list = rdMapper.selectCustomer();
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	void selectRegdate() {
		List<Map<String, Object>> list = rdMapper.selectRegdate();
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	void selectDeliverDate() {
		List<Map<String, Object>> list = rdMapper.selectDeliverDate();
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	void selectResphone() {
		List<Map<String, Object>> list = rdMapper.selectResphone("051-000-0008");
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	@Test
	void selectDayMenu() {
		List<Map<String, Object>> list = rdMapper.selectDayMenu();
		for( Map<String, Object> obj : list) {
			System.out.println(obj.toString());
		}
	}
	
}
