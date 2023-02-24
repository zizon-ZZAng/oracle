package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
	private long no;
	private long cnt;
	private String email;
	private long menuno;
	private Date regdate;
}
