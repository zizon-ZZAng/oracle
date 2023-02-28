package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Item {
	
	private long code;
	private String name;
	private long price;
	private long quantity;
	private String content;
	private Date regdate;

	
	
	
}
