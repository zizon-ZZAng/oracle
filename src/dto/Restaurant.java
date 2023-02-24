package dto;

import java.beans.Transient;
import java.util.Date;

import lombok.Data;

@Data
public class Restaurant {
  // 전화번호
  private String phone;
  // 가게이름
  private String name;
  // 주소
  private String address;
  // 암호
  private String password;
  // 등록일자
  private Date regdate;
  
  //일시적변수 임시 = 데이터베이스의 타입과 관련없이 java에서 데이터 전달용도로만 쓰이는
//@Transient
  private String newPassord;
}