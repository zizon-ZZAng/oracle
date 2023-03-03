package test;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
	public boolean singUpMember() {
		
		Member member = new Member();
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		int flag = 0;
		
		try {
			
			conn = new MyBatisContext().getConn();
			
			String sql = " INSERT INTO member2(id, name, gender, password, address) " +
					 " VALUES(?,?,?,?,?) ";
			
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, member.getId());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getName());
			ps.setString(4, member.getAddress());
			ps.setString(5, member.getGender());
			
			//sql문 실행하기 (INSERT, UPDATE, DELETE)
			flag = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				
			}
		}
		
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	
		
//		String id="";
//		String pw="";
//		
//		String hash = this.hashPW(pw, id);
//		
//		Member member = new Member();
//		
//		member.setId(id);
//		member.setName("");
//		member.setPassword(hash);
//		member.setGender("");
//		member.setAddress("");
//		
//		int ret = mapper.signUpMember(member);
//		System.out.println(ret);
	}
	
	
	//로그인
	@Test
	void loginMember() {
		Member member = new Member();
		member.setId("");
		member.setPassword("");
		
		System.out.println(member.getName());
		
		
	}
	
	
	
	//회원정보 수정
	@Test
	void updateMember() {
		Member member = new Member();
		
		member.setName("");
		member.setGender("");
		member.setAddress("");
		
		member.setId("");
		member.setPassword("");
		
		int ret = mapper.updateMember(member);
		System.out.println(ret);	
		
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
	
	//회원탈퇴
	@Test
	void unMember() {
		Member member = new Member();
		
		member.setId("");
		member.setPassword("");
		
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
