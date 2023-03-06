package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Weather {

	private long code;
	private String wdate;
	private int temperature;
	private int rain;
	private String locname;
}
