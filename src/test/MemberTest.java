package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberTest {

	
	MemberMapper mapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	
	//비밀번호 암호화
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
		
		String id="";
		String pw="";
		
		String hash = this.hashPW(pw, id);
		
		Member member = new Member();
		
		member.setId(id);
		member.setName("");
		member.setPassword(hash);
		member.setGender("");
		member.setAddress("");
		
		int ret = mapper.signUpMember(member);
		System.out.println(ret);
	}
	
	//회원정보 수정
	@Test
	void updateMember() {
		
		
		
	}
	
	
	//비밀번호 변경
	@Test
	void updatePWMember() {
		String id="";
		String pw="";
		
		String newpw="";
		
		String hash = this.hashPW(newpw, id);
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(pw);
		
		member.setNewpw(hash);
		
	}

}
