package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Restaurant;

@Mapper
public interface RestaurantMapper {

	
	// 쿼리문은 다 여기 넣기
	@Select({
		"SELECT r.* FROM restaurant r"
	})
	public List<Restaurant> selectRestaurantList();
	
	
	@Select({
		" SELECT r.* FROM restaurant r ",
		" WHERE phone=#{phone} "
	})
	public Restaurant selectOneRestaurant(String p); 
	
	
	@Select({
		" SELECT r.* FROM restaurant r ",
		" WHERE phone=#{phone} AND password =#{password} "
	})
	public Restaurant selectRestaurant(Restaurant r);
	
	
	
	/* 
	// 매개변수를 두개 입력받는 경우
	@Select({
		" SELECT r.* FROM restaurant r ",
		" WHERE phone=#{phone} AND password =#{password} "
	})
	public Restaurant selectRestaurant(@Param("phone") String p, 
			@Param("password") String pa 이 변수는 아무거나해도 상관 없음.);
	*/
	
	
	
	// String[] str = {"a", "b", "c"}
	@Insert({
		"INSERT INTO restaurant(phone, name, address, password)",
		"VALUES(#{phone},#{name},#{address},#{password})"
	}) // r에서 받은걸 #{phone},#{name},#{address},#{password} 이기로 넣어라
	public int insertRestaurant(Restaurant r);
	
		
	@Update({
		" UPDATE restaurant r SET r.name=#{name}, r.address=#{address} ", 
		" WHERE r.phone=#{phone} "
	})
	public int updateRestaurant(Restaurant r);	
	
	
	
	/* 
	// 받는 파라미터에 이름을 부과해서 사용함
	@Update({
		" UPDATE restaurant r SET r.name=#{obj.name}, r.address=#{obj.address} ", 
		" WHERE r.phone=#{obj.phone} "
	})
	public int updateRestaurant(@Param("obj") Restaurant obj);	
	*/
	
	
	
	// 비밃번호 변경
	@Update({
		" UPDATE restaurant SET password =#{newPassword} ",
		" WHERE password=#{password} AND phone=#{phone} "
	})
	public int updateRestaurantPassword(Restaurant r);
	
	
	@Insert({
		" <script> ",
		" INSERT ALL ",
			// foreach : 반복문
			// separator : 하나의 인설트into 구문이 끝난 후 어떤 문자가 있는지 만약 , 가 있으면 ,를 넣을 것
			" <foreach collection='list' item='obj' separator=' '> ",
	    	" INTO restaurant(phone, name, address, password, regdate) ",
	    		" VALUES (#{obj.phone},#{obj.name},#{obj.address},#{obj.password},CURRENT_DATE) ",
	    	" </foreach> ",
	    " SELECT * FROM DUAL ",
	    " </script> "
	})
	public int restaurantInsertBatch(@Param("list") List<Restaurant> list);
}
