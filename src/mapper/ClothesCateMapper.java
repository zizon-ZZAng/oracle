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
import dto.Customer;
import dto.Menu;

@Mapper
public interface ClothesCateMapper {

	@Insert({
		" INSERT INTO clothescate1(type, name) ",
	    " VALUES(#{type}, #{name}) "	
	})
	public int insertClothesCate(ClothesCate c);
	
	
	@Update({
		" UPDATE clothescate1 SET name =#{name} ",
		" WHERE type =#{type} "
	})
	public int updateClothesCate(ClothesCate c);
	
	
	@Delete({
		" DELET FROM clothescate1 ",
		" WHERE type=#{obj.type} "
	})
	public int deleteClothesCate(@Param("obj") ClothesCate obj);
	
	
	@Select({
		" SELECT * FROM clothescate1 "
	})
	public List<ClothesCate> selectClothesCateList();
}
