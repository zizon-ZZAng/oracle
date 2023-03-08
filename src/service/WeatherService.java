package service;

import java.util.List;

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
	public List<Weather> weatherSelect();
	
	// Delete
	public int weatherDelete(Weather w);
	
	
	// 시간가져오기
	public List<Weather> weatherSelectHOUR(Weather w);
	
	// 날짜가져오기
	public List<Weather> weatherSelectDATE(Weather w);
	
}
