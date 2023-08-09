package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;
import dto.Restaurant;

class RestaurantTest {
	RestaurantMapper rMapper 
	= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);

	@Test
	void restaurantInsertBatch() {
		List<Restaurant> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Restaurant rest = new Restaurant();
			rest.setPhone("051-222-777"+i);
			rest.setName("이름1" + i);
			rest.setAddress("창원 사파동 11"+i);
			rest.setPassword("jkjk7"+i);

			list.add(rest);
		}

		int ret = rMapper.restaurantInsertBatch(list);
		System.out.println(ret);
	}

}
