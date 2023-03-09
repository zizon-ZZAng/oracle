package service;

import java.util.List;

import connection.MyBatisContext;
import dto.Clothes;

public class ClothesServiceImpl implements ClothesService {
	// 의류 데이터 입력
	@Override
	public int insertClothes(Clothes clothes) {
		try {
			return mapper.insertClothes(clothes);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}
	
	// 의류 전체 조회
	@Override
	public List<Clothes> selectClothesAll() {
		try {
			return mapper.selectClothesAll();
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 의류 1개 조회
	@Override
	public Clothes selectClothesOne(long clono) {
		try {
			return mapper.selectClothesOne(clono);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 의류 정보 수정
	@Override
	public int updateClothes(Clothes obj) {
		try {
			return mapper.updateClothes(obj);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

	// 의류 삭제
	@Override
	public int deleteClothes(long clono) {
		try {
			return mapper.deleteClothes(clono);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

}
