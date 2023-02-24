package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
	
	private long no;
	private Date regdate;
	private int cnt;
	private String email;
	private int menuno;
	

}
