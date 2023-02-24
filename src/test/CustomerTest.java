package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Customer;
import mapper.CustomerMapper;

class CustomerTest {

	// 테스트 mapper객체 생성
	CustomerMapper mapper = MyBatisContext.getSqlSession().getMapper(CustomerMapper.class);

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
	void insertCustomer() {
		String hash = this.hashPW("abcde", "javaid");
		Customer c = new Customer();

		c.setPassword(hash);
		c.setAddress("자바주소입니다");
		c.setChk(1L);
		c.setEmail("javaid");
		c.setPhone("010-0000-0002");

		System.out.println(mapper.insertCustomer(c));
	}

	@Test
	void selectLoginCustomer() {
		String hash = this.hashPW("abcde", "javaid");
		System.out.println(mapper.selectLoginCustomer("javaid",hash).toString());
	}
	
	@Test
	void updateCustomer() {
		String hash = this.hashPW("abcde", "javaid");
		Customer c = new Customer();

		c.setPassword(hash);
		c.setAddress("자바주소변경");
		c.setEmail("javaid");
		c.setPhone("011-0000-0002");

		System.out.println(mapper.updateCustomer(c));
	}
	
	// 비밀번호 변경
	@Test
	void updatePasswordCustomer() {
		
		// 입력받아야 할 값을 변수를 지정해 놓은 다음 넣어주는 게 좋음.
		String email = "javaid";
		String pw = "abcde";
		String newPw = "abcdef";
		
		String hash = this.hashPW(pw, email);
		String newHash = this.hashPW(newPw, email);
		
		Customer c = new Customer();

		c.setNewPassword(newHash);
		c.setPassword(hash);
		c.setEmail(email);

		System.out.println(mapper.updatePasswordCustomer(c));
	}

}
