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

	MemberMapper mMapper 
	= MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	@Test
	void procMemberInsert() {
		// 전달할 map객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("userid", "d234");
	    map.put("userpw", "a1");
	    map.put("username", "java테스트");
	    map.put("userage", 12);
	    map.put("userphone", "010-2222-2222");
	    map.put("usergender", "M");
	    map.put("ret", -1); // ret값은 임의의 값인 -1로 설정함.
	    
	    // 프로시저 호출하기 => ret값이 변경이 되었음
	    mMapper.callProcMemberInsert(map);
	    
	    // 변경된 ret값을 확인함. => 1이면 성공, 0이면 실패
	    System.out.println(map.get("ret"));
	}
	
	@Test
	void procMemberSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usergender", "F");
		map.put("cursor", null);
		
		// 프로시저 호출하기 cursor부분 결과값 변경됨.
		mMapper.callProcMemberSelect(map);
		System.out.println(map.get("cursor").toString());
	}

	
	@Test
	void memberInsertBatch() {
		List<Member> list = new ArrayList<Member>();
		for(int i=0; i<3; i++) {
			Member member = new Member();
			member.setUserid("aaa100"+i);
			member.setUserpw("암호");
			member.setUsername("이름");
			member.setUserage(23);
			member.setUserphone("010-0000-000"+i);
			member.setUsergender("M");
			list.add(member);
		}
		int ret = mMapper.memberInsertBatch(list);
		System.out.println(ret); // 숫자 3이 출력됨.
	}
	
	@Test
	void memberUpdateBatch() {
		List<Member> list = new ArrayList<Member>();
		for(int i=0; i<2; i++) {
			Member member = new Member();
			member.setUserid("a9");
			member.setUsername("바뀐 이름");
			member.setUserage(45);
			list.add(member);
		}
			int ret = mMapper.memberUpdateBatch(list);
			System.out.println(ret);
	}
	
	@Test
	void memberUpsert() {
		Member m = new Member();
		m.setUserid("e10");
		m.setUsername("도로롱");
		m.setUserpw("aaaa");
		m.setUserage(17);
		m.setUserphone("010-1004-1004");
		m.setUsergender("M");
		
		int ret = mMapper.memberUpsert(m);
		System.out.println(ret);
	}
}
