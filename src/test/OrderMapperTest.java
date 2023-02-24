package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Order;
import mapper.OrderMapper;

class OrderMapperTest {
	OrderMapper oMapper = 
		MyBatisContext.getSqlSession().getMapper(OrderMapper.class);

	//1.주문 하기
	@Test
	void insertOrder() {
		Order o = new Order();
		o.setMenuno(1018L);
		o.setEmail("bbb@bbb.com");
		o.setCnt(1500L);
		
		System.out.println(oMapper.insertOrder(o));
		
	}
	
	
	//주문내역조회(해당고객의)
	@Test
	void updateOrder() {
		Order o = new Order();
		o.setMenuno(1018L);
		o.setEmail("bbb@bbb.com");
		o.setCnt(37L);
		
		System.out.println(oMapper.updateOrder(o));
	}
	
	//주문내역조회
	@Test
	void selectOrderList() {
	 	List<Order> list = oMapper.selectOrderList("aaa@aaa.com");
	 	for(Order order : list) {
	 		System.out.println(order);
	 	}
	}
	
	//inner join한 뷰 보기
	@Test
	void selectView() {
		List<Map<String, Object>> list = oMapper.selectView();
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
	}

}
