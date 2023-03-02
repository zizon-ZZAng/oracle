package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberMapperTest {
	MemberMapper mMapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	@Test
	void procMemberInsert() {
		// 전달할 map 객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", "b456");
		map.put("userpw", "a");
		map.put("username", "java 테스트");
		map.put("userage", 12);
		map.put("userphone", "010-2222-2222");
		map.put("usergender", "F");		
		map.put("ret", -1); // ret 값은 임의의 값인 -1로 설정함
		
		// 프로시저 호출하기 => ret 값이 변경되었음
		mMapper.callProcMemberInsert(map);
		
		// 변경된 ret 값을 확인함 => 1이면 성공, 0이면 실패
		System.out.println(map.get("ret"));
	}
	
	@Test
	void procMemberSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usergender", "F");
		map.put("cursor", null);
		
		// 프로시저 호출하기 cursor부분 결과값 변경됨
		mMapper.callProcMemberSelect(map);
		System.out.println(map.get("cursor").toString());
	}
	
	@Test
	void memberInsertBatch() {
		List<Member> list = new ArrayList<>();
		for (int i=0; i<3; i++) {
			Member member = new Member();
			member.setUserid("aa100"+i);
			member.setUserpw("암호");
			member.setUsername("이름");
			member.setUserage(23);
			member.setUserphone("010-0000-000"+i);
			member.setUsergender("F");
			list.add(member);
		}
		System.out.println(mMapper.memberInsertBatch(list));
	}
	
	@Test
	void memberUpdateBatch() {
		List<Member> list = new ArrayList<>();
		for (int i=0; i<2; i++) {
			Member member = new Member();
			member.setUserid("aa100"+i);
			member.setUsername("이름변경"+i);
			member.setUserage(26+i);
			list.add(member);
		}
		System.out.println(mMapper.memberUpdateBatch(list));
	}
	
	@Test
	void memberMergeBatch() {
		Member member = new Member();
		member.setUserid("aa1003");
		member.setUserpw("암호2");
		member.setUsername("이름변경~");
		member.setUserage(26);
		member.setUserphone("010-0000-0302");
		member.setUsergender("M");
		
		System.out.println(mMapper.memberMergeBatch(member));
	}
}
