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
	void ProcMemberInsert() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userid","dasdfa4");
		map.put("userpw","2a3b4c");
		map.put("username","이삼사");
		map.put("userage",23);
		map.put("userphone","010-0218-9978");
		map.put("usergender","F");
		map.put("ret",-1);	//ret값은 임의의 값인 -1로 설정함
		
		//프로시저 호출하기 => ret값이 변경이 되었음
		mapper.callProcMemberInsert(map);
		
		//변경된 ret값을 확인함 => 1이면 성공, 0이면 실패
		System.out.println(map.get("ret"));
		
		
	}
	
	@Test
	void ProcMemberSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usergender", "F");
		map.put("cursor",null);
		
		//프로시저 호출하기 cursor부분 결과값 변경됨.
		mapper.callProcMemberSelect(map);
		System.out.println(map.get("cursor"));
	}
	
	
	
	//시퀀스가 없는 데이터 추가
	@Test
	void memberInsertBatch() {
		List<Member> list = new ArrayList<Member>();
		for(int i=0; i<=3;i++) {
			Member member = new Member();
			member.setUserid("qad"+i);
			member.setUserpw("qad"+i);
			member.setUserage(22);
			member.setUsername("이름름"+i);
			member.setUsergender("F");
			member.setUserphone("010-7788-775"+i);
			
			list.add(member);
		
		}
		int ret = mapper.memberInsertBatch(list);
		System.out.println(ret);
	}
	
//	나 지금 뭐하고 이쓴ㄴ거지? 뭐?
	
	//일괄 수정
	@Test
	void memberUpdateBatch() {
		List<Member> list = new ArrayList<>();
		
		for(int i=1; i<=2;i++) {
			Member member = new Member();
			
			member.setUserid("a"+i);
			
			member.setUsername("가나다");
			member.setUserage(27);
			
			list.add(member);
		
		}
		int ret = mapper.memberUpdateBatch(list);
		System.out.println(ret);
		
	}
	
	
	//해당 아이디가 있으면 update 없으면 insert 수행
	@Test
	void memberUpsert() {
		Member m = new Member();
		m.setUserid("aaa");
		m.setUsername("종이");
		m.setUserpw("aaa");
		m.setUsergender("F");
		m.setUserphone("010-5555-8888");
		m.setUserage(17);
		
		int ret = mapper.memberUpsert(m);
		System.out.println(ret);
		
	}
	
	//내가 원하는 값만 수정
	@Test
	void memberUpdateOne() {
		
		Member m = new Member();
		
		m.setUserid("a1a2s");
		m.setUsername("김헤이즐넛");
		m.setUserage(23);
		m.setUserphone(null);
		m.setUsergender("F");
		
		
		int ret = mapper.memberUpdateOne(m);
		System.out.println(ret);
	}
	
	
	@Test
	void memberLikeList() {
		Map<String, String> map = new HashMap<>();
		map.put("txt", "a");
		map.put("column", "userid");
		
//		map.put("txt1", "a");
//		map.put("column1", "userid");
		
		List<Member> list = mapper.memberLikeList(map);
		System.out.println(list.toString());
	}
	
	

}
