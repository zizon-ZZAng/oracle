package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Location;
import mapper.LocationMapper;

public interface LocationService {
	
	final LocationMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(LocationMapper.class);

	
	// Insert
	public int locationInsert (Location l);

	// Update
	public int locationUpdate (Location l);
	
	// Select
	public List<Map<String, Object>> locationSelect();
	
	// Delete
	public int locationDelete (Location l);
	
	
	
}