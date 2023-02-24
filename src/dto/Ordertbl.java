package dto;

import java.util.Date;

import lombok.Data;

// 주문테이블
@Data
public class Ordertbl {
	// 주문번호(시퀀스)
	private long no;
	// 주문일자
	private Date regdate;
	// 주문수량
	private long cnt;
	// 이메일
	private String email;
	// 메뉴번호(시퀀스)
	private long menuno;
}