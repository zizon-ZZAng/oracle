package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.Delivery;

@Mapper
public interface DeliveryMapper {
	
	//배달 등록
	@Insert({" INSERT INTO delivery(no, phone, orderno) ", 
			 " VALUES(seq_delivery_no.NEXTVAL, #{delivery.phone}, #{delivery.orderno}) "})
	public int insertDelivery(@Param("delivery") Delivery delivery);
	
	
	//메뉴별 전체 주문 수량 및 전체 금액
	@Select({"SELECT menuno, name, sum(cnt) totalcnt, price * cnt totalprice  FROM deliveryinfoview df GROUP BY menuno, name, price, cnt"})
	public List<Map<String, Object>> deliveryMenuView();
	
//	고객별 전체주문 횟수 및 전체 금액 
	@Select({" SELECT email, COUNT(*) ordercount, sum(price * cnt) totalprice  FROM deliveryinfoview df GROUP BY email "})
	public List<Map<String, Object>> cutomerOrderView();
	
	
	//주문 시간대별 주문횟수 및 전체 금액
	@Select({"SELECT TO_CHAR(regdate,'HH24:MI')  , COUNT(*) ordercount, sum(price * cnt)totalprice FROM deliveryinfoview GROUP BY TO_CHAR(regdate,'HH24:MI') "})
	public List<Map<String, Object>> hourOrderView();
	
	//식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량
	@Select({" SELECT name, SUM(cnt) ordercnt FROM deliveryinfoview WHERE resphone=#{resphone} GROUP BY name, resphone "})
	public List<Map<String, Object>> restaurantPhoneMenuView(String phone);
	
	
	//배달 시간대별 배달 횟수
	@Select({" SELECT TO_CHAR(deliverydate, 'HH24') deliveryHour , COUNT(*) deliverycount FROM deliveryinfoview GROUP BY TO_CHAR(deliverydate, 'HH24') "})
	public List<Map<String, Object>> deliveryCountView();
	
	//배달자별 배달건수
	@Select({" SELECT ridername, COUNT(*) deliverycnt FROM deliveryinfoview  GROUP BY ridername "})
	public List<Map<String, Object>> deliveryCountView2();

	//요일별 주문횟수
	@Select({" SELECT TO_CHAR(regdate, 'DAY') day, COUNT(*) count FROM deliveryinfoview GROUP BY TO_CHAR(regdate, 'DAY') "})
	public List<Map<String, Object>> dayOrderView();
	
}
