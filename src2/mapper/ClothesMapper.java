package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Clothes;

@Mapper
public interface ClothesMapper {
	// 의류 데이터 입력
	@Insert({" INSERT INTO clothes2(clono, name, thickness, catetype) ",
			 " VALUES(seq_clothes2_clono.NEXTVAL, #{name}, #{thickness}, #{catetype}) "})
	public int insertClothes(Clothes clothes);
	
	// 의류 전체 조회
	@Select({" SELECT c.* FROM clothes2 c "})
	public List<Clothes> selectClothesAll();
	
	//의류 1개 조회
	@Select({" SELECT c.* FROM clothes2 c WHERE clono = #{clono}"})
	public Clothes selectClothesOne(@Param("clono") long clono);
	
	// 의류 정보 수정
	@Update({" <script> ",
			 " UPDATE clothes2 SET name = #{obj.name} ",
			 	 " <if test = 'obj.thickness != null'> " ,
			 	 		" , thickness = #{obj.thickness} ",
			 	 " </if> ",
			 	 
			 	 " <if test = 'obj.catetype != null'> " ,
	 	 				" , catetype = #{obj.catetype} ",
	 	 		 " </if> ",
	 	 	" WHERE clono = #{obj.clono} ",
	 	 	" </script> "})
	public int updateClothes(@Param("obj") Clothes obj);
	
	// 의류 삭제
	@Delete({" DELETE FROM clothes2 ",
		 	 " WHERE clono = #{clono} "})
	public int deleteClothes(@Param("clono") long clono);
}
