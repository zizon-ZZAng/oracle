package service;

import connection.MyBatisContext;
import dto.Location;
import mapper.LocationMapper;

public interface LocationService {
	final LocationMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(LocationMapper.class);

	// 지역 데이터 입력
	public int insertLocation(Location location);

	// 지역 조회
	public Object[] selectLocation();
}
