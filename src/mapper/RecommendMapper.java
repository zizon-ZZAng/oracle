package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Recommend;

public interface RecommendMapper {
	
	// 추천목록 삭제
	@Delete({
		" DELETE FROM recommend1 WHERE reno = #{reno} "
	})
	public int deleteRecommend(int reno);
	
	
	//추천목록 삽입
	@Insert({
		" INSERT INTO recommend1(reno,id,code,setno) ", 
	    " VALUES(SEQ_RECOMMEND_NO1.NEXTVAL,#{id},#{code},#{setno}) "
	    
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
	
	
	//아이디별 시간별 추천옷 보기
	@Select({
		" SELECT r.* ",
		" FROM recommend1 r ",
		" WHERE id=#{id.id} AND code=#{obj.code} "
	})
	public Recommend selectRecommendSetno(@Param("id")String id, @Param("obj")int code);
	
	
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
