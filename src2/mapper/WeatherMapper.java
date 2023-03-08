package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Weather;

public interface WeatherMapper {
	
	// 날씨 데이터 입력
	@Insert({
		" INSERT INTO weather2 (code, wdate, temperature, rain, locname) ",
	    " VALUES(seq_weather2_code.NEXTVAL, #{wdate}, #{temperature}, #{rain}, #{locname}) "
	})
	public int insertWeather (Weather weather);
	
	// 날씨 전체 조회
	@Select({
		" SELECT w.* FROM weather2 w "
	})
	public List<Weather> selectWeather();
	
	// 지역 하나 날씨 조회?
	
	
	// 날짜 하루 날씨 조회?
	
	
	
	// 날씨 온도 수정
	@Update({
		" UPDATE weather2 SET temperature = #{temperature} WHERE code = #{code} "
	})
	public int updateWeather();

}
