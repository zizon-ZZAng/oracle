package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Clothesset;
import mapper.ClothessetMapper;

public interface ClothessetService{

	final ClothessetMapper mapper 
	= MyBatisContext.getSqlSession().getMapper(ClothessetMapper.class);
	
	// 번호를 받으면 옷 세트 출력
	public List<Clothesset> selectClothessetOne(int num);
	
	// 옷 세트 출력
	public List<Clothesset> selectClothesset();
}
