package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Restaurant;

@Mapper
public interface RestaurantMapper {
	
	//String[] str = {"a", "b", "c"};	
	@Insert({" INSERT INTO restaurant(phone, name, address, password) ", // 처음과 끝을 꼭 띄우기
			 " VALUES(#{phone},#{name},#{address},#{password}) "})//값을 넣음? #{} 추가 추가 
	public int insertRestaurant(Restaurant obj); //직접 치지 않는건 외부에서 알아서 드러오게 해줌 이 코드가
	

	@Select({" SELECT r.* FROM restaurant r "})
	public List<Restaurant> selectRestaurantList();
	
}
