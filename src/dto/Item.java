package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Item {
<<<<<<< Updated upstream
	private long code;
	private long price;
	private long quantity;
	private String name;
=======

	private long code;
	private String name;
	private long price;
	private long quantity;
>>>>>>> Stashed changes
	private String content;
	private Date regdate;
}
