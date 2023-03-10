package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Recommend;
import mapper.RecommendMapper;

class RecommendMapperTest {
	RecommendMapper rMapper = MyBatisContext.getSqlSession().getMapper(RecommendMapper.class);

	// 상의 추천
	@Test
	void selectClothesTop() {
		Map<String, Object> map = new HashMap<>();

		map.put("id", "id2");
		//map.put("address", "서울");
		map.put("week", "2023/03/15");
		map.put("hour", "09");
		//map.put("column_rank", 1);
		//map.put("column_cltype", "하의");

		List<Map<String,Object>> list = rMapper.selectClothesTop(map); 
		
		for(Map<String,Object> map2 : list) {
			System.out.println(map2);
		}
	}

//	@Test
//	void selectClothesBottom() {
//		Map<String, Object> map = new HashMap<>();
//
//		map.put("id", "id1");
//		map.put("address", "서울");
//		map.put("week", "2023/03/16");
//		map.put("hour", "12");
//		map.put("rank", 1);
//		map.put("cltype", "하의");
//
//		System.out.println(rMapper.selectClothesBottom(map));
//	}

//	@Test
//	void selectClothesShoes() {
//		Map<String, Object> map = new HashMap<>();
//
//		map.put("id", "id1");
//		map.put("address", "서울");
//		map.put("week", "2023/03/16");
//		map.put("hour", "12");
//		map.put("rank", 1);
//		map.put("cltype", "신발");
//
//		System.out.println(rMapper.selectClothesShoes(map));
//	}

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

}
