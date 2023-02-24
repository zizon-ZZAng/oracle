package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Delivery {
	// 배달 번호
	private long no;
	// 배달 일시
	private Date regdate;
	// 배달자 연락처
	private String phone;
	// 주문 번호
	private long orderno;
}
