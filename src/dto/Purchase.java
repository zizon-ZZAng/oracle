package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Purchase {
	private long no;
	private long cnt;
	private long code;
	private String userid;
	private Date regdate;

}
