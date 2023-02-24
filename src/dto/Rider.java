package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Rider {
	// 연락처
	private String phone;
	// 이름
	private String name;
	// 등록 일자
	private Date regdate;
	// 암호
	private String password;
}
