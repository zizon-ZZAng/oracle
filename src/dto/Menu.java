package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Menu {
  // 메뉴번호(시퀀스)
  private Long no;
  // 메뉴이름
  private String name;
  // 메뉴가격
  private Long price;
  // 메뉴설명
  private String content;
  // 등록일자
  private Date regdate;
  // 전화번호
  private String phone;
  
}