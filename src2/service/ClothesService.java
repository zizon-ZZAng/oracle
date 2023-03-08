package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.Clothes;
import mapper.ClothesMapper;

public interface ClothesService {
	final ClothesMapper mapper 
		= MyBatisContext.getSqlSession().getMapper(ClothesMapper.class);
	
	// 의류 데이터 입력
	public int insertClothes(Clothes clothes);

	// 의류 전체 조회
	public List<Clothes> selectClothesAll();
	
	// 의류 1개 조회
	public Clothes selectClothesOne(@Param("clono") long clono);
	
	// 의류 정보 수정
	public int updateClothes(@Param("obj") Clothes obj);
	
	// 의류 삭제
	public int deleteClothes(@Param("clono") long clono);
}
