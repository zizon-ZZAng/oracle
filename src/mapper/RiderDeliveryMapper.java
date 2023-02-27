package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.RiderDelivery;

public interface RiderDeliveryMapper {

	@Insert({
		" INSERT INTO rider (phone, name, password) ",
	    " VALUES (#{phone}, #{name}, #{password}) "
	})
	public int insertRider (RiderDelivery rider);
	
	@Select({
		 " SELECT r.* FROM rider r WHERE phone = #{phone} "
	})
	public RiderDelivery selectRider (RiderDelivery rider);
	
	@Insert({
		" INSERT INTO delivery (no, phone, orderno) ",
	    " VALUES(#{no}, #{phone}, #{orderno}) "
	})
	public int insertdelivery (RiderDelivery obj);
	
	@Select({
		" SELECT name, SUM(cnt), SUM(price*cnt) ",
		" FROM deliveryinfoview ",
		" GROUP BY name "
	})
	public List <Map<String, Object>> selectMenu();
	
	@Select({
		" SELECT email, COUNT(*), SUM(price*cnt) ",
		" FROM deliveryinfoview ",
		" GROUP BY email "
	})
	public List <Map<String, Object>> selectCustomer();
	
	@Select({
		" SELECT TO_CHAR(regdate, 'HH24'), COUNT(*), SUM(price*cnt) ",
		" FROM deliveryinfoview ",
		" GROUP BY TO_CHAR(regdate, 'HH24') "
	})
	public List <Map<String, Object>> selectRegdate();
	
	@Select
	({
		" SELECT  TO_CHAR(deliverdate, 'HH24'), COUNT(*) ",
		" FROM deliveryinfoview ",
		" GROUP BY TO_CHAR(deliverdate, 'HH24') "
	})
	public List <Map<String, Object>> selectDeliverDate();
	
	@Select({
		" SELECT ridername, COUNT(*) ",
		" FROM deliveryinfoview ",
		" GROUP BY ridername "
	})
	public List <Map<String, Object>> selectRiderName();
	
	@Select({
		" SELECT name, SUM(cnt) ",
		" FROM deliveryinfoview ",
		" WHERE resphone = #{resphone} ",
		" GROUP BY name "
	})
	public List <Map<String, Object>> selectResphone(String resphone);
	
	@Select({
		" SELECT TO_CHAR(regdate, 'DAY'), COUNT(*) ",
		" FROM deliveryinfoview ",
		" GROUP BY TO_CHAR(regdate, 'DAY') "
	})
	public List <Map<String, Object>> selectDayMenu();
}
