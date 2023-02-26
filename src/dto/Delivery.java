package dto;

import java.util.Date;
import lombok.Data;
@Data
public class Delivery {
	private String deliveryno;
	private int riderphone;
	private Date regdate;
	private int ortherno;

}
