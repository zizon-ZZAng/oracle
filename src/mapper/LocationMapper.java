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
<<<<<<< HEAD
		"INSERT INTO location0 (no, name) VALUES (seq_location1_no.NEXTVAL, #{name})"
=======
		"INSERT INTO location0 (name) VALUES (#{name})"
>>>>>>> 236144ea53d7932aabc578522091155555198be0
	})
	public int locationInsert (Location l);
	
	@Update({
<<<<<<< HEAD
		"UPDATE location0 SET name = #{name} WHERE no = #{no}"
=======
		"UPDATE location0 SET name = #{name} WHERE name = #{name}"
>>>>>>> 236144ea53d7932aabc578522091155555198be0
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
