package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import mapper.RecommendMapper;

class RecommendTest {

	RecommendMapper mapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);
	
	@Test
	void clothesRecommend() {
		Map<String, Object> map = new HashMap<>();
		map.put("wdate", "2023-03-07 10:00:00");
//		map.put("address", "부산");
		map.put("id", "b");
		
		List<Map<String, Object>> list = mapper.clothesRecommend(map);
		
		for(Map<String, Object> m : list) {
			
			System.out.println(m.toString());
		}
		
		
	}

}
