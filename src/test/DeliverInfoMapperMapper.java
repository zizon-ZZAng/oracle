package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import mapper.DeliveryInfoViewMapper;

class DeliverInfoMapperMapper {

	DeliveryInfoViewMapper mapper = MyBatisContext.getSqlSession().getMapper(DeliveryInfoViewMapper.class);

	// 메뉴별 전체 주문수량 및 전체금액(어떤 메뉴가 잘 팔리는가?)
	@Test
	void totalMenuTotalpri() {
		List<Map<String, Object>> list = mapper.totalMenuTotalpri();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	// 고객별 전체 주문횟수 및 전체금액(어떤 고객이 vip인가?)
	@Test
	void totalCustomerTotalpri() {
		List<Map<String, Object>> list = mapper.totalCustomerTotalpri();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	// 배달 시간대별 주문횟수 및 전체금액(어느 시간대에 주문을 많이 하는가?)
	@Test
	void totalcntDateHHTotalpri() {
		List<Map<String, Object>> list = mapper.totalcntDateHHTotalpri();
		for (Map<String, Object> map : list) {

			System.out.println(map);
		}
	}
	
	
	// 배달 시간대별 주문횟수 및 전체금액(어느 시간대에 주문을 많이 하는가?)
	@Test
	void riderOrderCnt() {
		List<Map<String, Object>> list = mapper.riderOrderCnt();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	//식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량?(우리가게에는 어떤 메뉴가 가장 잘나가나)
	@Test
	void RestaurantBestMenu() {
		List<Map<String, Object>> list = mapper.RestaurantBestMenu();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
	//요일별 주문횟수
	@Test
	void dayOfBestOrder() {
		List<Map<String, Object>> list = mapper.dayOfBestOrder();
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}
	
}
