package test;

import java.util.List;
import java.util.Map;

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
	
	//메뉴별 전체 주문 수량 및 전체 금액
	@Test
	void deliveryMenuView() {
		List<Map<String, Object>> list = mapper.deliveryMenuView();
		for(Map<String, Object> map : list) {
//			System.out.print(map.get("MENUNO")+" ");
//			System.out.print(map.get("NAME")+" ");
//			System.out.print(map.get("TOTALCNT")+" ");
//			System.out.println(map.get("TOTALPRICE")+" ");
			System.out.println(map.toString());
		}
	}
	
	//고객별 전체주문 횟수 및 전체 금액 
	@Test
	void customerOrderView() {
		List<Map<String, Object>> list = mapper.cutomerOrderView();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	//주문 시간대별 주문횟수 및 전체 금액
	@Test
	void hourOrderViewView() {
		List<Map<String, Object>> list = mapper.hourOrderView();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	//배달 시간대별 배달횟수
	
	//식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량
	@Test
	void restaurantPhoneMenu() {
		List<Map<String, Object>> list = mapper.restaurantPhoneMenuView("051-000-0000");
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	//배달 시간대별 배달 횟수
	@Test
	void deliveryCount() {
		List<Map<String, Object>> list = mapper.deliveryCountView();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	//배달자별 배달건수
	@Test
	void deliveryCount2() {
		List<Map<String, Object>> list = mapper.deliveryCountView2();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	//요일별 주문횟수
	@Test
	void dayOrder() {
		List<Map<String, Object>> list = mapper.dayOrderView();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	
	
	

}
