package service1;

import connection.MyBatisContext;
import dto1.Location;
import mapper1.LocationMapper;

public interface LocationService {
	
	final LocationMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(LocationMapper.class);

	
	// Insert
	public int locationInsert (Location l);

	// Update
	public int locationUpdate (Location l);
	
	// Select
	public Object[] locationSelect();
	
	// Delete
	public int locationDelete (Location l);
	
	
	
}