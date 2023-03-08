package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Clothes;
import mapper.ClothesMapper;

class ClothesMapperTest {
	ClothesMapper cMapper = MyBatisContext.getSqlSession().getMapper(ClothesMapper.class);
	
	// 의류 데이터 입력
	@Test
	void insertClothes() {
		Clothes c = new Clothes();
		c.setName("SIGN LOGO HOOD NAVY");
		c.setThickness("두꺼움");
		c.setCatetype("상의");
		
		System.out.println(cMapper.insertClothes(c));
	}
	
	// 의류 전체 조회
	@Test
	void selectClothesAll() {
		List<Clothes> list = cMapper.selectClothesAll();
		for (Clothes clothes : list) {
			System.out.println(clothes.toString());
		}
	}
	
	// 의류 1개 조회
	@Test
	void selectClothesOne() {
		System.out.println(cMapper.selectClothesOne(1001L));
	}
	
	// 의류 정보 수정
	@Test
	void updateClothes() {
		Clothes c = new Clothes();
		c.setClono(1001L);
		c.setName("옷 이름 변경~~");
		c.setThickness("두꺼움");
		c.setCatetype(null);
		
		System.out.println(cMapper.updateClothes(c));
	}
	
	// 의류 삭제
	@Test
	void deleteClothes() {
		System.out.println(cMapper.deleteClothes(1001L));
	}	
}
