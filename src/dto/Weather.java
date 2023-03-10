package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Weather {

	// 날씨테이블
	
	private int code;
	// 넘버(고유키)
	
	private Date regdate;
	// 날짜
	
	private String weather;
	// 날씨
	
	private float temperature;
	// 기온
	
	private String address;
	// 지역넘버(외래키)

	private String regdate2;
	// 임시변수
	
	private String week;
	// 임시변수
	private int hour;
	// 임시변수
	private String name1;
	// 임시변수
}

