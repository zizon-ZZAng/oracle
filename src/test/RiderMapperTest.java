package test;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Rider;
import mapper.RiderMapper;

class RiderMapperTest {
	RiderMapper rMapper 
		= MyBatisContext.getSqlSession().getMapper(RiderMapper.class);
	
	//메뉴 등록
	@Test
	void insertRider() {
		Rider r = new Rider();
		r.setName("ㅊㅋㅌ");
		r.setPhone("010-0000-0004");
		r.setPassword("jkl");
		
		System.out.println(rMapper.insertRider(r));
	}
	
	//배달자 1명 조회
	@Test
	void selectRiderList() {
		Rider r = rMapper.selectRiderList("010-0000-0001");
		System.out.println(r.toString());
		
	}
	

}
