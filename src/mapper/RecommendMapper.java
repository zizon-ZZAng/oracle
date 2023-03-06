package mapper;

import org.apache.ibatis.annotations.Insert;

import dto.Recommend;

public interface RecommendMapper {
	
	
	//추천목록 넣기
	@Insert({
		" INSERT INTO recommend1(no,id,clno,code) ", 
	    " VALUES(SEQ_RECOMMEND_NO1.NEXTVAL,'kim',10001, 10) "
	    
	})
	public int insertRecommend(Recommend r);
}
