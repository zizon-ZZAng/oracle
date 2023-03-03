package dto;

import java.util.Date;

import lombok.Data;
@Data
public class Member {

	private String userid;
	private String username;
	private String userpw;
	private int userage;
	private String userphone;
	private String usergender;
	private Date userdate ; 
	
	private Date useridd ;
	private Date useragee ;
	
}
