package service;

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
	public Location locationSelect (Location l);
	
	// Delete
	public int locationDelete (Location l);
	
	
	
}
