package dto;

import java.util.Date;

import lombok.Data;

//메뉴 테이블
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
// 등록 일자
private Date regdate;
// 전화번호
private String phone;
}