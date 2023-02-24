package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import dto.Delivery;

@Mapper
public interface DeliveryMapper {

	@Insert({
		" INSERT INTO delivery(no, regdate, phone, orderno) ",
		" VALUES ( seq_delivery_no.nextval, CURRENT_DATE, #{phone},#{orderno}) " 
	})
	public int insertDelivery(Delivery obj);
	
}
