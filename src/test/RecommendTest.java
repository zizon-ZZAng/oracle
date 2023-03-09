package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Recommend;
import mapper.RecommendMapper;

class RecommendTest {

	RecommendMapper mapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);
	
	
	//옷추천
	@Test
	void clothesRecommend() {
	
		Map<String, Object> map = new HashMap<>();
		map.put("wdate", "2023-03-07 10:00:00");
		map.put("id", "b");
		
		List<Map<String, Object>> list = mapper.clothesRecommend(map);
		
		for(Map<String, Object> m : list) {
			
			System.out.println(m.toString());
		}
		
	}
	
	//상의 추천
	@Test
	void clothesRecommendTop() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("wdate", "2023-03-07 10:00:00");
		map.put("id", "b");
		
		System.out.println(mapper.clothesRecommendTop(map));

	}
	

	
	//하의 추천
	@Test
	void clothesRecommendBottom() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("wdate", "2023-03-07 10:00:00");
		map.put("id", "b");
		
		System.out.println(mapper.clothesRecommendBottom(map)); 
		
	}
	
	
	//액세서리 추천
	@Test
	void clothesRecommendShoes() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("wdate", "2023-03-07 10:00:00");
		map.put("id", "b");
		
		System.out.println(mapper.clothesRecommendShoes(map)); 
		
	}
	
	

}
