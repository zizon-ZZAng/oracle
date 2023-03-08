package service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import connection.MyBatisContext;
import dto.Weather;
import mapper.WeatherMapper;

public interface WeatherService {
	final WeatherMapper mapper = MyBatisContext.getSqlSession().getMapper(WeatherMapper.class);

	// 날씨 데이터 입력
	public int insertWeather(Weather weather);

	// 날씨 전체 조회
	public List<Weather> selectWeather();

	// 날씨 온도 수정
	public int updateWeather();

	// 해당 날씨, 시간, 지역에 맞는 온도 조회
	public int selectWeatherTemp(Weather weather);
}
