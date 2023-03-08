package service;

import java.util.List;

import dto.Weather;

public class WeatherServiceImpl implements WeatherService{

	@Override
	public int weatherInsert(Weather w) {
		try {
			return mapper.weatherInsert(w);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int weatherUpdate(Weather w) {
		try {
			return mapper.weatherUpdate(w);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Weather> weatherSelect() {
		try {
			return mapper.weatherSelect();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int weatherDelete(Weather w) {
		try {
			return mapper.weatherDelete(w);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Weather> weatherSelectHOUR(Weather w) {
		try {
			return mapper.weatherSelectHOUR(w);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Weather> weatherSelectDATE(Weather w) {
		try {
			return mapper.weatherSelectDATE(w);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
