package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Recommend;

@Mapper
public interface RecommendMapper {

	// 옷추천(상,하,신) 한 번에 촤라락
	@Select("SELECT * FROM view_drank_mcv WHERE wdate LIKE #{wdate} || '%' AND (catetype='상의' OR catetype='하의' OR catetype='신발'  ) AND id=#{id} AND rank=1")
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map);

	// 옷추천 (상의)
	@Select("SELECT * FROM view_drank_mcv WHERE wdate LIKE #{wdate} || '%' AND (catetype='상의') AND id=#{id} AND rank=1")
	public Map<String, Object> clothesRecommendTop(Map<String, Object> map);

	// 옷추천 (하의)
	@Select("SELECT * FROM view_drank_mcv WHERE wdate LIKE #{wdate} || '%' AND (catetype='하의') AND id=#{id} AND rank=1")
	public Map<String, Object> clothesRecommendBottom(Map<String, Object> map);

	// 옷추천 (신발)
	@Select("SELECT * FROM view_drank_mcv WHERE wdate LIKE #{wdate} || '%' AND (catetype='신발') AND id=#{id} AND rank=1")
	public Map<String, Object> clothesRecommendShoes(Map<String, Object> map);

	
	
	
	// 테스트임 -상의-
	@Select("SELECT * FROM view_drank_mcv WHERE wdate LIKE #{wdate} || '%' AND (catetype='상의') AND id=#{id} AND rank=#{rank}")
	public Map<String, Object> clothesRecommendTopTest(Map<String, Object> map);

}
