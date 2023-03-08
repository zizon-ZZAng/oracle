package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Weather;
import mapper.WeatherMapper;

public interface WeatherService {
	final WeatherMapper mapper 
		= MyBatisContext.getSqlSession().getMapper(WeatherMapper.class);
	
	// 날씨 데이터 입력
	public int insertWeather (Weather weather);
	
	// 날씨 전체 조회
	public List<Weather> selectWeather();
	
	// 날씨 온도 수정
	public int updateWeather();
}
