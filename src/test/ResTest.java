package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;

class ResTest {
	RestaurantMapper mapper = MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);	

	@Test
	void resInsertBatch() {
		List<Restaurant> list = new ArrayList<>();
		
		for (Restaurant obj : list) {
			
			obj.setRetaurantphone("010-01");
			obj.setName("snrn");
			obj.setPassword("whgtq");
			obj.setAddress("동래구");
			obj.setRegdate(new Date()); 
			
			list.add(obj);
		}
		
		int ret = mapper.resInsertBatch(list);
		System.out.println();
			}

}
