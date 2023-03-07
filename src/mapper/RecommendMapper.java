package mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecommendMapper {
	
	//옷추천
	@Select("SELECT * FROM view_clovws2 where locname=#{locname} AND wdate=#{wdate} AND (catetype='상의' OR catetype='하의' OR catetype='신발')")
	public 

}
