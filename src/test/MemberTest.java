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
		
		String id="f";
		String pw="f";
		
		String hash = this.hashPW(pw, id);
		
		Member member = new Member();
		
		member.setId(id);
		member.setName("박신나");
		member.setPassword(hash);
		member.setGender("F");
		member.setAddress("광주");
		
		
		int ret = mapper.signUpMember(member);
		System.out.println(ret);
		
//		Connection conn = null;
//		PreparedStatement ps = null;
		
//		int flag = 0;
//		
//		try {
//			
//			conn = new MyBatisContext().getConn();
//			
//			String sql = " INSERT INTO member2(id, name, gender, password, address) " +
//					 " VALUES(?,?,?,?,?) ";
//			
//			ps = conn.prepareStatement(sql);
//			
//			
//			ps.setString(1, member.getId());
//			ps.setString(2, member.getPassword());
//			ps.setString(3, member.getName());
//			ps.setString(4, member.getAddress());
//			ps.setString(5, member.getGender());
//		
//			//sql문 실행하기 (INSERT, UPDATE, DELETE)
//			flag = ps.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(ps != null)
//					ps.close();
//				if(conn != null)
//					conn.close();
//			} catch (Exception e2) {
//				
//			}
//		}
//		
//		if(flag>0) {
//			return true;
//		}else {
//			return false;
//		}
	
		
	}
	
	
	

	//로그인
	@Test
	void loginMember() { 
		
		String id="a";
		String pw="bb";
		
		String hash = this.hashPW(pw, id);
		
		
		
		Member member =new Member();
		member.setId(id);
		member.setPassword(hash);
		
		
		System.out.println(mapper.loginMember(member));
		
		
		
		//System.out.println(mapper.loginMember("a", "bb"));
	
	
	}
	
	
	//회원정보 수정
	@Test
	void updateMember() {
		
		
		String id = "a";
		String pw = "bb";
				
		String hash = this.hashPW(pw, id);
		
		
		Member member = new Member();
		

		member.setId("a");
		member.setPassword(hash);
		
		
		member.setName("김철수");
		member.setGender(null);
		member.setAddress(null);
		
		System.out.println(mapper.updateMember(member));
		
	}
	
	
	//비밀번호 변경
	@Test
	void updatePWMember() {
		String id="a";
		String pw="bb";
		
		String hash = this.hashPW(pw, id);
		
		
		String newpw="a";
		
		String newhash = this.hashPW(newpw, id);
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(hash);
		
		member.setNewpw(newhash);
		
		int ret =mapper.updatePWMember(member);
		System.out.println(ret);
		
	}
	
	//회원탈퇴
	@Test
	void unMember() {	//개빡치네///////////////////////////////////
		
		String id="a";
		String pw="bb";
		
		String hash = this.hashPW(pw, id);
		
		
		Member member = new Member();
		
		member.setId(id);
		member.setPassword(hash);
		
		member.setName("");
		member.setAddress("");
		member.setGender("");
		member.setPassword("");
		member.setRegdate(null);
		member.setChk(0);
		
		int ret = mapper.unMember(member);
		System.out.println(ret);
		
		
	}
	

}
