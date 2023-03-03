package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberTest {
	MemberMapper mapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);	


	@Test
	void memberUpdateBatch() {
		
		List<Member> list = new ArrayList<>();
		for (int i = 0; i<=1; i++) {
			Member m = new Member();

		}
	
		int ret = mapper.memberUpdateBatch(list);
		System.out.println();
	}

//	@Test
//	void memberUpSertBatch() {
//		List<Member> list = new ArrayList<>();
//		for (int i = 0; i<=1; i++) {
//			Member m = new Member();
//			m.setUserid('a2');
//			m.setUserage(66);
//			m.setUsername("djkd");
//			m.setUsergender('F');
//			m.setUserphone('010909890');
//			m.setUserdate('a3');
//
//		?
//
//		int ret = mapper.memberUpSertBatch(list);
//		System.out.println();
//	}
	
	

	@Test
	void memberUpdateOne() {
		Member m = new Member();
		m.setUserid("a2");
		m.setUsername("aaaa");
		m.setUserage(12);
		m.setUserphone("051");
		m.setUsergender("M");
		
		int ret = mapper.memberUpdateOne(m);
		System.out.println(ret);
	}
	
	
	@Test
	   void memberLikeList() {
	      Map<String, String> map = new HashMap<>();
	      map.put("txt", "a");
	      map.put("column", "userid");
	      
//	      map.put("txt1", "a");
//	      map.put("column1", "userid");
	      
	      List<Member> list = mapper.memberLikeList(map);
	      System.out.println(list.toString());
	   }
		
		
		
		
		
		

	
}
