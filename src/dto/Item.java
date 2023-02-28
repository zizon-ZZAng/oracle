package dto;

import java.util.Date;

import lombok.Data;
@Data
public class Item {

	private long code;
	private String name;
	private String quantity;
	private String content;
	private long price;
	private Date regdate;

	
}
