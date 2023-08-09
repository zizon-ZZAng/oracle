package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Customer;
import mapper.CustomerMapper;

class CustomerMapperTest {
	CustomerMapper cMapper 
		= MyBatisContext.getSqlSession().getMapper(CustomerMapper.class);
	
	public String hashPW(String pw, String id) {
		try {
			// 1. Hash 알고리즘 SHA-256, 단방향 aa => dlkfjafkajl
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			// ex) 1234(암호) + salt(고유한 값, userid)
			md.update((pw + id).getBytes());
			
			// byte to String 으로 변경
			byte[] pwdSalt = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdSalt) {
				sb.append(String.format("%02x", b));
			}
			
			String result = sb.toString();
			
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 고객 회원가입
	@Test
	void insertCustomer() {
//		String hash = this.hashPW("암호", "가입할 아이디");
		String hash = this.hashPW("bbb", "bbb@bbb.com");
		
		Customer c = new Customer();
		c.setEmail("bbb@bbb.com");
		c.setPassword(hash);
		c.setPhone("010-0000-0002");
		c.setAddress("주소2");
		c.setChk(1);
		
		System.out.println(cMapper.insertCustomer(c));
	}
	
	// 고객 로그인
	@Test
	void loginCustomer() {
		String hash = this.hashPW("bbb", "bbb@bbb.com");
		
		Customer c = cMapper.loginCustomer("bbb@bbb.com", hash);
		System.out.println(c.toString());
	}
//	void loginCustomer() {
//		String hash = this.hashPW("aaa", "aaa@aaa.com");
//		
//		Customer c = cMapper.loginCustomer("aaa@aaa.com", hash);
//		System.out.println(c.toString());
//	}
	
	// 고객 회원정보수정
	@Test
	void updateCustomer() {
		String hash = this.hashPW("bbb", "bbb@bbb.com");
		
		Customer c = new Customer();
		c.setPhone("010-0002-0002");
		c.setAddress("주소 변경");
		
		c.setEmail("bbb@bbb.com");
		c.setPassword(hash);
		
		System.out.println(cMapper.updateCustomer(c));
	}
	
	// 고객 암호 변경
	@Test
	void updateCustomerPW() {
		String oldHash = this.hashPW("bbb", "bbb@bbb.com"); // 원래 암호
		String newHash = this.hashPW("b", "bbb@bbb.com");   // 바꿀 암호
		
		Customer c = new Customer();
		c.setNewPassword(newHash); // 새로운 암호
		
		c.setEmail("bbb@bbb.com");
		c.setPassword(oldHash);	   // 원래 암호
		
		System.out.println(cMapper.updateCustomerPW(c));
	}
}
