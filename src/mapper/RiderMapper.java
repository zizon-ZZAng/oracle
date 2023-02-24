package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Rider;

@Mapper
public interface RiderMapper {
	
	@Insert({
		" INSERT INTO rider( phone, name, regdate, password ) ",
		" VALUES ( #{phone}, #{name}, CURRENT_DATE, #{password}) " 
	})
	public int insertRider(Rider obj);
	
	@Select({
		" SELECT r.* FROM rider r WHERE phone=#{phone} "
	})
	public Rider selectOneRider(Rider obj);
	
}

