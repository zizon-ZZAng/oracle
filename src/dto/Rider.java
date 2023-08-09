package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Rider {
	private String phone;
	private String name;
	private Date regdate;
	private String password;

}
