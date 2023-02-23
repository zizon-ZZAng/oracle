package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Menu {
  // 메뉴번호(시퀀스)
  private Long no;
  // 메뉴명
  private String name;
  // 가격
  private Long price;
  // 설명
  private String content;
  // 등록일자
  private Date regdate;
  // 전화번호
  private String phone;
  
  private String strPrice;	// => TO_CHAR 쓰려고 선언한듯?
  private String strregdate; // => TO_CHAR 쓰려고 선언한듯?
  private Long discountprice;
  
}
