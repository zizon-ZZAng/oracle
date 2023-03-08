package service;

import java.security.MessageDigest;

import dto.Member;

public class MemberServiceImpl implements MemberService {
	// 비밀번호 암호화
	public String hashPW(String pw, String id) {
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update((pw + id).getBytes());

			byte[] pwdSalt = md.digest();

			StringBuffer sb = new StringBuffer();
			for (byte b : pwdSalt) {
				sb.append(String.format("%02x", b));
			}

			String result = sb.toString();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 회원가입
	@Override
	public int signUpMember(Member member) {
		try {
			return mapper.signUpMember(member);
		} catch (Exception e) {
			e.printStackTrace();
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
			return -1;
		}
	}
	
	// 회원 1명 조회(회원가입시 아이디 중복 확인용)
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
}
