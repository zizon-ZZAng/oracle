package test1;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto1.Location;
import dto1.Weather;
import mapper1.LocationMapper;

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
		System.out.println(mapper.locationSelect());
	}
	
	@Test
	void locationDelete() {
		l.setNo(49);
		System.out.println(mapper.locationDelete(l));
	}
	
	

}
