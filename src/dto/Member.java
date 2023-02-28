package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	private String userid;
	private String userpw;
	private String username;
	private int userage;
	private String usergender;
	private String userphone;
	private Date userdate;

}
