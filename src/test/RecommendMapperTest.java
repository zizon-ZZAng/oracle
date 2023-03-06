package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Recommend;
import mapper.RecommendMapper;

class RecommendMapperTest {
	RecommendMapper rMapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);
	Recommend r = new Recommend();

	// 추천 목록 삽입
	@Test
	void insertRecommend() {
		r.setId("aaa1");
		r.setClno(10011);
		r.setCode(6);

		System.out.println(rMapper.insertRecommend(r));
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

}
