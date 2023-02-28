package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;

class RestaurantMapperTest {

	// 테스트 mapper객체 생성
	RestaurantMapper rMapper 
	= MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
	
	
	// 식당추가 테스트
	@Test
	void insertRestaurant() {
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-1002"); // 기본키
		obj.setAddress("대구");
		obj.setPassword("aaa");
		obj.setName("양식집");
		
		int ret = rMapper.insertRestaurant(obj);
		System.out.println(ret);
	}

	// 식당목록 전체조회 테스트
	@Test
	void selectRestaurantList() {
		List<Restaurant> list = rMapper.selectRestaurantList();
		for(Restaurant obj : list) {
			System.out.println(obj.toString());
		}
		
	}
	
	// 식당정보 변경
	@Test
	void updateRestaurant() {
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-0005"); // 기본키
		obj.setAddress("울산");
		obj.setName("숯불갈비집");
		
		int ret = rMapper.updateRestaurant(obj);
		System.out.println(ret);
	}
	
	// 식당 비밀번호 변경
	@Test
	void updatePassword() {
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-0010");
		obj.setPassword("k");
		obj.setNewpassword("vvv");
		
		int ret = rMapper.updatePassword(obj);
		System.out.println(ret);
	}
	
	// 식당 로그인
	@Test
	void selectReataurantLogin() {
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-1112");
		obj.setPassword("e");
		
		System.out.println(rMapper.selectRestaurantLogin(obj));
	}
	
	// 식당하나 조회
	@Test
	void selectReataurantOne() {
		System.out.println(rMapper.selectRestaurantOne("051-000-0010"));
	}
	
	@Test
	void insertRestaurantBatch() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for(int i=0; i<5; i++) {
			Restaurant r = new Restaurant();
			r.setPhone("061-000-001"+i);
			r.setName("카페테리아"+i);
			r.setAddress("대전");
			r.setPassword("eee"+i);
			list.add(r);
			}
			int ret = rMapper.insertRestaurentBatch(list);
			System.out.println(ret);
		
	}
	
}
