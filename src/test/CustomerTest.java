package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Customer;
import mapper.CustomerMapper;

class CustomerTest {
	
	CustomerMapper mapper = MyBatisContext.getSqlSession().getMapper(CustomerMapper.class);	
	
	// 암호와 아이디를 전달하면 64자의 암호를 변환하는 메소드
	public String hashPW(String pw, String id) {
		try {
			// 1.Hash알고리즘 SHA-256
			// 단방향. 감나무 => 728932ㅑ4ㅗ3ㅕㅐㅕㄱ43ㅑㅕ고ㅓ(64자리 암호)
			// 암호가 감나무로는 해석불가능
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// ex) 가 1234 암호 + salt 사용자아이디
			// ex) 나 1234 암호 + salt 사용자아이디
			// 아이디가 다 다르므로 비번이 중복으로 암호화되지 않는다
			md.update((pw + id).getBytes());

			// byte to string 으로 변경
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
	
	// 고객 회원가입
	@Test
	void insertCustomer() {
		String hash = this.hashPW("uu9u@ghj.com", "0000");
		Customer obj = new Customer();
		obj.setEmail("uu9u@ghj.com");
		obj.setPassword(hash);
		obj.setPhone("010-222-1111");
		obj.setAddress("서구");
		obj.setChk(1);
		
		int ret = mapper.insertCustomer(obj);
		System.out.println(ret);
	}
	
	// 고객 로그인
	@Test
	void selectCustomerLogin() {
		String hash = this.hashPW("uu9u@ghj.com", "0000");
		Customer obj = new Customer();
		obj.setEmail("uu9u@ghj.com");
		obj.setPassword(hash);

		Customer ret = mapper.selectCustomerLogin(obj);
		System.out.println(ret);
	}

	//고객 회원정보수정
	@Test
	void updateCustomer() {
		String hash = this.hashPW("uu9u@ghj.com", "0000");
	
		Customer obj = new Customer();
		obj.setPhone("010-1235-0000");
		obj.setAddress("부산진구");
		obj.setEmail("uu9u@ghj.com");
		obj.setPassword(hash);
		
		int ret = mapper.updateCustomer(obj);
		System.out.println(ret);
	}
	
	//고객 암호변경
	@Test
	void updateCustomerPw() {
		String hash1 = this.hashPW("uu9u@ghj.com", "0000");
		String hash2 = this.hashPW("uu9u@ghj.com", "1111");
		
		Customer obj = new Customer();
		obj.setEmail("uu9u@ghj.com");
		obj.setPassword(hash1);
		obj.setNewpassword(hash2);
		
		int ret = mapper.updateCustomerPw(obj);
		System.out.println(ret);
	}
	
}


