package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Rider;
import mapper.RiderMapper;

class RiderTest {

	// 테스트 mapper객체 생성
	RiderMapper mapper = MyBatisContext.getSqlSession().getMapper(RiderMapper.class);

	// 암호와 아이디를 전달하면 64자의 암호를 변환하는 메소드
	public String hashPW(String pw, String id) {
		try {
			// 1. Hash알고리즘 SHA-256, 단방향 aa => 3jif3j43kj34kjk34jk43jjk34
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((pw + id).getBytes());

			byte[] pwdSalt = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdSalt) {
				sb.append(String.format("%02x", b));
			}
			String result = sb.toString();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Test
	void insertRider() {
		Rider obj = new Rider();

		for (int i = 2; i < 10; i++) {
			obj.setName("rider" + i);
			String hash = this.hashPW("password", "rider" + i);
			obj.setPassword(hash);
			obj.setPhone("010-0001-000" + i);

			System.out.println(mapper.insertRider(obj));
		}
	}

	@Test
	void selectOneRider() {
		Rider obj = new Rider();

		obj.setPhone("010-0001-0000");

		System.out.println(mapper.selectOneRider(obj).toString());
	}
	
	

}
