package dto;

import java.util.Date;

import lombok.Data;


@Data
public class Member2 {
	private String id;
	private String name;
	private String gender;
	private String password;
	private String address;
	private int chk;
	private Date regdate;
	
	private String newpw;

}
