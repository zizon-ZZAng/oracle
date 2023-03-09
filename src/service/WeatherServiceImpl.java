package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Weather;

public class WeatherServiceImpl implements WeatherService {
	// 날씨 데이터 입력
	@Override
	public int insertWeather(Weather weather) {
		try {
			return mapper.insertWeather(weather);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}

	}

	// 날씨 전체 조회
	@Override
	public List<Weather> selectWeather() {
		try {
			return mapper.selectWeather();
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 날씨 온도 수정
	@Override
	public int updateWeather() {
		try {
			return mapper.updateWeather();
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

	@Override
	public int selectWeatherTemp(Weather weather) {
		try {
			return mapper.selectWeatherTemp(weather);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

}
