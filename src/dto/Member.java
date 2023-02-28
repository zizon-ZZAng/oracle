package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String userid;
	public String userpw;
	public String username;
	public int userage;
	public String usergender;
	public String userphone;
	private Date userdate;
}
