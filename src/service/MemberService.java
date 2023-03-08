package service;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

public interface MemberService {
	final MemberMapper mapper
		= MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	// 회원가입
	public int signUpMember(Member member);
	
	// 로그인
	public Member loginMember(Member member);
	
	// 회원정보 수정
	public int updateMember(Member member);
	
	// 비밀번호 변경
	public int updatePWMember(Member member);
	
	// 회원탈퇴
	public int unMember(Member member);
	
	// 회원 1명 조회(회원가입시 아이디 중복 확인용)
//	public Member selectMemberOne(String id);
	
	
	//아이디 중복 체크 확인용 다른버전임
	public Member selectMemberIdChk(String id);
}
