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
	void callProcMemberInsert() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid","d234");
		map.put("userpw","a");
		map.put("username","java테스트");
		map.put("userage",12);
		map.put("userphone","010-2222-3333");
		map.put("usergender","M");		
		map.put("ret", -1);	// ret 값은 임의의 값인 -1로 설정함.
		
		// 프로시저 호출하기 => ret 값이 변경이 되었음.
		mapper.callProcMemberInsert(map);
		
		// 변경된 ret 값을 확인함. => 1이면 성공 0이면 실패.
		System.out.println(map.get("ret"));		
		
	}
	
	
	@Test
	void callProcMemberUpsert() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid","d234");
		map.put("userpw","a");
		map.put("username","java테스트");
		map.put("userage",12);
		map.put("userphone","010-2222-3333");
		map.put("usergender","M");		
		map.put("ret", -1);	// ret 값은 임의의 값인 -1로 설정함.
		
		// 프로시저 호출하기 => ret 값이 변경이 되었음.
		mapper.callProcMemberUpsert(map);
		
		// 변경된 ret 값을 확인함. => 1이면 성공 0이면 실패.
		System.out.println(map.get("ret"));		
		
	}
	
	
	@Test
	void procMemberSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usergender", "F");
		map.put("cursor", null);
		
		// 프로시저 호출하기 cursor부분 결과값 변경됨.
		mapper.callProcMemberSelect(map);
		System.out.println( map.get("cursor").toString() );
	}
	
	
	@Test
	void memberInsertBatch() {
		List<Member> list = new ArrayList<>();
		for(int i=0;i<3;i++) {
			Member member = new Member();
			member.setUserage(25);
			//member.setUserdate(new Date());
			member.setUsergender("F");
			member.setUserid("aaa100" + i);
			member.setUsername("자바이름");
			member.setUserphone("010-0000-000" + i);
			member.setUserpw("암호");
			list.add(member);
		}
		int ret = mapper.memberInsertBatch(list);
		System.out.println(ret); //숫자 3이 출력됨.
	}
	
	
	@Test
	void memberUpdateBatch() {
		List<Member> list = new ArrayList<>();
		for(int i=0;i<3;i++) {
			Member member = new Member();
			member.setUserage(i*100);
			//member.setUserdate(new Date());
			//member.setUsergender("F");
			member.setUserid("aaa100" + i);
			member.setUsername("이름변경"+i);
			//member.setUserphone("010-0000-000" + i);
			//member.setUserpw("암호");
			list.add(member);
		}
		int ret = mapper.memberUpdateBatch(list);
		System.out.println(ret); //숫자 3이 출력됨.
	}
	
	
	@Test
	void memberMerge() {
		Member member = new Member();
		member.setUserage(101);
		member.setUsergender("F");
		member.setUserid("a2003");
		member.setUsername("머지성공2");
		member.setUserphone("010-0000-000");
		member.setUserpw("새암호");
			
		int ret = mapper.memberMerge(member);
		System.out.println(ret);
	}
	
	@Test
	void memberUpdateOne() {
		Member member = new Member();
		member.setUserage(0);
		member.setUsergender(null);
		member.setUserid("a");
		member.setUsername("이름");
		member.setUserphone("010-0000");
		
		int ret = mapper.memberUpdateOne(member);
		System.out.println(ret);
	}
	
	@Test
	void memberLikeList() {
		Map<String, String> map = new HashMap<>();
		
		map.put("column","userid");
		map.put("txt", "a");
		
		List<Member> list = mapper.memberLikeList(map);
		
		for(Member m : list) {
			System.out.println(m.toString());
		}
	}
}
