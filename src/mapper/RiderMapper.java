package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import dto.Rider;

public interface RiderMapper {
	
	//배달자 등록 
	@Insert({
		" INSERT INTO rider ( phone, name, password) ",
	    " VALUES(#{phone},#{name},#{password}) "
	})
	public int insertRider(Rider r);
	
	//배달자 1명 조회
	@Select({
		
		" SELECT r.* FROM rider r WHERE phone=#{phone} "
	})
	public Rider selectRiderList(String phone);

	

	
	
}
