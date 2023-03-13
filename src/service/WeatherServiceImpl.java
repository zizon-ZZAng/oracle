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

	// 해당 날씨, 시간, 지역에 맞는 온도 조회
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

	// 날짜에서 시간만 조회 => MainFrame2에서 시간 콤보박스용
	@Override
	public Object[] selectDayTime() {
		try {
			return mapper.selectDayTime();
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}
	// 날짜에서 년월일만 조회 => MainFrame2에서 날짜 콤보박스용
	@Override
	public Object[] selectDayYMD() {
		try {
			return mapper.selectDayYMD();
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

}
