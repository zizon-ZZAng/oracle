package service;

import java.util.List;
import java.util.Map;

import connection.MyBatisContext;
import dto.Recommend;

public class RecommendServiceImpl implements RecommendService {

	@Override
	public int insertRecommend(Recommend r) {
		try {
			return rMapper.insertRecommend(r);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public List<Recommend> selectRecommendId(String id) {
		try {
			return rMapper.selectRecommendId();
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	@Override
	public List<Recommend> selectRecommendNo(int no) {
		try {
			return rMapper.selectRecommendNo(no);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	@Override
	public List<Recommend> selectRecommendCode(int code) {
		try {
			return rMapper.selectRecommendCode(code);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

//	// 상의추천
//		@Override
//		public List<Integer> selectClothesTop(Map<String, Object> map) {
//			try {
//				return rMapper.selectClothesTop(map);
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//				MyBatisContext.getSqlSession().close();
//				return null;
//			}
//		}
	
	
	
	
	// 상의추천
	@Override
	public List<Map<String,Object>> selectClothesTop(Map<String, Object> map) {
		try {
			return rMapper.selectClothesTop(map);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

//	// 하의추천
//	@Override
//	public int selectClothesBottom(Map<String, Object> map) {
//		try {
//			return rMapper.selectClothesBottom(map);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			MyBatisContext.getSqlSession().close();
//			return 0;
//		}
//	}
//
//	// 신발추천
//	@Override
//	public int selectClothesShoes(Map<String, Object> map) {
//		try {
//			return rMapper.selectClothesShoes(map);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			MyBatisContext.getSqlSession().close();
//			return 0;
//		}
//	}

}
