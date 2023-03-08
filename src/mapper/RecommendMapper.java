package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Recommend;

@Mapper
public interface RecommendMapper {
	
	//옷추천 
	@Select("SELECT * FROM view_mcv WHERE wdate=#{wdate} AND (catetype='상의' OR catetype='하의' OR catetype='신발') AND id=#{id}")
	public List<Map<String, Object>> clothesRecommend(Map<String, Object> map);
	
	//옷추천 - 상의
	
	

}
