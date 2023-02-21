package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Restaurant;

@Mapper
public interface RestaurantMapper {
	// 데이터 추가
	@Insert({" INSERT INTO restaurant(phone, name, address, password) ", 
			 " VALUES(#{phone}, #{name}, #{address}, #{password}) "})
	public int insertRestaurant(Restaurant obj);
	
	// 전체 조회
	@Select({" SELECT r.* FROM restaurant r "})
	public List<Restaurant> selectRestaurantList();
}
