package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Customer {
  // 이메일
  private String email;
  // 암호
  private String password;
  // 연락처
  private String phone;
  // 주소
  private String address;
  // 0,1 블랙리스트
  private Long chk;
  // 등록일자
  private Date regdate;
  
  // 임시변수
  private String newPassword;
}
