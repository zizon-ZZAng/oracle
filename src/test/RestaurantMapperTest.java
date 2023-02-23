package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;

class RestaurantMapperTest {

	RestaurantMapper mapper = MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);

	// 식당추가테스트
	@Test
	void insertRestaurant() {
		Restaurant obj = new Restaurant();
		obj.setRetaurantphone("051-4895-6124");
		obj.setAddress("기장군");
		obj.setPassword("666666");
		obj.setName("기장손칼국수옆에카페");
		
		int ret = mapper.insertRestaurant(obj);
		System.out.println(ret);
	}

	// 식당목록조회 테스트
	@Test
	void selectRestaurant() {

		List<Restaurant> list = mapper.selectRestaurantList();
		for (Restaurant r : list) {
			System.out.println(r);
		}
	}

	// 식당정보변경
	@Test
	void updateRestaurant() {

		Restaurant obj = new Restaurant();
		obj.setRetaurantphone("051-002-3926");
		obj.setAddress("수영구");
		obj.setName("수영밀면");
		
		int ret = mapper.updateRestaurant(obj);
		System.out.println(ret);
	}
	
	// 식당비밀번호변경
		@Test
		void updateRestaurantPassword() {

			Restaurant obj = new Restaurant();
			obj.setNewPassword("i4");
			
			obj.setPassword("fg");
			obj.setRetaurantphone("051-000-3226");
			
			int ret = mapper.updateRestaurantPassword(obj);

			System.out.println(ret);
		}

	// 식당1개조회
	@Test
	void selectRestaurantOne() {
		Restaurant r = mapper.selectRestaurantOne("051-4895-6123");
		System.out.println(r);
	}

	// 식당로그인
	@Test
	void selectRestaurantLogin() {

		Restaurant ret = mapper.selectRestaurantLogin("051-000-3226", "i4");
		System.out.println(ret.toString());

	}
	
	
}
