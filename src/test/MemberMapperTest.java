package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberMapperTest {
	MemberMapper mMapper 
		= MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
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
	void insertMember() {
//		String hash = this.hashPW("암호", "가입할 아이디");
		String hash = this.hashPW("abcde", "hhh1");
		
		Member m = new Member();
		m.setId("hhh1");
		m.setName("이름");
		m.setPassword(hash);
		m.setSex("F");
		m.setAddress("회원가입주소");
		m.setChk(1);
		
		System.out.println(mMapper.insertMember(m));
	}
	
	// 고객 로그인
	@Test
	void loginMember() {
		String hash = this.hashPW("abcde", "hhh1");
		
		Member m = mMapper.loginMember("hhh1", hash);
		System.out.println(m.toString());
	}

	
	// 고객 회원정보수정
	
	@Test
	void updateMember() {
		String hash = this.hashPW("abcde", "hhh1");
		
		Member m = new Member();
		m.setName("이름");
		m.setSex("F");
		m.setAddress("주소 수정");
		m.setPassword(hash);
		
		System.out.println(mMapper.updateMember(m));
	}
	
	// 고객 암호 변경
	@Test
	void updateMemberPW() {
		String oldHash = this.hashPW("abcde", "hhh1"); // 원래 암호
		String newHash = this.hashPW("abcde2", "hhh1");   // 바꿀 암호
		
		Member m = new Member();
		m.setNewPassword(newHash); // 새로운 암호
		
		m.setId("hhh1");
		m.setPassword(oldHash);	   // 원래 암호
		
		System.out.println(mMapper.updateMemberPW(m));
	}
}
