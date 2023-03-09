package service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import connection.MyBatisContext;
import mapper.RecommendMapper;

public interface RecommendService {
	RecommendMapper mapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);

	// 옷추천
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map);

	// 옷추천 (상의)
	public Map<String, Object> clothesRecommendTop(Map<String, Object> map);

	// 옷추천 (하의)
	public Map<String, Object> clothesRecommendBottom(Map<String, Object> map);

	// 옷추천 (신발)
	public Map<String, Object> clothesRecommendShoes(Map<String, Object> map);
}
