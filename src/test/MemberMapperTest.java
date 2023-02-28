package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
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
}
