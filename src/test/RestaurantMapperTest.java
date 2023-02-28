package test;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Restaurant;
import mapper.RestaurantMapper;


@Mapper
class RestaurantMapperTest {

	RestaurantMapper mapper = MyBatisContext.getSqlSession().getMapper(RestaurantMapper.class);
	
	public String hashPW(String pw, String id) {
		try {
			// 1. Hash알고리즘 SHA-256, 단방향 aa가 3asdf3sd32f13d2f1as5d6f4 이렇게 될 수 있는데 거꾸로는 안됨
			// 암호를 잊어버림. 임시암호를 주잖아 이게 단방향임
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// ex)1234암호 + salt 사용자 ID 를 넣어서 복잡하게 함
			md.update((pw + id).getBytes());

			// byte to String으로 변경
			byte[] pwdSalt = md.digest();

			StringBuffer sb = new StringBuffer();
			for (byte b : pwdSalt) {
				sb.append(String.format("%02x", b));
			}

			String result = sb.toString();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	

	
	//식당 추가 테스트
	@Test
	void insertRestaurant() {
		
		 String phone = "051-000-0002";
		 String pw = "1a2";
		    
		 String hash = this.hashPW(pw, phone);
		
		
		Restaurant obj = new Restaurant();
		
		obj.setPhone(phone);
		obj.setName("치킨");
		obj.setAddress("부산 북구");
		obj.setPassword(hash);
		
		int ret = mapper.insertRestaurant(obj);
		System.out.println(ret);
	}
	
	//식당 목록 조회 테스트
	@Test
	void selectRestaurantList() {
		
		List<Restaurant> list = mapper.selectRestaurantList();
		for(Restaurant obj : list) {
			System.out.println(obj.toString());	
		}
		
	}
	
	//수정
	@Test
	void updateRestaurant() {
		
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-0010");	//기본키
		obj.setName("한식");
		obj.setAddress("부산 서구");
		
		
	 	int ret = mapper.updateRestaurant(obj);
	 	System.out.println(ret);
	
	}
	
	//비밀번호 변경
	@Test
	void updateRestaurantPW() {
		Restaurant obj = new Restaurant();
		obj.setPassword("4da5");
		obj.setPhone("051-111-0251");
		
		obj.setNewPassord("a45");
		
		
		int ret = mapper.updateRestaurantPW(obj);
		System.out.println(ret);
	}

	//식당 1개 조회
	@Test
	void selectRestaurantOne() {
		
		Restaurant obj = mapper.selectRestaurantOne("051-000-0010"); 
		
		System.out.println(obj.toString());
	}
	
	//식당 1개조회 list
	@Test
	void selectOneRestaurant() {
		
		Restaurant obj = new Restaurant();
		obj.setPhone("051-000-0010");
		List<Restaurant> list = mapper.selectRestaurantOne2(obj);
	
		for(Restaurant rs : list) {
			System.out.println(rs.toString());
		}
	}
	
	//식당 로그인
		@Test
		void restaurantLogin() {
			
			Restaurant obj = new Restaurant();
			obj.setPhone("010-000-0012");
			obj.setPassword("bbab");
			
			System.out.println(obj.getName());
		}
	
		
	//식당로그인 2
	@Test
	void restaurantLogin2() {
		
		System.out.println(mapper.restaurantLogin2("051-000-0012","bbaa").toString());
	}
	
	
	
	//시퀀스가 없는 데이터 추가
	@Test
	void restaurantInsertBatch() {
		List<Restaurant> list = new ArrayList<Restaurant>();
		for(int i=0; i<=3;i++) {
			Restaurant restaurant = new Restaurant();
			restaurant.setPhone("051-000-002"+i);
			restaurant.setName("카페"+i);
			restaurant.setAddress("부산 기장군");
			restaurant.setPassword("pwd"+i);
			
			
			list.add(restaurant);
		
		}
		int ret = mapper.restaurantInsertBatch(list);
		System.out.println(ret);
	}
	
	
	
	
	
}
