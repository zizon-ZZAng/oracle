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
	
	@Update({
		" UPDATE restaurant SET name = #{name}, address = #{address} ",
			   " WHERE phone = #{phone} "
	})
	public int updateRestaurant ( Restaurant obj);
	
//	@Update({
//		" UPDATE restaurant SET name = #{obj.name}, address = #{obj.address} ",
//			   " WHERE phone = #{obj.phone} "
//	})
//	public int updateRestaurant (@Param("obj") Restaurant obj); // 명확하게 할려고 <객체.> 을 쓴다
	
	@Update({
		" UPDATE restaurant SET password = #{newpassword} ",
			   " WHERE phone = #{phone} AND password = #{password} "
	})
	public int updatePassword (Restaurant obj);
	
	@Select({
		" SELECT r.* FROM restaurant r WHERE phone = #{phone} AND password = #{password} "
	})
	public Restaurant selectRestaurantLogin (Restaurant obj);
	
//	@Select({
//		" SELECT r.* FROM restaurant r WHERE phone = #{phone} AND password = #{password} "
//	})
//	public Restaurant selectRestaurantLogin (@Param(String phone) @Param(String password));
	
	
	@Select({
		" SELECT r.* FROM restaurant r WHERE phone = #{phone} "
	})
	public Restaurant selectRestaurantOne (String phone);
	
	@Insert({
		" <script> ",
			" INSERT ALL ",
			" <foreach collection='list' item= 'obj' seperator=' '> ",
				" INTO restaurant (phone, name, address, password, regdate) ",
				" VALUES(#{obj.phone}, #{obj.name}, #{obj.address}, #{obj.password}, CURRENT_DATE) ",
	        " </foreach> ",
	        " SELECT * FROM DUAL ",
	    " </script> "
	})
	public int insertRestaurentBatch(@Param("list") List<Restaurant> list); 
}
