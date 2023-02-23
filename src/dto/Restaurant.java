package dto;

import java.beans.Transient;
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
	
	// 임시변수 : 데이터베이스의 타입과 관련없이 자바에서 데이터를 전달하는 기능만 함.
	// @Transient
	private String newPassword;
}
