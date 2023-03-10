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
	
	
	// 지역 and 날짜 하나 조회
	@Select({" SELECT * FROM weather2 WHERE wdate=#{wdate} AND locname=#{locname} "})
	public Weather selectWeatherOne(Weather weather);
	
	
	// 날씨 온도 수정
	@Update({
		" UPDATE weather2 SET temperature = #{temperature} WHERE code = #{code} "
	})
	public int updateWeather();
	
	// 해당 날씨, 시간, 지역에 맞는 온도 조회
	@Select({" SELECT temperature FROM weather2 WHERE wdate like #{wdate} || '%' AND locname=#{locname} "})
	public int selectWeatherTemp(Weather weather);
	
	// 날짜에서 시간만 조회(임의로 서울의 230307 선택) => MainFrame2에서 시간 콤보박스용
	@Select({" select concat(substr(wdate, 12, 2), '시') from weather2 ",
			 " where locname = '서울' and wdate like '2023-03-07' || '%' ",
			 " order by wdate "})
	public Object[] selectDayTime();
}
