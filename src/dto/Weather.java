package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Weather {

	private int code;
	private Date wdate;
	private int temperature;
	private int rain;
	private String name;
}
