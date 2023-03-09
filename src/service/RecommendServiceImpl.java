package service;

import java.util.List;

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

	@Override
	public Recommend selectRecommendSetno(String id_num, int code_num) {
		try {
			return rMapper.selectRecommendSetno(id_num, code_num);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

}
