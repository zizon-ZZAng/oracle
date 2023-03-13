package service;

import connection.MyBatisContext;
import dto.Member;

public class MemberServiceImpl implements MemberService {
	// 회원가입
	@Override
	public int signUpMember(Member member) {
		try {
			return mapper.signUpMember(member);
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

	// 로그인
	@Override
	public Member loginMember(Member member) {
		try {
			return mapper.loginMember(member);

		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return null;
		}
	}

	// 회원정보 수정
	@Override
	public int updateMember(Member member) {
		try {
			member.setId(mapper.loginMember(member).getId());
			return mapper.updateMember(member);

		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}

	// 비밀번호변경
	@Override
	public int updatePWMember(Member member) {
		try {

			return mapper.updatePWMember(member);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	// 회원탈퇴
	@Override
	public int unMember(Member member) {
		try {

			return mapper.unMember(member);

		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
	}
	
	// 회원 1명 조회
	@Override
	public Member selectMemberOne(String id) {
		try {
			return mapper.selectMemberOne(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//아이디 중복 체크
	@Override
	public int selectMemberIdChk(String id) {
		try {
			
			return mapper.selectMemberIdChk(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			MyBatisContext.getSqlSession().close();
			return -1;
		}
		
		
	}
}
