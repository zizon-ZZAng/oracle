package mapper1;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto1.Location;

@Mapper
public interface LocationMapper {

	@Insert({
		"INSERT INTO location0 (name) VALUES (#{name})"
	})
	public int locationInsert (Location l);
	
	@Update({
		"UPDATE location0 SET name = #{name} WHERE name = #{name}"
	})
	public int locationUpdate (Location l);
	
	@Select({
		"SELECT * FROM location0 "
	})
	public Object[] locationSelect ();
	
	@Delete({
		"DELETE FROM location0 WHERE name = #{name}"
	})
	public int locationDelete (Location l);
	
	
	
	
	
}
