package test;

import java.util.Date;
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
		w.setNo(33);
		System.out.println(mapper.weatherInsert(w));
	}

	@Test
	void weatherUpdate() {
		Weather w = new Weather();
		w.setWeather("맑음");
		w.setTemperature(25.3f);
		w.setNo(33);
		w.setCode(22);
		System.out.println(mapper.weatherUpdate(w));
	}

	@Test
	void weatherSelectHOUR() {
		Weather w = new Weather();
		w.setNo(30);
			List<Weather> list =mapper.weatherSelectHOUR(w);
			for(Weather obj : list) {
			System.out.println(obj);}
		}
	
	@Test
	void weatherSelectDATE() {
	
		Weather w = new Weather();
		w.setNo(30);
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
	@Test
	void weatherInsert30() {
		for (int i = 1; i <= 24; i++) {
			Weather w = new Weather();
			w.setWeather("맑음");
			w.setTemperature(20.5f);
			w.setNo(31);
			System.out.println(mapper.weatherInsert(w));
		}
	}

	// 시간 업데이트
	@Test
	void weatherUpdateHour() {
		for (int i = 1; i <= 24; i++) {
			Weather w = new Weather();
			w.setCode(34 + i);

			if (i < 10) {
				w.setRegdate2("2023-03-06-0" + i);
			} else {
				w.setRegdate2("2023-03-06-" + i);
			}
			System.out.println(mapper.weatherUpdateHour(w));
		}
	}

	@Test
	void weatherUpdateTemp() {
		for (int i = 1; i <= 24; i++) {
			Weather w = new Weather();
			w.setCode(34+i);
			if (i < 7) {
				w.setTemperature(9.7f);
			} else if (i >= 7 && i <= 12) {
				w.setTemperature(18f);
			} else if (i >= 11 && i <= 18) {
				w.setTemperature(22.9f);
			} else if (i >= 19 && i <= 24) {
				w.setTemperature(15f);
			}
			System.out.println(mapper.weatherUpdateTemp(w));
		}
	}
}
