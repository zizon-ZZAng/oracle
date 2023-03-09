package test;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Clothes;
import dto.ClothesCate;
import mapper.ClothesCateMapper;

public class ClothesCateMapperTest {
	
	ClothesCateMapper mapper = MyBatisContext.getSqlSession().getMapper(ClothesCateMapper.class);
	ClothesCate c = new ClothesCate();

	@Test
	void insertClothesCate() {
	
		c.setName("옷분류 이름");
	
		System.out.println(mapper.insertClothesCate(c));
	}
	
	
	@Test
	void updateClothesCate() {
		
		c.setName("옷분류 이름");

		System.out.println(mapper.updateClothesCate(c));
	}
	
	
	@Test
	void deleteClothesCate() {

		System.out.println(mapper.deleteClothesCate(c));
	}
	
	
	@Test
	void selectClothesCateList() {
		
		List<ClothesCate> list = mapper.selectClothesCateList();
		
		for(ClothesCate m : list) {
			System.out.println(m.toString());
		}
	}
	

}