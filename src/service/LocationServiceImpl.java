package service;

import dto.Location;

public class LocationServiceImpl implements LocationService{

	@Override
	public int locationInsert(Location l) {
		try {
			return mapper.locationInsert(l);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int locationUpdate(Location l) {
		try {
			return mapper.locationUpdate(l);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Location locationSelect(Location l) {
		try {
			return mapper.locationSelect(l);
		} catch (Exception e) {
			e.printStackTrace();
			return null;		
			}
	}

	@Override
	public int locationDelete(Location l) {
		try {
			return mapper.locationDelete(l);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
