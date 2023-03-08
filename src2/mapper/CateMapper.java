package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import dto.Cate;

@Mapper
public interface CateMapper {
	// 의류 종류 조회
	@Select({" SELECT ca.* FROM cate2 ca "})
	public List<Cate> selectCate();
	
	// 의류 종류 추가
	@Insert({" INSERT INTO cate2(type) VALUES(#{type}) "})
	public int insertCate(String type);
	
	// 의류 종류 삭제
	@Delete({" DELETE FROM cate2 WHERE type = #{type} "})
	public int deleteCate(String type);
}
