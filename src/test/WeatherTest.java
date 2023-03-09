package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Weather;
import mapper.WeatherMapper;
@Mapper
class WeatherTest {

	WeatherMapper mapper = MyBatisContext.getSqlSession().getMapper(WeatherMapper.class);
	Weather w = new Weather();

	@Test
	void weatherInsert() {
		Weather w = new Weather();
		w.setWeather("흐림");
		w.setTemperature(25.3f);
		w.setName("서울");
		System.out.println(mapper.weatherInsert(w));
	}

	@Test
	void weatherUpdate() {
		Weather w = new Weather();
		w.setWeather("맑음");
		w.setTemperature(25.3f);
		w.setName("서울");
		w.setCode(22);
		System.out.println(mapper.weatherUpdate(w));
	}

	@Test
	void weatherSelectHOUR() {
		Weather w = new Weather();
		w.setName("서울");
			List<Weather> list =mapper.weatherSelectHOUR(w);
			for(Weather obj : list) {
			System.out.println(obj);}
		}
	
	@Test
	void weatherSelectDATE() {
	
		Weather w = new Weather();
		w.setName("서울");
			List<Weather> list = mapper.weatherSelectDATE(w);
			for(Weather obj : list) {
			System.out.println(obj);}
	}
	
	@Test
	void weatherSelectWV() {

		List<Map<String, Object>> list = mapper.weatherSelectWV();
		for(Map<String, Object> map : list) {
			System.out.println(map);
			}
	}

	@Test
	void weatherSelectWeather() {
		List<Weather> list = mapper.weatherSelectWeather();
		for(Weather w : list) {
			System.out.println(w);
			}
	}
	
	@Test
	void weatherSelect() {
		System.out.println(mapper.weatherSelect());
	}

	@Test
	void weatherDelete() {
		Weather w = new Weather();
		w.setCode(22);
		System.out.println(mapper.weatherDelete(w));
	}

	// 일단 24행 일괄추가
	// 수정필요
	@Test
	void weatherInsert30() {
		for (int i = 1; i <= 24; i++) {
			Weather w = new Weather();
			w.setWeather("맑음");
			w.setTemperature(20.5f);
			w.setName("서울");
			System.out.println(mapper.weatherInsert(w));
		}
	}

	// 시간 업데이트
	@Test
	void weatherUpdateHour() {
		for (int i = 0; i <= 24; i++) {
			Weather w = new Weather();
			w.setCode(202 + i);

			if (i < 10) {
				w.setRegdate2("2023-03-13-0" + i);
			} else {
				w.setRegdate2("2023-03-13-" + i);
			}
			System.out.println(mapper.weatherUpdateHour(w));
		}
	}

	// 기온 업데이트
	@Test
	void weatherUpdateTemp() {
		for (int i = 0; i <= 24; i++) {
			Weather w = new Weather();
			w.setCode(202 +i);
			if (i < 7) {
				w.setTemperature(10f);
			} else if (i >= 7 && i <= 12) {
				w.setTemperature(30f);
			} else if (i >= 11 && i <= 18) {
				w.setTemperature(22.9f);
			} else if (i >= 19 && i <= 24) {
				w.setTemperature(19f);
			}
			System.out.println(mapper.weatherUpdateTemp(w));
		}
	}
	
	// 날씨 업데이트
	@Test
	void weatherUpdateWTH() {
		for (int i = 0; i <= 24; i++) {
			Weather w = new Weather();
			w.setCode(154 +i);
			if (i < 7) {
				w.setWeather("맑음");
			} else if (i >= 7 && i <= 12) {
				w.setWeather("안개");
			} else if (i >= 11 && i <= 18) {
				w.setWeather("황사");
			} else if (i >= 19 && i <= 24) {
				w.setWeather("눈");
			}
			System.out.println(mapper.weatherUpdateWTH(w));
		}
	}

	
	@Test
	void weatherSelectWVTemp() {
//		Weather w = new Weather();
//		w.setName("서울특별시");ㄴ
//		w.setW_date("2023-03-06");
//		w.setW_hour("02");
		
		Map<String, Object> map = new HashMap<>();
		map.put("address" , "대구");
		map.put("week", "2023/03/13");
		map.put("hour", "02");
		
		Map<String, Object> list = mapper.weatherSelectWVTemp(map);
		
		//List<Map<String, Object>> list = mapper.weatherSelectWVTemp(map);
		System.out.println(list.toString());
		
//		for(Map<String, Object> m : list) {
//			System.out.println(list.toString());
//		}
	}
}
