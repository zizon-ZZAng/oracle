package dto;

import lombok.Data;

@Data
public class Recommend {
	
	
	//member
	private String id;
	private String name;
	private String gender;
	private String address;
	
	//clothes
	private long clono;
	private String cloname;
	private String thickness;
	private String catetype;
	
	//weather
	private long code;
	private String wdate;
	private int temperature;
	private int rain;
	private String locname;
	
	//추가
	private int rank;

}
