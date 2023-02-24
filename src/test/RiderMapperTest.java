package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Rider;
import mapper.RiderMapper;

class RiderMapperTest {
	RiderMapper ridMapper 
		= MyBatisContext.getSqlSession().getMapper(RiderMapper.class);
	
	// 배달자 등록
	@Test
	void insertRider() {
		Rider rider = new Rider();
		rider.setPhone("010-0000-0005");
		rider.setName("마마마");
		rider.setPassword("e");
		
		System.out.println(ridMapper.insertRider(rider));
	}
	
	// 배달자 1명 조회
	@Test
	void selectRiderOne() {
		System.out.println(ridMapper.selectRiderOne("010-0000-0004").toString());
	}

}
