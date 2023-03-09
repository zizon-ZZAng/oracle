package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Clothes;
import dto.ClothesCate;
import dto.Customer;
import dto.Member;

@Mapper
public interface ClothesMapper {
	
	@Insert({
		" INSERT INTO clothes0(clno, clname, texture, thickness, name) ",
	    " VALUES(#{clno}, #{clname}, #{texture}, #{thickness},#{name}) "	
	})
	public int insertClothes(Clothes c);
	
	
	@Update({
		" <script> ",
		" UPDATE clothes0 SET clname=#{obj.clname} ",
			" <if test='obj.texture != null'> ",
				", texture=#{obj.texture} ",
			" </if> ",
			
			" <if test='obj.thickness != null'> ",
				", thickness=#{obj.thickness} ",
			" </if> ",
			
		" WHERE clno=#{obj.clno} ",
		" </script> "
	})
	public int UpdateClothesOne(@Param("obj") Clothes obj);

	
	@Delete({
		" DELET FROM clothes0 ",
		" WHERE clno=#{obj.clno} "
	})
	public int deleteClothes(@Param("obj") Clothes obj);
	
	
	@Select({
		" SELECT * FROM clothes0 "
	})
	public List<Clothes> selectClothesList();
	
	
	// 문자를 포함한 옷 출력
	@Select({
		" SELECT c.* FROM clothes0 c WHERE ${map.column} ",
		" LIKE '%' || #{map.txt} || '%' "
	})
	public List<Clothes> clothesLikeList( @Param("map") Map<String, String> map);
}
