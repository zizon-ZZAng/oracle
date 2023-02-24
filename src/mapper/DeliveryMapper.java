package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import dto.Delivery;

public interface DeliveryMapper {
	
	////배달 등록(기본키,외래키2)
	@Insert({
		" INSERT INTO delivery (no,phone,orderno) ",
	    " VALUES(seq_delivery_no.NEXTVAL,#{phone},#{orderno}) "
	})
	public int insertDelivery(Delivery d);
	
	//ordertblview+ 식당정보를 inner join 한 ordertblview1
	@Select({
		"SELECT * FROM ordertblview1 "
	})
	public List<Map<String, Object>> selectView1();
	
	//메뉴별 전체 주문 수량 및 전체금액 (어떤 메뉴가 잘팔리는가)
	@Select({
		" SELECT di.name, sum(di.cnt), sum(di.price*di.cnt) totalprice ",
	    " FROM deliveryinfoview di GROUP BY di.name "
	})
	public List<Map<String, Object>> selectMenuView();
	
	
	//고객별 전체 주문 횟수 및 전체금액 (어떤 고객이 vip인가)
	@Select({
		" SELECT di.email, count(di.cnt) count, sum(di.price*di.cnt) total ",
	    " FROM deliveryinfoview di GROUP BY di.email "
	})
	public List<Map<String, Object>> selectCustomerView();
	
	//주문 시간대별 주문횟수 및 전체금액 (어느 시간대에 주문을 많이 하는가)
	@Select({
		" SELECT di.regdate, count(di.cnt) count, sum(di.price*di.cnt) total ",
	    " FROM deliveryinfoview di GROUP BY di.regdate "
	})
	public List<Map<String, Object>> selectOrderView();
	
	
	//배달시간대별 배달 횟수 (어느 시간대 배달을 많이 하는가)
	@Select({
		" SELECT TO_CHAR(di.regdate,'HH24'), count(*) count ",
	    " FROM deliveryinfoview di GROUP BY TO_CHAR(di.regdate,'HH24') "
	})
	public List<Map<String, Object>> selectOrderHourView();
	
	
	//배달자별 배달건수 (어느 배달기사가 배달으 많이 했는가)
	@Select({
		" SELECT di.deliveryphone, count(di.deliveryno) ",
	    " FROM deliveryinfoview di GROUP BY di.deliveryphone "
	})
	public List<Map<String, Object>> selectDeliveryView();
	
	//식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량은?(우리가게에 어떤메뉴가 잘나가나)
	@Select({
		" SELECT di.name, sum(cnt) ",
	    " FROM deliveryinfoview di WHERE di.phone = #{phone} GROUP BY di.name "
	})
	public List<Map<String, Object>> selectRestaurantPhoneView(String phone);
	
	
	@Select({
		" SELECT TO_CHAR(di.regdate,'DAY') ,count(cnt) ",
		" FROM deliveryinfoview di GROUP BY TO_CHAR(di.regdate,'DAY') "
	})
	public List<Map<String, Object>> selectWeekdayView();
}
