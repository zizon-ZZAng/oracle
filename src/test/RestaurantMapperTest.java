package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import dto1.Member;
import mapper.RestaurantMapper;

class RestaurantMapperTest {
	
	// 테스트 mapper객체 생성
	RestaurantMapper mapper 
		= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);

	// 식당추가 테스트
	@Test
	void insertRestaurant() {
		Restaurant obj = new Restaurant();
		
		obj.setAddress("대구");
		obj.setName("대구볼찜");
		obj.setPassword("aaa");
		obj.setPhone("010-0145-7854");
		
		int ret = mapper.insertRestaurant(obj);
		System.out.println(ret);
	}
	
	// 식당목록조회 테스트
	@Test
	void selectRestaurantList() {
	
		List<Restaurant> list = mapper.selectRestaurantList();
		
		for(Restaurant r : list) {
			System.out.println(r.toString());
		}
	}

	@Test
	void updateRestaurant() {
		Restaurant r = new Restaurant();
		r.setName("자바에서 이름변경");
		r.setAddress("자바에서 주소변경");
		r.setPhone("051-110-1235");
		
		int ret = mapper.updateRestaurant(r);
		System.out.println(ret);		
	}
	
	@Test
	void updateRestaurantPassword() {
		Restaurant r = new Restaurant();
		r.setPassword("연어덮밥맛집");
		r.setPhone("051-110-1235");
		r.setNewPassword("자바에서비밀번호변경");
		
		int ret = mapper.updateRestaurantPassword(r);
		System.out.println(ret);		
	}
	
	@Test
	void selectOneRestaurant() {	
		 Restaurant r = mapper.selectOneRestaurant("051-110-1235");
		System.out.println(r.toString());
	}
	
	@Test
	void selectRestaurant() {
		Restaurant r = new Restaurant();
		r.setPassword("b");
		r.setPhone("051-000-1234");
		
		Restaurant res = mapper.selectRestaurant(r);
		System.out.println(res.toString());
	}
	
	@Test
	void restaurantInsertBatch() {
		List<Restaurant> list = new ArrayList<>();
		for(int i=0;i<3;i++) {
			Restaurant restaurant = new Restaurant();
			restaurant.setAddress("자바주소" + i);
			restaurant.setName("자바이름" + i);
			restaurant.setPassword("password" + i);
			restaurant.setPhone("010-1111-000" + i);
			
			list.add(restaurant);
		}
		
		int ret = mapper.restaurantInsertBatch(list);
		System.out.println(ret);
		
	}
	
}
