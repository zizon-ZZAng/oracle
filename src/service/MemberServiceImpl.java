package service;

import dto.Member;

public class MemberServiceImpl implements MemberService {

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

	@Override
	public Member loginMember(Member member) {
		try {
			
			return mapper.loginMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateMember(Member member) {
		try {
			return mapper.updateMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updatePWMember(Member member) {
		try {
			
			return mapper.updatePWMember(member);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

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
