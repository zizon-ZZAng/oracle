package service;

import connection.MyBatisContext;
import dto.Weather;
import mapper.WeatherMapper;

public interface WeatherService {
	
	
	final WeatherMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(WeatherMapper.class);
	
	// Insert
	public int weatherInsert(Weather w);
	
	// Update
	public int weatherUpdate(Weather w);
	
	// Select
	public Weather weatherSelect(Weather w);
	
	// Delete
	public int weatherDelete(Weather w);
	
}