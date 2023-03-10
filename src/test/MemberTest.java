package test;

import java.security.MessageDigest;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Member;
import mapper.MemberMapper;

class MemberTest {

   
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
   void loginMember() { 
      
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
   void updateMember() {
      
      
      String id = "s";
      String pw = "s";
            
      String hash = this.hashPW(pw, id);
      
      
      Member member = new Member();
      

      member.setId("s");
      member.setPassword(hash);
      
      member.setName("김변경");
      member.setAddress("부산");
      
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
   
   //회원 1명 조회
   @Test
   void selectMemberOne() {
	 
	  System.out.println(mapper.selectMemberOne("s")); 
   }
   
   
   // 아이디 있는지 체크
   @Test
   void selectMemberIdChk() {
	   System.out.println(mapper.selectMemberIdChk("c"));
   }
   

}