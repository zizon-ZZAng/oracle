package dto;

import lombok.Data;

@Data
public class Member {
	// 아이디
	private String id;
	// 이름
	private String name;
	// 성별
	private String sex;
	// 암호
	private String password;
	// 주소
	private String address;
	// 탈퇴 유무(0 or 1)
	private int chk;
	
	// 임시 변수
	 private String newPassword;
}