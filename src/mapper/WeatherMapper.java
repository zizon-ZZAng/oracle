package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

	@Update({ " UPDATE WEATHER1 SET regdate = TO_DATE(#{regdate}, 'YYYY-MM-DD-HH24') WHERE no = #{no} " })
	public int weatherUpdateHour(Weather w);

	@Update({ " UPDATE WEATHER1 SET temperature = #{temperature} WHERE no = #{no} " })
	public int weatherUpdateWea(Weather w);

	//
	@Select({ " SELECT * FROM WEATHER1 WHERE code = #{code} " })
	public Weather weatherSelect(Weather w);

	@Delete({ " DELETE FROM WEATHER1 WHERE code = #{code} " })
	public int weatherDelete(Weather w);

}
