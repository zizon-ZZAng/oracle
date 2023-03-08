package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Recommend;

@Mapper
public interface RecommendMapper {

	// 옷추천 (상의)
	@Select("SELECT * FROM view_drank_mcv WHERE wdate=#{wdate} AND (catetype='상의') AND id=#{id} AND rank=1")
	public Map<String, Object> clothesRecommendTop(Map<String, Object> map);


}
