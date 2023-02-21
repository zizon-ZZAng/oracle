package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Restaurant;

@Mapper
public interface RestaurantMapper {

	// INSERT INTO () VALUES 붙으면 안됨. => 그래서 "에서 한칸씩 띄워라
	// String[] str = {"a","b","c"}
	@Insert({
		" INSERT INTO restaurant (phone, name, address, password) ",
		" VALUES (#{phone}, #{name}, #{address}, #{password}) "
		})
	public int insertRestaurant( Restaurant obj );
	
	@Select({
		" SELECT r.* FROM restaurant r "
	})
	public List<Restaurant> selectRestaurantList();
	
}
