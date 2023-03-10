package service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.Weather;

public class WeatherServiceImpl implements WeatherService {

	@Override
	public int weatherInsert(Weather w) {
		try {
			return mapper.weatherInsert(w);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public int weatherUpdate(Weather w) {
		try {
			return mapper.weatherUpdate(w);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public List<Weather> weatherSelect() {
		try {
			return mapper.weatherSelect();
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	@Override
	public int weatherDelete(Weather w) {
		try {
			return mapper.weatherDelete(w);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public List<Weather> weatherSelectHOUR(Weather w) {
		try {
			return mapper.weatherSelectHOUR(w);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}



	@Override
	public List<Weather> weatherSelectWeather() {
		try {
			return mapper.weatherSelectWeather();
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> weatherSelectWVTemp(Map<String, Object> map) {
		try {
			return mapper.weatherSelectWVTemp(map);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}
	
//	@Override
//	public Map<String, Object> weatherSelectWVTemp(Map<String, Object> map) {
//		try {
//			return mapper.weatherSelectWVTemp(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//			MyBatisContext.getSqlSession().close();
//			return null;
//		}
//
//	}

	@Override
	public String[] weatherSelectDATE() {
			try {
				return mapper.weatherSelectDATE();
			} catch (Exception e) {
				e.printStackTrace();
				MyBatisContext.getSqlSession().close();
				return null;
			}
		}
	}

	


	
	
	
