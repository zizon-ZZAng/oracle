package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;

public class RecommendServiceImpl implements RecommendService {
	// 옷추천
	@Override
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map) {
		try {
			return mapper.clothesRecommend(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 옷추천 (상의)
	@Override
	public Map<String, Object> clothesRecommendTop(Map<String, Object> map) {
		try {
			return mapper.clothesRecommendTop(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 옷추천 (하의)
	@Override
	public Map<String, Object> clothesRecommendBottom(Map<String, Object> map) {
		try {
			return mapper.clothesRecommendBottom(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 옷추천 (신발)
	@Override
	public Map<String, Object> clothesRecommendShoes(Map<String, Object> map) {
		try {
			return mapper.clothesRecommendShoes(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

}
