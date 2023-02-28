package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Item {

	private long code;
	private long price;
	private long quantity;
	private String name; 
	private String content; 
	private Date regdate;
}
