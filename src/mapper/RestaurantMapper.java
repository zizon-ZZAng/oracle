package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Restaurant;

@Mapper
public interface RestaurantMapper {

	// String[] str = {"a", "b", "c"}
	@Insert({
		"INSERT INTO restaurant(phone, name, address, password)",
		"VALUES(#{phone},#{name},#{address},#{password})"
	}) // r에서 받은걸 #{phone},#{name},#{address},#{password} 이기로 넣어라
	
	public int insertRestaurant(Restaurant r);

	// 쿼리문은 다 여기 넣기
	@Select({
		"SELECT r.* FROM restaurant r"
	})
	public List<Restaurant> selectRestaurantList();
	
	
}
