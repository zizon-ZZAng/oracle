package test1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
<<<<<<<< Updated upstream:src/test/ClothesMapperTest.java
import dto.Clothes;
import mapper.ClothesMapper;
========
import dto1.Clothes;
import mapper1.ClothesMapper;
>>>>>>>> Stashed changes:1조/test1/ClothesMapperTest.java

public class ClothesMapperTest {

	ClothesMapper mapper = MyBatisContext.getSqlSession().getMapper(ClothesMapper.class);
	Clothes c = new Clothes();
	
	
	@Test
	void insertClothes() {
		
		c.setClname("옷이름");
		c.setTexture("옷텍스처");
		c.setThickness("옷두께");

		System.out.println(mapper.insertClothes(c));
	}
	
	
	@Test
	void UpdateClothesOne() {
		
		c.setClname("옷이름");
		c.setTexture("옷텍스처");
		c.setThickness("옷두께");

		System.out.println(mapper.UpdateClothesOne(c));
	}
	
	
	@Test
	void deleteClothes() {
		c.setClno(0);

		System.out.println(mapper.deleteClothes(c));
	}
	
	
	@Test
	void selectClothesList() {
		
		List<Clothes> list = mapper.selectClothesList();
		
		for(Clothes m : list) {
			System.out.println(m.toString());
		}
	}
	
	
	@Test
	void clothesLikeList() {
		Map<String, String> map = new HashMap<>();
		
		map.put("column", "clname");
		map.put("txt", "hoody");
		
		List<Clothes> list = mapper.clothesLikeList(map);
		
		for(Clothes m : list) {
			System.out.println(m.toString());
		}
	}
	
}

