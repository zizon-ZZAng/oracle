package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Recommend;

public interface RecommendMapper {
	
	
	//추천목록 넣기
	@Insert({
		" INSERT INTO recommend1(no,id,clno,code) ", 
	    " VALUES(SEQ_RECOMMEND_NO1.NEXTVAL,#{id},#{clno}, #{code}) "
	    
	})
	public int insertRecommend(Recommend r);
	
	@Select({
		
	})
	public int selectRecommend(Recommend r);
	
	@Update({
		" UPDATE member1 SET name=#{name} , password=#{password} ,address=#{address} ",
				" WHERE id=#{id} "
	})
	public int updateRecommend(Recommend r);
	
}
