package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import dto.Clothesset;


public interface ClothessetMapper {

	@Select({ " SELECT * FROM clothesset " })
	public List<Clothesset> clothessetSelect();
}
