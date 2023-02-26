package dto;

import java.util.Date;

import lombok.Data;

@Data

public class OrderTbl {
	
	private int ortherNo;
	private int cnt;
	private String email;
	private int menuNo;
	private Date regdate;
	 

}
