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
	  
	  // 일시적변수 암시 = 데이터베이스의 타입과 상관없이 java에서 데이터 전달용도
	  // @Transient => 이런 기능이 있는데 에러떠서 주석 걸어놓음.
	  private String newpassword;
	}

