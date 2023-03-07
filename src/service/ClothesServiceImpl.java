package service;

import java.util.List;
import java.util.Map;

import dto.Clothes;

public class ClothesServiceImpl implements ClothesService {

	@Override
	public int insertClothes(Clothes c) {
		try {
			
			return mapper.insertClothes(c);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int UpdateClothesOne(Clothes obj) {
		try {
			return mapper.UpdateClothesOne(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteClothes(Clothes obj) {
		try {
			return mapper.deleteClothes(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Clothes> selectClothesList() {
		try {
			return mapper.selectClothesList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Clothes> clothesLikeList(Map<String, String> map) {
		try {
			return mapper.clothesLikeList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
