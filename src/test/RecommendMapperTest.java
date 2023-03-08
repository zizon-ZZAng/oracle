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
		
		for(int i=41; i<=50; i++) {
			rMapper.deleteRecommend(i);
		}
	}
	
	
	// 추천 목록 삽입
	@Test
	void insertRecommend() {
		
		for(int i=26; i<=31; i++) {
			Recommend r = new Recommend();
			Recommend r2 = new Recommend();
			Recommend r3 = new Recommend();
			Recommend r4 = new Recommend();
			Recommend r5 = new Recommend();
			Recommend r6 = new Recommend();
			Recommend r7 = new Recommend();
			Recommend r8 = new Recommend();
			Recommend r9 = new Recommend();
			Recommend r10 = new Recommend();
			Recommend r11 = new Recommend();
			
			r.setId("tak");
			r.setCode(i);
			
			r.setSetno(3);
			r2.setSetno(3);
			r3.setSetno(3);
			r4.setSetno(3);
			r5.setSetno(3);
			r6.setSetno(3);
			r7.setSetno(3);
			r8.setSetno(3);
			r9.setSetno(3);
			
			
			r2.setId("yoon");
			r2.setCode(i);
		
			r3.setId("yeo");
			r3.setCode(i);
				
			r4.setId("lee");
			r4.setCode(i);
					
			r5.setId("shin");
			r5.setCode(i);
					
			r6.setId("jeon");
			r6.setCode(i);		
			
			r7.setId("kim");
			r7.setCode(i);
					
			r8.setId("ddd");
			r8.setCode(i);	
			
			r9.setId("v");
			r9.setCode(i);
			
			r10.setId("ha");
			r10.setCode(i);
			
			r11.setId("zizon");
			r11.setCode(i);
			
			rMapper.insertRecommend(r);
			rMapper.insertRecommend(r2);
			rMapper.insertRecommend(r3);
			rMapper.insertRecommend(r4);
			rMapper.insertRecommend(r5);
			rMapper.insertRecommend(r6);
			rMapper.insertRecommend(r7);
			rMapper.insertRecommend(r8);
			rMapper.insertRecommend(r9);
			rMapper.insertRecommend(r10);
			System.out.println(rMapper.insertRecommend(r11));
			
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
