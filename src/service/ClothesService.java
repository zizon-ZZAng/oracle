package service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.Clothes;
import mapper.ClothesMapper;

public interface ClothesService {

	final ClothesMapper mapper 
		= MyBatisContext.getSqlSession().getMapper(ClothesMapper.class);
	
	
	// 옷 등록
	public int insertClothes(Clothes c);
	
	// 옷 수정
	public int UpdateClothesOne(Clothes obj);
	
	// 옷 삭제
	public int deleteClothes(Clothes obj);
	
	// 옷 조회
	public List<Clothes> selectClothesList();
	
	// 문자를 포함한 옷 검색
	public List<Clothes> clothesLikeList(Map<String, String> map);
	
	
}
