package service;

import java.security.MessageDigest;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
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
			member.setPassword(hash);
			
			return mMapper.insertMember(m);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public Member loginMember(Member m) {
		try {			
			return mMapper.loginMember(m);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	@Override
	public int memberUpdateOne(Member obj) {
		try {
			return mMapper.memberUpdateOne(obj);
		}
		catch(Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
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
			MyBatisContext.getSqlSession().close();
			return 0;
		}
	}

	@Override
	public Member selectMember(String id) {
		try {
			return mMapper.selectMember(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}
	
	

}
