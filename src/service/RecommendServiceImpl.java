package service;

import java.util.List;

import dto.Recommend;

public class RecommendServiceImpl implements RecommendService {

	@Override
	public int insertRecommend(Recommend r) {
		try {
			return rMapper.insertRecommend(r);
			
		}
		catch(Exception e) {
			e.printStackTrace();
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
			return null;
		}
	}

}
