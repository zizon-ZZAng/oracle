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

	MemberMapper Mmapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);

	@Test
	void procMemberInsert() {
		// 전달할 map 객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", "jkjkjk");
		map.put("userpw", "asdfghj");
		map.put("username", "ㅇㅅㅇ");
		map.put("userage", 50);
		map.put("userphone", "010-2222-1113");
		map.put("usergender", "M");
		map.put("ret", -1); // ret 값은 임의의 값인 -1로 설정함

		// 프로시저 호출하기 => ret값이 변경 되었음
		Mmapper.callProcMemberInsert(map);

		// 변경된 ret 값 확인 => 1이면 성공 0이면 실패
		System.out.println(map.get("ret"));
	}

	@Test
	void procMemberSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usergender", "F");
		map.put("cursor", null);

		// 프로시저 호출하기 cursor 부분 결과값 변경됨
		Mmapper.callProcMemberSelect(map);
		System.out.println(map.get("cursor").toString());
	}

	@Test
	void memberInsertBatch() {
		List<Member> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Member member = new Member();
			member.setUserid("kkk100" + i);
			member.setUsername("이름" + i);
			member.setUserage(23);
			member.setUsergender("F");
			member.setUserphone("010-7777-888" + i);
			member.setUserpw("암호" + i);
			list.add(member);
		}

		int ret = Mmapper.memberInsertBatch(list);
		System.out.println(ret);
	}
	
	@Test
	void memberUpdateBatch() {
		List<Member> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Member member = new Member();
			member.setUserid("kkk100" + i);
			member.setUsername("이름A" + i);
			member.setUserage(23);
			list.add(member);
		}

		int ret = Mmapper.memberUpdateBatch(list);
		System.out.println(ret);
	}
	
	@Test
	void memberUpsert() {
		
		Member m = new Member();
		m.setUserid("a777");
		m.setUsername("바꿀이");
		m.setUserage(50);
		m.setUserpw("kkkk");
		m.setUserphone("010-8888-9999");
		m.setUsergender("F");

		System.out.println(Mmapper.memberUpsert(m));
	}
}
