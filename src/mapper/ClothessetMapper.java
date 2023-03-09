package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import dto.Clothesset;


public interface ClothessetMapper {

	@Select({ " SELECT * FROM clothesset WHERE clno = #{clno}" })
	public List<Clothesset> selectClothessetOne(int clno);
	
	
	@Select({ " SELECT * FROM clothesset " })
	public List<Clothesset> selectClothesset();
	
	
}
