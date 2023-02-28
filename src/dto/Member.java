package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {

	private String userid;
	
	public String userpw;
	
	public String username;
	
	public int userage;
	
	public String userphone;
	
	public String usergender;
	
	private Date userdate;
	
}
