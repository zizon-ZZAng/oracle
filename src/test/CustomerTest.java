package test;

import java.security.MessageDigest;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Customer;
import mapper.CustomerMapper;

@Mapper
class CustomerTest {
	
	/* a789@gmail.com - 1234, c123@gmail.com - 7878, a456@naver.com - 0012, a123@naver.com - 1199
	 b123@gmail.com - 1235 */

	CustomerMapper mapper = MyBatisContext.getSqlSession().getMapper(CustomerMapper.class);
	
	//암호와 아이디를 전달하면 64자의 암호를 변환하는 메소드
	public String hashPW(String pw, String id) {
		try {
			// 1. Hash알고리즘 SHA-256, 단방향 aa가 3asdf3sd32f13d2f1as5d6f4 이렇게 될 수 있는데 거꾸로는 안됨
			// 암호를 잊어버림. 임시암호를 주잖아 이게 단방향임
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// ex)1234암호 + salt 사용자 ID 를 넣어서 복잡하게 함
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

	//회원가입할 때랑 로그인할때 쓰임
	@Test
	void insertCutomer() {
		
		String hash = this.hashPW("1598", "c123@gmail.com");
		
		Customer customer = new Customer();
		
		customer.setEmail("c123@gmail.com");
		customer.setPassword(hash);
		customer.setAddress("부산 북구");
		customer.setPhone("010-0002-0004");
		
		int ret = mapper.insertCutomer(customer);
		System.out.println(ret);
		

	}
	
	//로그인
	@Test
	void customerLogin() {
		
	
		Customer customer = new Customer();
		
		customer.setEmail("c123@gmail.com");
		customer.setPassword("e6d9afea181df6718efe5ea31f45107dc0208c1b1264d1be2992594e355210be");
		
		System.out.println(mapper.customerLogin(customer));
		
		
	}
	
	//회원정보 수정
	@Test
	void customerUpdate() {
		Customer customer = new Customer();
		
		customer.setEmail("c123@gmail.com");
		customer.setPassword("e6d9afea181df6718efe5ea31f45107dc0208c1b1264d1be2992594e355210be");
		
		customer.setAddress("부산 진구");
		customer.setPhone("010-0000-0005");
		customer.setChk(1);
		
		System.out.println(mapper.customerUpdate(customer));
	}
	
	
	//암호 변경
	@Test
	void customerChange() {
		

		String hash1 = this.hashPW("0105", "b123@gmail.com");
		String hash2 = this.hashPW("1235", "b123@gmail.com");
		
		
		Customer customer = new Customer();
		customer.setPassword(hash1);
		customer.setEmail("b123@gmail.com");
		
		customer.setNewPassword(hash2);
		
		int ret = mapper.customerChange(customer);
		System.out.println(ret);
	
		
		
	}
	
	

}
