package service;

import java.util.List;
import java.util.Map;

public class RecommendServiceImpl implements RecommendService {
	// 옷추천
	@Override
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map) {
		try {
			return mapper.clothesRecommend(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
