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
	// 차단 기능(0 or 1)
	private int chk;
	// 등록 일자
	private Date regdate;
	
	// 임시 변수
	 private String newPassword;
}
