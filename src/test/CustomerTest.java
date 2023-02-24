package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Customer;
import mapper.CustomerMapper;

class CustomerTest {

	CustomerMapper cMapper 
	= MyBatisContext.getSqlSession().getMapper(CustomerMapper.class);
	
	// 암호와 아이디를 전달하면 64자의 암호를 변환하는 메소드
	public String hashPW (String pw, String id) {
		try {
			// 1. Hash알고리즘 SHA-256, 단방향 aa => ssdnlsd3sdmdff4gergr5gfd48
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			// ex)A라는 1234 암호 + salt 사용자 아이디
			// ex)B라는 1234 암호 + salt 사용자 아이디
			md.update(( pw + id ).getBytes());
			byte[] pwdSalt = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : pwdSalt) {
				sb.append(String.format("%02x", b));
			}
			
			String result = sb.toString();
			System.out.println(result);
			return result;
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			}
		}

//	@Test
//	void insertCustomer() {
//		String email = "sss2@naver.com";
//		String password = "sss2";
//		String hash = this.hashPW("sss2", "sss2@naver.com");
//		
//		Customer obj = new Customer();
//		obj.setEmail("sss2@naver.com");
//		obj.setPassword(hash);
//		obj.setPhone("010-0000-2223");
//		obj.setAddress("서울");
//		obj.setChk(1);
//		
//		int ret = cMapper.insertCustomer(obj);
//		System.out.println(ret);
//		
//	}
	
//	@Test
//	void selectCustomerLogin() {
//		String email = "bbb@naver.com";
//		String password = "bbbb";
//		String hash = this.hashPW("bbbb", "bbb@naver.com");
//		
//		Customer obj = new Customer();
//		obj.setEmail("bbb@naver.com");
//		obj.setPassword(hash);
//		
//		System.out.println(cMapper.selectCustomerLogin(obj));
//		
//	}
//	@Test
//	void updateCustomer() {
//		String email = "sss2@naver.com";
//		String password = "sss2";
//		String hash = this.hashPW("sss2", "sss2@naver.com");
//		
//		Customer obj = new Customer();
//		obj.setEmail("sss2@naver.com");
//		obj.setPassword(hash);
//		obj.setAddress("대구");
//		obj.setPhone("010-0000-8888");
//		
//		int ret = cMapper.updateCustomer(obj);
//		System.out.println(ret);
//	
//	}
	
	@Test
	void updateCustomerPw() {
		String email = "sss2@naver.com";
		String password = "sss2";
		String hash = this.hashPW("sss2", "sss2@naver.com");
		String hash1 = this.hashPW("xxxxx", "sss2@naver.com");
		
		Customer obj = new Customer();
		obj.setEmail("sss2@naver.com");
		obj.setPassword(hash);
		obj.setNewpassword(hash1);
		
		int ret = cMapper.updateCustomerPw(obj);
		System.out.println(ret);
		
		
	
	}
}

