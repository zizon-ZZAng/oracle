package service;

import connection.MyBatisContext;
import dto.Member2;
import mapper.Member2Mapper;

public interface MemberService {
	final Member2Mapper mapper
		= MyBatisContext.getSqlSession().getMapper(Member2Mapper.class);
	
	// 회원가입
	public int signUpMember(Member2 member);
	
	// 로그인
	public Member2 loginMember(Member2 member);
	
	// 회원정보 수정
	public int updateMember(Member2 member);
	
	// 비밀번호 변경
	public int updatePWMember(Member2 member);
	
	// 회원탈퇴
	public int unMember(Member2 member);
}
