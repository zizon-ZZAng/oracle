package service;

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
	public Weather weatherSelect(Weather w) {
		try {
			return mapper.weatherSelect(w);
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

}
