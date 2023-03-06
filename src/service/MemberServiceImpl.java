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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePWMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int unMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}
}
