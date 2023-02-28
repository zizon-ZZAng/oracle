package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.NNN;
import mapper.NNNMapper;

class NNNTest {
	NNNMapper mapper = MyBatisContext.getSqlSession().getMapper(NNNMapper.class);	

	@Test
	void procNNNInsert() {
	// 전달할 map객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "A2");
		map.put("name", "히히");
		map.put("pw", "mm");
		map.put("age", 15);
		
		map.put("ret", -1); //ret값은 임의로 -1
		//프로시저 호출하기 => ret값이 변경되어 있음
		mapper.callProcNNNInsert(map);
		//변경된 ret값을 확인함 => 1이면 성공 0이면 실패
		System.out.println(map.get("ret"));
	}

	
	@Test
	void procNNNUpsert() {
	// 전달할 map객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "A2");
		map.put("name", "되랏");
		map.put("pw", "에잇");
		map.put("age", 15);
		
		map.put("ret", -1); //ret값은 임의로 -1
		//프로시저 호출하기 => ret값이 변경되어 있음
		mapper.callProcNNNUpsert(map);
		//변경된 ret값을 확인함 => 1이면 성공 0이면 실패
		System.out.println(map.get("ret"));
	}

	
	@Test
	void procNNNSelect() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "A2");
		map.put("cursor", null);
		
		// 프로시저 호출하기 cursor부분 결과값변경
		mapper.callProcNNNSelect(map);
		System.out.println("ret");
	}
	
	
	@Test
	void nnnInsertBatch() {
	// 전달할 map객체 생성
		List<NNN> list = new ArrayList<NNN>();
		for(int i=0; i<3; i++) {
		NNN nnn = new NNN();
		nnn.setId("B"+i);
		nnn.setName("Bname"+i);
		nnn.setAge(20+i);
		nnn.setPw("pw"+i);
		list.add(nnn);
		}
		int ret = mapper.nnnInsertBatch(list);
		System.out.println(ret);
	}

	
}
