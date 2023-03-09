package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Clothes;
import dto.Clothesset;

public class ClothessetServiceImpl implements ClothessetService{

	@Override
	public List<Clothesset> selectClothesset(int num) {
		try {
			return mapper.selectClothesset();
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

}
