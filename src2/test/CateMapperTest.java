package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Cate;
import mapper.CateMapper;

class CateMapperTest {
	CateMapper caMapper = MyBatisContext.getSqlSession().getMapper(CateMapper.class);
	
	// 의류 종류 조회
	@Test
	void selectCate() {
		List<Cate> list = caMapper.selectCate();
		for (Cate cate : list) {
			System.out.println(cate.toString());
		}
	}
	
	// 의류 종류 추가 (의류 종류명을 입력하면 추가)
	@Test
	void insertCate() {
		System.out.println(caMapper.insertCate("신애린"));
	}
	
	// 의류 종류 삭제 (의류 종류명을 입력하면 삭제)
	@Test
	void deleteCate() {
		System.out.println(caMapper.deleteCate("신애린"));
	}
}
