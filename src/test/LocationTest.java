package test;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Location;
import dto.Weather;
import mapper.LocationMapper;

class LocationTest {
	LocationMapper mapper = MyBatisContext.getSqlSession().getMapper(LocationMapper.class);
	Location l = new Location();

	@Test
	void locationInsert() {
		l.setName("마산");
		System.out.println(mapper.locationInsert(l));
	}
	
	@Test
	void locationUpdate() {
		l.setNo(48);
		l.setName("전주");
		System.out.println(mapper.locationUpdate(l));
	}
	
	@Test
	void locationSelect() {
		l.setNo(48);
		System.out.println(mapper.locationSelect(l));
	}
	
	@Test
	void locationDelete() {
		l.setNo(49);
		System.out.println(mapper.locationDelete(l));
	}
	
	

}
