package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Rider;
import mapper.RiderMapper;



class RiderTest {
	
	
	RiderMapper mapper = MyBatisContext.getSqlSession().getMapper(RiderMapper.class);
	
	public String hashPW(String pw, String id) {
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");

		
			md.update((pw + id).getBytes());

			// byte to String으로 변경
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

	
	//배달원3 - 3333 배달원4 - 4444
	@Test
	void insertRider() {
		
		String name = "배달원5";
		String pw = "5555";
		
		String hash = this.hashPW(pw, name);
		
		Rider rider = new Rider();
		
		rider.setName(name);
		rider.setPhone("010-5555");
		rider.setPassword(hash);
		
		int ret = mapper.insertRider(rider);
		System.out.println(ret);
		
		
	}
	
	
	// 배달원 1명 조회
	@Test
	void selectRider() {
		Rider rider = new Rider();
		rider.setPhone("010-1111");
		
		System.out.println(mapper.selectRider(rider));
		
	}
	

}
