package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;

class RestaurantMapperTest {
	// 테스트 mapper 객체 생성
	RestaurantMapper rMapper 
		= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
	
	// 식당 추가 테스트
	@Test
	void insertRestaurant() {
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-0011");
		obj.setAddress("창원시 성산구");
		obj.setPassword("e");
		obj.setName("양식");
		
		System.out.println(rMapper.insertRestaurant(obj));
	}
	
	// 식당 목록 조회 테스트
	@Test
	void selectRestaurantList() {
		List<Restaurant> list = rMapper.selectRestaurantList();
		for (Restaurant r : list) {
			System.out.println(r.toString());
		}
	}
	
	// 식당 이름, 주소 변경
	@Test
	void updateRestaurant() {
		Restaurant obj1 = new Restaurant();
		obj1.setName("식당이름 변경");
		obj1.setAddress("주소 변경");
		obj1.setPhone("051-000-0010");
		
		System.out.println(rMapper.updateRestaurant(obj1));
	}
	
	// 식당 비밀번호 변경
	@Test
	void updateRestaurantPassword() {
		Restaurant obj2 = new Restaurant();
		obj2.setNewPassword("new");
		obj2.setPhone("051-000-0011");
		obj2.setPassword("eee");
		
		System.out.println(rMapper.updateRestaurantPassword(obj2));
	}
	
	// 식당 1개 조회
	@Test
	void selectRestaurantOne() {
		Restaurant obj3 = rMapper.selectRestaurantOne("051-000-0008");
		System.out.println(obj3.toString());
	}
	
	// 식당 로그인
	@Test
	void loginRestaurant() {
//		Restaurant obj4 = new Restaurant();
//		obj4.setPhone("051-000-0009");
//		obj4.setPassword("c");
//		System.out.println(rMapper.loginRestaurant(obj4).toString());
		
		System.out.println(rMapper.loginRestaurant("051-000-0009", "c").toString());
	}
	
	// 230228
	@Test
	void restaurantInsertBatch() {
		List<Restaurant> list = new ArrayList<>();
		for(int i=1; i<6; i++) {
			Restaurant rest = new Restaurant();
			rest.setPhone("055-000-00"+i+i);
			rest.setName("식당"+i);
			rest.setAddress("주소"+i);
			rest.setPassword("pw"+i);
			list.add(rest);
		}
		System.out.println(rMapper.restaurantInsertBatch(list));
	}
}
