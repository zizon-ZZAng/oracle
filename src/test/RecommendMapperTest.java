package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Recommend;
import mapper.RecommendMapper;

class RecommendMapperTest {
	RecommendMapper rMapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);

	@Test
	void deleteRecommend() {

		for (int i = 41; i <= 50; i++) {
			rMapper.deleteRecommend(i);
		}
	}

	// 추천 목록 삽입
	@Test
	void insertRecommend() {

		for (int i = 153; i <= 153; i++) {
			Recommend r = new Recommend();

			r.setId("lee");
			r.setCode(i);
			r.setSetno(7);

			System.out.println(rMapper.insertRecommend(r));

		}
	}

	// id별 추천목록 보기
	@Test
	void selectRecommendId() {

		List<Recommend> list = rMapper.selectRecommendId();

		for (Recommend r : list) {
			System.out.println(r.toString());
		}
	}

	// 옷별 추천목록 보기
	@Test
	void selectRecommendNo() {

		List<Recommend> list = rMapper.selectRecommendNo(3);

		for (Recommend r : list) {
			System.out.println(r.toString());
		}
	}

	// 날씨별 추천목록 보기
	@Test
	void selectRecommendCode() {

		List<Recommend> list = rMapper.selectRecommendCode(5);

		for (Recommend r : list) {
			System.out.println(r.toString());
		}
	}

	// 날씨별 추천목록 보기
	@Test
	void selectRecommendSetno() {
		
		int r = rMapper.selectRecommendSetno(45.f);
		
		System.out.println(r);

	}

}
