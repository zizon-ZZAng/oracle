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
			" INSERT INTO WEATHER0 (code, regdate, weather, temperature, name) VALUES(func_SEQ_WEATHER0_CODE_nextval, CURRENT_DATE, #{weather}, #{temperature}, #{no}) " })
	public int weatherInsert(Weather w);

	@Update({ " UPDATE WEATHER0 SET weather= #{weather}, temperature = #{temperature} WHERE name = #{name}  " })
	public int weatherUpdate(Weather w);

	// 일괄추가 할때 쓰는거

	// 시간 업데이트
	@Update({ " UPDATE WEATHER0 SET regdate = TO_DATE(#{regdate2}, 'YYYY-MM-DD-HH24') WHERE name = #{name} " })
	public int weatherUpdateHour(Weather w);

	// 기온 업데이트
	@Update({ " UPDATE WEATHER0 SET temperature = #{temperature} WHERE name = #{name} " })
	public int weatherUpdateTemp(Weather w);

	// 날씨 업데이트
	@Update({ " UPDATE WEATHER0 SET weather = #{weather} WHERE name = #{name} " })
	public int weatherUpdateWTH(Weather w);

	@Select({ " SELECT * FROM WEATHER0  " })
	public List<Weather> weatherSelect();

	// 시간가져오기
	// no는 넣거나 빼거나 필요에 따라
	@Select({ " SELECT distinct(hour) as hour FROM wea_clo_mem_view order by hour " })
	public Object[] weatherSelectHOUR();

	// 날짜가져오기
	// no는 넣거나 빼거나 필요에 따라
	@Select({ " SELECT week from wea_clo_mem_view where address=#{address} " })
	public List<Weather> weatherSelectDATE();

	// 날씨 가져오기
	@Select({ " SELECT weather from wea_clo_mem_view where address=#{address} " })
	public List<Weather> weatherSelectWeather();

	// 뷰 가져오기
	@Select({ " SELECT * FROM wea_clo_mem_view " })
	public List<Map<String, Object>> weatherSelectWV();

	// 뷰로(지역이름,날짜,시간으로) 기온 가져오기
	@Select({ " SELECT temperature, weather ", " from wea_clo_mem_view ",
			" where address=#{address} and week=#{week} and hour =#{hour} " })
	public List<Map<String, Object>> weatherSelectWVTemp(Map<String, Object> map);

	@Delete({ " DELETE FROM WEATHER0 WHERE name = #{name} " })
	public int weatherDelete(Weather w);

}