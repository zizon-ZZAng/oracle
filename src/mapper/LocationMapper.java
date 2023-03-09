package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.Location;

public interface LocationMapper {

	// 지역 데이터 입력
	@Insert({
		" INSERT INTO location2 (name) VALUES(#{name}) "
	})
	public int insertLocation (Location location);
	
	// 지역 조회
	@Select({
		" SELECT * FROM location2 "
	})
	public Object[] selectLocation();

}
