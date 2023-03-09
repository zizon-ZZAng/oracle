package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Location;

@Mapper
public interface LocationMapper {

	@Insert({
		"INSERT INTO location0 (no, name) VALUES (seq_location1_no.NEXTVAL, #{name})"
	})
	public int locationInsert (Location l);
	
	@Update({
		"UPDATE location0 SET name = #{name} WHERE no = #{no}"
	})
	public int locationUpdate (Location l);
	
	@Select({
		"SELECT * FROM location0 "
	})
	public Object[] locationSelect ();
	
	@Delete({
		"DELETE FROM location1 WHERE no = #{no}"
	})
	public int locationDelete (Location l);
	
	
	
	
	
}
