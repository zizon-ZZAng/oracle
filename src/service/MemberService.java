package service;

import org.apache.ibatis.annotations.Param;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

public interface MemberService {
	final MemberMapper mMapper 
		= MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	//회원가입
	public int insertMember(Member m);
	
	//로그인
	public Member loginMember(Member m);
	
	// 고객 회원정보수정
	public int member1UpdateOne(@Param("obj") Member obj);
	
	//암호 변경
	public int updateMemberPW(Member m);
}
