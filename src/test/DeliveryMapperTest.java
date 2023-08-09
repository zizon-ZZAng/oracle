package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Delivery;
import mapper.DeliveryMapper;

class DeliveryMapperTest {

	DeliveryMapper dMapper = MyBatisContext.getSqlSession().getMapper(DeliveryMapper.class);

	// 배달 등록(기본키,외래키2)
	@Test
	void insertDelivery() {
		Delivery d = new Delivery();
		d.setPhone("010-0000-0004");
		d.setOrderno(100004L);

		System.out.println(dMapper.insertDelivery(d));

	}

	// ordertblview+ 식당정보를 inner join 한 ordertblview1
	@Test
	void selectordertblview1() {
		List<Map<String, Object>> list = dMapper.selectView1();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 메뉴별 전체 주문 수량 및 전체금액 (어떤 메뉴가 잘팔리는가)
	@Test
	void selectMenuView() {
		List<Map<String, Object>> list = dMapper.selectMenuView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 고객별 전체 주문 횟수 및 전체금액 (어떤 고객이 vip인가)
	@Test
	void selectCustomerView() {
		List<Map<String, Object>> list = dMapper.selectCustomerView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 주문 시간대별 주문횟수 및 전체금액 (어느 시간대에 주문을 많이 하는가)
	@Test
	void selectOrderView() {
		List<Map<String, Object>> list = dMapper.selectOrderView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 배달시간대별 배달 횟수 (어느 시간대 배달을 많이 하는가)
	@Test
	void selectOrderHourView() {
		List<Map<String, Object>> list = dMapper.selectOrderHourView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 배달자별 배달건수 (어느 배달기사가 배달으 많이 했는가)
	@Test
	void selectDeliveryView() {
		List<Map<String, Object>> list = dMapper.selectDeliveryView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량은?(우리가게에 어떤메뉴가 잘나가나)
	@Test
	void selectRestaurantPhoneView() {
		List<Map<String, Object>> list = dMapper.selectRestaurantPhoneView("055-111-2222");
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

	// 요일별 주문횟수
	@Test
	void selectWeekdayView() {
		List<Map<String, Object>> list = dMapper.selectWeekdayView();
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}
}
