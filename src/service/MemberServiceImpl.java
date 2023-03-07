package service;

import dto.Member;

public class MemberServiceImpl implements MemberService{

	@Override
	public int insertMember(Member m) {
		try {
			return mMapper.insertMember(m);
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Member loginMember(String id, String password) {
		try {
			return mMapper.loginMember(id, password);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
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
