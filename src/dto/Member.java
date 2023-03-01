package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
<<<<<<< Updated upstream

	private String userid;
	public String userpw;
	public String username;
	public int userage;
	public String usergender;
	public String userphone;
	private Date userdate;
	
=======
	
	 private String userid;
	 private String userpw;
	 private String username;
	 private int userage;
	 private String userphone;
	 private String usergender;
	 private Date userdate = new Date();
	 // private int ret;
	  
>>>>>>> Stashed changes
}
