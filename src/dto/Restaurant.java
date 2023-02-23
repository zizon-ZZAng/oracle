package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Restaurant {
	  // 전화번호
	  private String phone;
	  // 가게 이름
	  private String name;
	  // 가게 주소
	  private String address;
	  // 암호
	  private String password;
	  // 등록 일자
	  private Date regdate;
	  
	  // 일시적 변수(임시 변수) 
	  // -> 데이터베이스의 타입과 관련없이 자바에서 데이터 전달용도
	  // @Transient
	  private String newPassword;
}
