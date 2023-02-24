package dto;

import java.util.Date;

import lombok.Data;

//배달테이블
@Data
public class Delivery {
// 배달번호
private Long no;
// 배달일시
private Date regdate;
// 연락처
private String phone;
// 주문번호(시퀀스)
private Long orderno;
}
