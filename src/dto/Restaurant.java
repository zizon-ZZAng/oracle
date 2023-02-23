package dto;

import java.beans.Transient;
import java.util.Date;

import lombok.Data;

// 식당테이블
	@Data
	public class Restaurant {
	  // 전화번호
	  private String retaurantphone;
	  // 가게이름
	  private String name;
	  // 주소
	  private String address;
	  // 로그인pw
	  private String password;
	  // 등록일자
	  private Date regdate;
	  
	  // 일시적 임시변수 = 데이터베이스의 타입과 관련없이 자바에서 데이터 전달용도
	  // @Transient 
	  private String newPassword;
	
	}
	

