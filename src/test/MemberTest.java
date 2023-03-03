package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberTest {

	
	MemberMapper mapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	
	public String hashPW(String pw, String id) {
		try {
		
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
	
	
	//회원가입
	@Test
	void singUpMember() {
		
		Member member = new Member();
		
		
		
		
		int ret = mapper.signUpMember(member);
		System.out.println(ret);
	}
	
	
	//비밀번호 변경
	@Test
	void updatePWMember() {
		
		
	}

}
