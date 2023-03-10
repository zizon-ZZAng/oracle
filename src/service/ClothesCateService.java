package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.ClothesCate;
import mapper.ClothesCateMapper;


public interface ClothesCateService {

	final ClothesCateMapper mapper 
		= MyBatisContext.getSqlSession().getMapper(ClothesCateMapper.class);
	
	// 의류 등록
	public int insertClothesCate(ClothesCate c);
	
	// 의류 업데이트
	public int updateClothesCate(ClothesCate c);
	
	// 의류 삭제
	public int deleteClothesCate(@Param("obj") ClothesCate obj);
	
	// 의류 조회
	public List<ClothesCate> selectClothesCateList();
	
	
}
