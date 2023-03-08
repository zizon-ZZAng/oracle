package service;

import java.util.List;
import java.util.Map;

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
	
	// 뷰로(지역이름,날짜,시간으로) 기온 가져오기
	public float weatherSelectWVTemp(Map<String, Object> map);


	
}
