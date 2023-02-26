package test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.OrderTbl;
import mapper.OrderTblMapper;

class OrderTblMapperTest {
	
	OrderTblMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(OrderTblMapper.class);

	//주문하기
	@Test
	void insertOrderTbl() {
		
		OrderTbl obj = new OrderTbl();
		obj.setCnt(1);
		obj.setRegdate(new Date());
		obj.setEmail("uu9u@ghj.com");
		obj.setMenuNo(1020);
		
		int ret = mapper.insertOrderTbl(obj);
		System.out.println(ret);
	}
		
	//주문수량변경
		@Test
		void updateOrderTbl() {
		OrderTbl obj = new OrderTbl();
		obj.setCnt(9);
		obj.setEmail("uu9u@ghj.com");
		obj.setOrtherNo(10016);
		int ret = mapper.updateOrderTbl(obj);
				System.out.println(ret);
		}
		
	
	//주문내역조회
		@Test
		void selectOrderTbl() {
		OrderTbl obj = new OrderTbl();
		obj.setEmail("ktng");
		
		List<OrderTbl> list = mapper.selectOrderTbl(obj);
				System.out.println(list);
		}

		//뷰
		@Test
		void selectOrderTblView() {
			
		List<Map<String, Object>> list = mapper.selectOrderTblView();
			for(Map<String, Object> map : list) {
				System.out.println(map);
				
			}
		}
		
}


