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
	
	

}
