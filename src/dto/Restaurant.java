package dto;

import java.util.Date;

import lombok.Data;

// 식당테이블
@Data
public class Restaurant {
	// 전화번호
	private String phone;
	// 가게이름
	private String name;
	// 가게주소
	private String address;
	// 암호
	private String password;
	// 등록일자
	private Date regdate;
}
