package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Clothes;
import dto.ClothesCate;

@Mapper
public interface ClothesCateMapper {

	// 삽입
	@Insert({
		" INSERT INTO clothescate0(name) ",
	    " VALUES(#{name}) "	
	})
	public int insertClothesCate(ClothesCate c);
	
	// 업데이트
	@Update({
		" UPDATE clothescate0 SET name =#{name} ",
		" WHERE type =#{type} "
	})
	public int updateClothesCate(ClothesCate c);
	
	// 삭제
	@Delete({
		" DELET FROM clothescate0 ",
		" WHERE type=#{obj.type} "
	})
	public int deleteClothesCate(@Param("obj") ClothesCate obj);
	
	// 출력
	@Select({
		" SELECT * FROM clothescate0 "
	})
	public List<ClothesCate> selectClothesCateList();
}
