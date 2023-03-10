package mapper1;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto1.Recommend;

public interface RecommendMapper {
	
	// 추천목록 삭제
	@Delete({
		" DELETE FROM recommend0 WHERE reno = #{reno} "
	})
	public int deleteRecommend(int reno);
	
	
	//추천목록 삽입
	@Insert({
		" INSERT INTO recommend0(reno,id,code,clno) ", 
	    " VALUES(SEQ_RECOMMEND_NO1.NEXTVAL,#{id},#{code},#{clno}) "
	    
	})
	public int insertRecommend(Recommend r);
	
	
	//id별 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend0 r where id=#{id} "
	})
	public List<Recommend> selectRecommendId();
	
	
	//특정 옷 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend0 r where clno=#{clno} "
	})
	public List<Recommend> selectRecommendNo(int no);
	
	
	//특정날씨 추천목록 보기
	@Select({
		" SELECT r.* FROM recommend0 r where code=#{code} "
	})
	public List<Recommend> selectRecommendCode(int code);
	
	
	//아이디별 시간별 추천옷 보기
	@Select({
		" SELECT clno ",
		" FROM recommend0 r ",
		" WHERE r.temperature=#{temperature} "
	})
	public int selectRecommendSetno(float temperature);
	
	
	@Update({
		" <script> ",
		" UPDATE recommend0 SET clno=#{obj.clno} ",
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
