package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Ordertbl;
import mapper.OrdertblMapper;

@Mapper
class OrdertblTest {

	// 테스트 mapper객체 생성
	OrdertblMapper mapper = MyBatisContext.getSqlSession().getMapper(OrdertblMapper.class);

	@Test
	void insertOrdertbl() {

		Ordertbl obj = new Ordertbl();

		obj.setCnt(3L);
		obj.setEmail("DJSKFJ9@naver.com");
		obj.setMenuno(1022L);

		System.out.println(mapper.insertOrdertbl(obj));
	}

	@Test
	void selectOrdertbl() {

		Ordertbl obj = new Ordertbl();

		obj.setNo(100001L);
		obj.setEmail("DJSKFJ@naver.com");
		obj.setMenuno(1033L);

		Ordertbl obj2 = mapper.selectOrdertbl(obj);

		System.out.println(obj2.toString());
	}
	
	@Test
	void updateOrdertbl() {
		
		Ordertbl obj = new Ordertbl();

		obj.setCnt(10);
		obj.setNo(100001L);
		obj.setEmail("DJSKFJ@naver.com");
		obj.setMenuno(1033L);

		System.out.println(mapper.updateOrdertbl(obj));
	}
	
	@Test
	void selectOrdertblView() {

		List<Map<String, Object>> list = mapper.selectOrdertblView();
		
		for(Map<String, Object> o : list) {
			System.out.println(o.toString());
		}		
	}
}
