package dto;

import java.util.Date;

import lombok.Data;
@Data
public class Delivery {
	private long no;
	private Date regdate;
	private String phone;
	private long orderno;

}
