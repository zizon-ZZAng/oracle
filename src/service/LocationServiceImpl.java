package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Location;

public class LocationServiceImpl implements LocationService {

	@Override
	public int insertLocation(Location location) {
		try {
			return mapper.insertLocation(location);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

	@Override
	public Object[] selectLocation() {
		try {
			return mapper.selectLocation();

		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

}
