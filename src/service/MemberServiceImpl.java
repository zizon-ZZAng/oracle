package service;

import java.security.MessageDigest;

import dto.Member;

public class MemberServiceImpl implements MemberService{

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
	
	@Override
	public int insertMember(Member m) {
		try {
			String hash = this.hashPW(m.getPassword(), m.getId()); //(비번,아이디)
			
			
			Member member = new Member();
			member.getId().toString();
			member.getPassword().toString();
			member.getName().toString();
			member.getSex().toString();
			member.setChk(1);
			
			m.setChk(1);
			
			return mMapper.insertMember(m);
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int loginMember(String id, String password) {
		try {
			String hash = this.hashPW(password, id); //(비번,아이디)

			return mMapper.loginMember(id, hash);
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int member1UpdateOne(Member obj) {
		try {
			return mMapper.member1UpdateOne(obj);
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateMemberPW(Member m) {
		try {
			return mMapper.updateMemberPW(m);
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	

}
