package test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Order;
import mapper.OrderMapper;

class OrdertblTest {

	OrderMapper mapper = MyBatisContext.getSqlSession().getMapper(OrderMapper.class);

	// 주문하기
	@Test
	void insertOrder() {
		Order order = new Order();

		order.setCnt(11);
		order.setEmail("a456@naver.com");
		order.setMenuno(1017);

		int ret = mapper.insertOrder(order);
		System.out.println(ret);

	}

	// 주문수량 변경
	@Test
	void updateOrder() {
		Order order = new Order();

		order.setNo(100004);
		order.setEmail("a456@naver.com");

		order.setCnt(8);

		int ret = mapper.updateOrder(order);
		System.out.println(ret);

	}

	// 주문내역 조회
	@Test
	void selectOrder() {
		List<Map<String, Object>> list = mapper.selectOrder("a456@naver.com");

		for (Map<String, Object> map : list) {
			System.out.println(map.toString());

		}

	}
	
	//view 주문내역 조회
	@Test
	void selectViewOrder() {
		List<Map<String, Object>> list = mapper.selectOrder("a456@naver.com");
		
		for (Map<String, Object> map : list) {
			System.out.println(map.toString());

		}
		
	}
	
	
	

}
