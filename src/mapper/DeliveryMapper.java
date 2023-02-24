package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dto.Delivery;

@Mapper
public interface DeliveryMapper {
	
	//배달 등록
	@Insert({" INSERT INTO delivery(no, phone, orderno) ", 
			 " VALUES(seq_delivery_no.NEXTVAL, #{delivery.phone}, #{delivery.orderno}) "})
	public int insertDelivery(@Param("delivery") Delivery delivery);
	
	
	
}
