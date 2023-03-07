package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Weather;

@Mapper

public interface WeatherMapper {

	@Insert({
			" INSERT INTO WEATHER1 (code, regdate, weather, temperature, no) VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE, #{weather}, #{temperature}, #{no}) " })
	public int weatherInsert(Weather w);

	@Update({
			" UPDATE WEATHER1 SET weather= #{weather}, temperature = #{temperature}, no = #{no} WHERE code = #{code} " })
	public int weatherUpdate(Weather w);

	// 시간 업데이트
	@Update({ " UPDATE WEATHER1 SET regdate = TO_DATE(#{regdate}, 'YYYY-MM-DD-HH24') WHERE code = #{code} " })
	public int weatherUpdateHour(Weather w);

	// 날씨 업데이트
	@Update({ " UPDATE WEATHER1 SET temperature = #{temperature} WHERE code = #{code} " })
	public int weatherUpdateTemp(Weather w);

	@Select({ " SELECT * FROM WEATHER1 WHERE code = #{code} " })
	public Weather weatherSelect(Weather w);

	@Select({ " SELECT * FROM WEATHER1 where regdate LIKE  '%'||#{regdate}||'%' AND no = #{no} " })
	public List<Weather> weatherSelectDate(Weather w);

	
	@Delete({ " DELETE FROM WEATHER1 WHERE code = #{code} " })
	public int weatherDelete(Weather w);

}