package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member2;
import mapper.Member2Mapper;

class MemberTest {

<<<<<<< Updated upstream
=======
	Member2Mapper mMapper 
	= MyBatisContext.getSqlSession().getMapper(Member2Mapper.class);
>>>>>>> Stashed changes
	
	MemberMapper mapper = MyBatisContext.getSqlSession().getMapper(MemberMapper.class);
	
	
	//비밀번호 암호화
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
	
	
	//회원가입
	@Test
	void singUpMember() {
		
		String id="f";
		String pw="f";
		
		String hash = this.hashPW(pw, id);
		
		Member member = new Member();
		
		member.setId(id);
		member.setName("박신나");
		member.setPassword(hash);
		member.setGender("F");
		member.setAddress("광주");
		
		
		int ret = mapper.signUpMember(member);
		System.out.println(ret);	
	}

	//로그인
	@Test
<<<<<<< Updated upstream
	void loginMember() { 
=======
	void memberInsertBatch() {
		List<Member2> list = new ArrayList<Member2>();
		for(int i=0; i<3; i++) {
			Member2 member = new Member2();
			member.setUserid("aaa100"+i);
			member.setUserpw("암호");
			member.setUsername("이름");
			member.setUserage(23);
			member.setUserphone("010-0000-000"+i);
			member.setUsergender("M");
			list.add(member);
		}
		int ret = mMapper.memberInsertBatch(list);
		System.out.println(ret); // 숫자 3이 출력됨.
	}
	
	@Test
	void memberUpdateBatch() {
		List<Member2> list = new ArrayList<Member2>();
		for(int i=0; i<2; i++) {
			Member2 member = new Member2();
			member.setUserid("a9");
			member.setUsername("바뀐 이름");
			member.setUserage(45);
			list.add(member);
		}
			int ret = mMapper.memberUpdateBatch(list);
			System.out.println(ret);
	}
	
	@Test
	void memberUpsert() {
		Member2 m = new Member2();
		m.setUserid("e10");
		m.setUsername("도로롱");
		m.setUserpw("aaaa");
		m.setUserage(17);
		m.setUserphone("010-1004-1004");
		m.setUsergender("M");
>>>>>>> Stashed changes
		
		String id="a";
		String pw="bb";
		
		String hash = this.hashPW(pw, id);
		
		
		
		Member member =new Member();
		member.setId(id);
		member.setPassword(hash);
		
		
		System.out.println(mapper.loginMember(member));
		
	
	}
	
	
	//회원정보 수정
	@Test
<<<<<<< Updated upstream
	void updateMember() {
		
		
		String id = "a";
		String pw = "bb";
				
		String hash = this.hashPW(pw, id);
		
		
		Member member = new Member();
=======
	void memberUpdateOne() {
		Member2 member = new Member2();
		member.setUserid("s");
		member.setUsername("바닐라라떼");
		member.setUserage(20);
		member.setUserphone(null);
		member.setUsergender(null);
>>>>>>> Stashed changes
		

		member.setId("a");
		member.setPassword(hash);
		
		
		member.setName("김철수");
		member.setGender(null);
		member.setAddress(null);
		
		System.out.println(mapper.updateMember(member));
		
	}
	
	
	//비밀번호 변경
	@Test
	void updatePWMember() {
		String id="a";
		String pw="bb";
		
		String hash = this.hashPW(pw, id);
		
		
		String newpw="a";
		
		String newhash = this.hashPW(newpw, id);
		
		Member member = new Member();
		member.setId(id);
		member.setPassword(hash);
		
		member.setNewpw(newhash);
		
		int ret =mapper.updatePWMember(member);
		System.out.println(ret);
		
	}
	
	//회원탈퇴
	@Test
	void unMember() {
		
		String id="a";
		String pw="a";
		
		String hash = this.hashPW(pw, id);
		
		Member member = new Member();
		
		member.setId(id);
		member.setNewpw(hash);
		
		int ret = mapper.unMember(member);
		System.out.println(ret);
	}
	
<<<<<<< Updated upstream

=======
	@Test
	void memberLikeList() {
		Map<String, Object> map = new HashMap<>();
		map.put("txt", "a");
		map.put("column", "userid");
		
		List<Member2> list = mMapper.memberLikeList(map);
		System.out.println(list);
	}
>>>>>>> Stashed changes
}
