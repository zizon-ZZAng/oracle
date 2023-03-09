package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Location;
import mapper.LocationMapper;

class LocationTest {
	
	LocationMapper lMapper = MyBatisContext.getSqlSession().getMapper(LocationMapper.class);
	
	// 지역 데이터 입력
	@Test
	void insertLocation() {
		Location l = new Location();
		l.setName("대전");
		
		int ret = lMapper.insertLocation(l);
		System.out.println(ret);
	}
	
	// 지역 전체 조회
	@Test
	void selectLocation() {
		Object[] list = lMapper.selectLocation();
		for(Object location : list) {
			System.out.println(location);
		}
	}
		
}
