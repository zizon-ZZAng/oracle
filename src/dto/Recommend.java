package dto;

import lombok.Data;

@Data
public class Recommend {

	// 추천번호
	private long reno;
	// 아이디
	private String id;
	// 고유키(code)
	private int code;

	// 임시변수
	// 옷 순서
	private int rank;
	// 주소
	private String address;
	// 날짜
	private String week;
	// 시간
	private String hour;
}
