package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Customer {

	private String email;
	
	private String password;
	
	private String phone;
	
	private String address;
	
	private Date regdate;
	
	private int chk;
	
	private String newpassword;
	
	
}
