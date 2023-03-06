package service;

import java.util.List;

import dto.ClothesCate;

public class ClothesCateServiceImpl implements ClothesCateService{

	@Override
	public int insertClothesCate(ClothesCate c) {
		try {
			return mapper.insertClothesCate(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateClothesCate(ClothesCate c) {
		try {
			return mapper.updateClothesCate(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteClothesCate(ClothesCate obj) {
		try {
			return mapper.deleteClothesCate(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<ClothesCate> selectClothesCateList() {
		try {
			return mapper.selectClothesCateList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
