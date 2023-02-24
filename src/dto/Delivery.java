package dto;

import java.util.Date;

import lombok.Data;


@Data
public class Delivery {
	
	private int no;
	private Date regdate;
	private String phone;
	private long orderno;

}
