package service;

import java.util.List;
import java.util.Map;

public class RecommendServiceImpl implements RecommendService {
	// 옷추천
	@Override
	public Map<String, Object> clothesRecommendTop(Map<String, Object> map) {
		try {
			return mapper.clothesRecommendTop(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
