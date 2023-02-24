package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import mapper.ViewMapper;

class ViewMapperTest {
	ViewMapper vMapper 
		= MyBatisContext.getSqlSession().getMapper(ViewMapper.class);
	
	// 메뉴별 전체 주문 수량 및 전체금액 (어떤 메뉴가 잘 팔리는가?)
	@Test
	void selectGroupBYMenu() {
		List<Map<String, Object>> list = vMapper.selectGroupBYMenu();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 고객별 전체 주문 횟수 및 전체금액 (어떤 고객이 vip인가?)
	@Test
	void selectGroupBYEmail() {
		List<Map<String, Object>> list = vMapper.selectGroupBYEmail();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 주문 시간대별 주문횟수 및 전체금액 (어느 시간대에 주문을 많이 하는가?)
	@Test
	void selectGroupBYRegdate() {
		List<Map<String, Object>> list = vMapper.selectGroupBYRegdate();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 배달시간대별 배달 횟수 (어느 시간대에 배달을 많이 하는가?)
	@Test
	void selectGroupBYDvdate() {
		List<Map<String, Object>> list = vMapper.selectGroupBYDvdate();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 배달자별 배달건수 (어느 배달기사가 배달을 많이 했는가?)
	@Test
	void selectGroupBYRider() {
		List<Map<String, Object>> list = vMapper.selectGroupBYRider();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 식당 연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량 (우리 가게에는 어떤 메뉴가 잘 나가나?)
	@Test
	void selectGroupBYRestaurant() {
		List<Map<String, Object>> list = vMapper.selectGroupBYRestaurant("051-000-0004");
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
	
	// 요일별 주문횟수
	@Test
	void selectGroupBYDay() {
		List<Map<String, Object>> list = vMapper.selectGroupBYDay();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
}
