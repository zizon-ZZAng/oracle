package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Recommend;

public interface RecommendMapper {
	
	
	//추천목록 삽입
	@Insert({
		" INSERT INTO recommend1(no,id,clno,code) ", 
	    " VALUES(SEQ_RECOMMEND_NO1.NEXTVAL,#{id},#{clno}, #{code}) "
	    
	})
	public int insertRecommend(Recommend r);
	
	//id별 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend1 r where id=#{id} "
	})
	public List<Recommend> selectRecommendId();
	
	//특정 옷 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend1 r where no=#{no} "
	})
	public List<Recommend> selectRecommendNo(int no);
	
	//특정날씨 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend1 r where code=#{code} "
	})
	public List<Recommend> selectRecommendCode(int code);
	
	
	
	@Update({
		" <script> ",
		" UPDATE recommend1 SET no=#{obj.no} ",
			" <if test='obj.id != id'> ",
				" ,id=#{obj.id} ",
			" </if> ",
			
			" <if test='obj.code != null '> ",
				" , code=#{obj.code} ",
			" </if> ",
			" WHERE reno=#{obj.reno} ",
			" </script> "
		
	})
	public int Recommend1UpdateOne(@Param("obj") Recommend obj);
	
}
