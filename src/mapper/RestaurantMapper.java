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
	// 데이터 추가
	@Insert({" INSERT INTO restaurant(phone, name, address, password) ", 
			 " VALUES(#{phone}, #{name}, #{address}, #{password}) "})
	public int insertRestaurant(Restaurant obj);
	
	// 전체 조회
	@Select({" SELECT r.* FROM restaurant r ORDER BY phone "})
	public List<Restaurant> selectRestaurantList();
	
	// 식당 이름, 주소 변경
	@Update({" UPDATE restaurant ",
			 " SET name = #{name}, address = #{address} ",
			 " WHERE phone = #{phone} "})
	public int updateRestaurant(Restaurant obj);
	
	// 식당 비밀번호 변경
	@Update({" UPDATE restaurant ",
			 " SET password = #{newPassword} ",
			 " WHERE phone = #{phone} AND password = #{password}" })
	public int updateRestaurantPassword(Restaurant obj);
	
	// 식당 1개 조회
	@Select({" SELECT r.* FROM restaurant r WHERE phone = #{phone} " })
	public Restaurant selectRestaurantOne(String phone);
	
	// 식당 로그인
	@Select({" SELECT r.* FROM restaurant r ",
			 " WHERE phone = #{phone} AND password = #{password} "})
//	public Restaurant loginRestaurant(Restaurant obj);
	public Restaurant loginRestaurant(@Param("phone") String phone, @Param("password") String password);	
}
