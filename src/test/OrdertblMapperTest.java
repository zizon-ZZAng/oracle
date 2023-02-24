package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Ordertbl;
import mapper.OrdertblMapper;

class OrdertblMapperTest {
	OrdertblMapper oMapper 
		= MyBatisContext.getSqlSession().getMapper(OrdertblMapper.class);
	
	// 주문하기(기본키 : 주문번호 no, 외래키 : 고객이메일 email, 외래키 : 메뉴번호 menuno)
	@Test
	void insertOrdertbl() {
		Ordertbl order = new Ordertbl();
		order.setCnt(6);
		order.setEmail("aaa@aaa.com");
		order.setMenuno(1006);
		
		System.out.println(oMapper.insertOrdertbl(order));;
	}
	
	// 주문수량 변경(해당고객의)
	@Test
	void updateOrdertblCnt() {
		System.out.println(oMapper.updateOrdertblCnt(100002, "aaa@aaa.com", 10));
	}
	
	// 주문내역 조회(해당고객의)
	@Test
	void selectOrdertblList() {
		List<Ordertbl> list = oMapper.selectOrdertblList("aaa@aaa.com");
		for (Ordertbl order : list) {
			System.out.println(order.toString());
		}
	}
}
