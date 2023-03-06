package service;

import dto.Member;

public class MemberServiceImpl implements MemberService {
	
	
	//회원가입
	@Override
	public int signUpMember(Member member) {
		try {
			return mapper.signUpMember(member);
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	//로그인
	@Override
	public Member loginMember(Member member) {
		try {
			
			return mapper.loginMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//회원정보변경
	@Override
	public int updateMember(Member member) {
		try {
			return mapper.updateMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	//비밀번호변경
	@Override
	public int updatePWMember(Member member) {
		try {
			
			return mapper.updatePWMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	//회원탈퇴
	@Override
	public int unMember(Member member) {
		try {
			
			return mapper.unMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
