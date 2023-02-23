package dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

// 메뉴테이블
@Data
public class Menu {

	  // 메뉴번호 시퀀스
	  private Long menuno;
	  // 메뉴명
	  private String name;
	  // 가격
	  private Long price;
	  // 설명
	  private String content;
	  // 등록일자
	  private Date regdate;
	  // 전화번호
	  private String retaurantphone;
	  
	  
	  //임시변수
	  private String strPrice;
	  private String strRegdate;
	  private String disPrice;
	}

