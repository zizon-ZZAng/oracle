package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecommendMapper {

	// 옷추천
	@Select("SELECT * FROM view_drank_mcv WHERE wdate=#{wdate} AND (catetype='상의'OR catetype='하의' OR catetype='신발' ) AND id=#{id}} AND rank=1) AND id=#{id}")
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map);

	// 옷추천 - 상의
	@Select("SELECT * FROM view_drank_mcv WHERE wdate=#{wdate} AND catetype='상의' AND id=#{a} AND rank=1")
	public List<Map<String, Object>> clothesTopRecommend(Map<String, Object> map);

	// 옷추천 - 하의
	@Select("SELECT * FROM view_drank_mcv WHERE wdate=#{wdate} AND catetype='하의' AND id=#{a} AND rank=1")
	public List<Map<String, Object>> clothesBottomRecommend(Map<String, Object> map);

	// 옷추천 - 신발
	@Select("SELECT * FROM view_drank_mcv WHERE wdate=#{wdate} AND catetype='신발' AND id=#{a} AND rank=1")
	public List<Map<String, Object>> clothesShoesRecommend(Map<String, Object> map);

}
