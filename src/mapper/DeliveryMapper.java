package mapper;

import org.apache.ibatis.annotations.Insert;

import dto.Delivery;

public interface DeliveryMapper {
	// 배달 등록(기본키, 외래키 2개)
	@Insert({" INSERT INTO delivery(no, phone, orderno) ",
			 " VALUES(seq_delivery_no.NEXTVAL, #{phone}, #{orderno}) "})
	public int insertDelivery(Delivery d);
}
