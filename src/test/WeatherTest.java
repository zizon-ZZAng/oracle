package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Weather;
import mapper.WeatherMapper;

class WeatherTest {

	WeatherMapper wMapper = MyBatisContext.getSqlSession().getMapper(WeatherMapper.class);
	
	// 날씨 데이터 입력
	@Test
	void insertWeather() {
		Weather w = new Weather();
		w.setWdate("2023-03-07 00:00:00"); 
		w.setTemperature(8);
		w.setRain(0);
		w.setLocname("부산");
		
		
		int ret = wMapper.insertWeather(w);
		System.out.println(ret);
	}
	
	// 날씨 전체 조회
	@Test
	void selectWeather() {
		List<Weather> list = wMapper.selectWeather();
		for(Weather weather : list) {
			System.out.println(weather.toString());
			
		}
	}
	
	// 날씨 온도 수정
	@Test
	void updateWeather() {
		Weather w = new Weather();
		w.setCode(1);
		w.setTemperature(10);
		
		int ret = wMapper.updateWeather();
		System.out.println(ret);
	}
	
	//날짜 and 지역 조회
	@Test
	void insertWeatherOne() {
		
		Weather w = new Weather();
		
		w.setWdate("2023-03-07 06:00:00");
		w.setLocname("부산");
		
		System.out.println(wMapper.selectWeatherOne(w));
	}
	
	
	// 해당 날씨, 시간, 지역에 맞는 온도 조회
	@Test
	void selectWeatherTemp() {
		Weather w = new Weather();
		w.setWdate("2023-03-07 07%");
		w.setLocname("부산");
		System.out.println(wMapper.selectWeatherTemp(w));
	}
}
