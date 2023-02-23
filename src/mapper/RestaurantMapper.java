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

	// String[] str = {"a", "b", "c"};
	@Insert({ " INSERT INTO restaurant(phone, name, address, password) ", // 처음과 끝을 꼭 띄우기
			" VALUES(#{phone},#{name},#{address},#{password}) " }) // 값을 넣음? #{} 추가 추가
	public int insertRestaurant(Restaurant obj); // 직접 치지 않는건 외부에서 알아서 드러오게 해줌 이 코드가

	// 조회
	@Select({ " SELECT r.* FROM restaurant r " })
	public List<Restaurant> selectRestaurantList();

	// 수정
	@Update({ " UPDATE restaurant SET name=#{obj.name}, address=#{obj.address} ", " WHERE phone=#{obj.phone} " })
	public int updateRestaurant(@Param("obj") Restaurant obj);

	// 비밀번호 변경
	@Update({ " UPDATE restaurant SET password=#{newPassord} ", " WHERE phone=#{phone} and password=#{password} " })
	public int updateRestaurantPW(Restaurant obj);

	// 식당 1개 조회(1)
	@Select({ " SELECT * FROM restaurant ", " WHERE phone=#{phone} " })
	public Restaurant selectRestaurantOne(String phone);

	// 식당 1개 조회(2)
	@Select({ " SELECT r.phone, r.name, r.address  FROM restaurant r WHERE r.phone =#{phone} " })
	public List<Restaurant> selectRestaurantOne2(Restaurant phone);

	// 식당 로그인
	@Select({ " SELECT * FROM restaurant WHERE phone=#{phone} AND password=#{password} " })
	public Restaurant restaurantLogin(Restaurant obj);

	// 식당 로그인(2)
	@Select({ " SELECT * FROM restaurant  WHERE phone=#{phone} AND password=#{password} " })
	public Restaurant restaurantLogin2(@Param("phone") String phone, @Param("password") String password);

}
