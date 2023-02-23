package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Restaurant;

public interface RestaurantMapper {

	// 식당추가테스트
	@Insert
	({ 
		" INSERT INTO restaurant( retaurantphone, name, address, password ) " 
		, " VALUES( #{retaurantphone}, #{name}, #{address}, #{password}) " 
		})
	public int insertRestaurant(Restaurant obj);
	
	// 식당목록조회 테스트
	@Select
	({
		" SELECT r.* FROM restaurant r "
	})
	public List<Restaurant> selectRestaurantList();
	
	// 식당정보변경
	@Update
	({
		" UPDATE restaurant "
		, "	SET name = #{name}, address = #{address} "
		, "	WHERE retaurantphone = #{retaurantphone} "
	})
	public int updateRestaurant(Restaurant obj);
	
	// 식당비밀번호변경
	@Update
	({
		" UPDATE restaurant "
		, "SET password = #{obj.newPassword}"
		, "WHERE retaurantphone = #{obj.retaurantphone} AND password = #{obj.password} "
	})
	public int updateRestaurantPassword(@Param("obj") Restaurant o);
	
	// 식당1개조회
	@Select
	({
		" SELECT r.* FROM restaurant r "
		, " WHERE retaurantphone = #{retaurantphone} "
	})
	public Restaurant selectRestaurantOne(@Param("retaurantphone") String x);

	// 식당로그인
	@Select
	({
		" SELECT r.* FROM restaurant r "
		, " WHERE retaurantphone = #{retaurantphone} AND password = #{password} "
	})
	public Restaurant selectRestaurantLogin(
			@Param("retaurantphone") String r, 
			@Param("password") String p); 
			//selectRestaurantLogin(어쩌구 , 저쩌구) 로 보내는 법
			//@Param("명칭") 타입 변수(p나 w나 r ..)


	
	
	
	
	
	

}
