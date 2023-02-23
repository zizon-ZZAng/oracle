package dto;

import java.util.Date;

import lombok.Data;


@Data
public class Customer {
	
	private String email;
	private String password;
	private String phone;
	private String address;
	private int chk;
	private Date regdate;
	
	private String newPassword;

}
