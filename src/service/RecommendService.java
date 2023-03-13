package service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.Recommend;
import mapper.RecommendMapper;

public interface RecommendService {
	final RecommendMapper rMapper
		=MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);
	
	
	// 상의 추천
	public List<Map<String,Object>> selectClothes(Map<String, Object> map);
		
	//추천 목록 삽입
	public int insertRecommend(Recommend r);
	
	//특정 id 추천목록 보기
	public List<Recommend> selectRecommendId(String id);
	
	//특정 옷 추천목록 보기
	public List<Recommend> selectRecommendNo(int no);
	
	//특정 날씨 추천목록 보기
	public List<Recommend> selectRecommendCode(int code);
	
}
