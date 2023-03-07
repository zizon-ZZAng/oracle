package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import mapper.RecommendMapper;

public interface RecommendService {
	RecommendMapper mapper
		= MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);
	
	// 옷추천
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map);
}
