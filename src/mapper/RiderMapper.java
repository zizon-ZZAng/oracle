package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.Rider;

@Mapper
public interface RiderMapper {
	
	
	
	//등록
	@Insert({" INSERT INTO rider(phone,name,password) VALUES(#{phone},#{name},#{password}) "})
	public int insertRider(Rider rider);
	
	
	//배달원 1명 조회
	@Select({"SELECT * FROM rider WHERE phone=#{phone}"})
	public Rider selectRider(Rider rider);

}
