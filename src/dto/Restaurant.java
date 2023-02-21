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
}
