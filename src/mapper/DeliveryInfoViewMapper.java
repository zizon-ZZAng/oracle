package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface DeliveryInfoViewMapper {

	//메뉴별 전체 주문수량 및 전체금액(어떤 메뉴가 잘 팔리는가?)
	@Select
	({
		" SELECT menuname, SUM(odrcnt), (menuprice * odrcnt) "
		, " FROM deliveryinfoview GROUP BY menuprice, menuname, odrcnt "
	})
	public List<Map<String, Object>> totalMenuTotalpri();
	
	//고객별 전체 주문횟수 및 전체금액(어떤 고객이 vip인가?)
	@Select
	({
		" SELECT cusemail, SUM(odrcnt), SUM(menuprice * odrcnt) "
		, " FROM deliveryinfoview GROUP BY cusemail "
	})
	public List<Map<String, Object>> totalCustomerTotalpri();
	
	//배달 시간대별 주문횟수 및 전체금액(어느 시간대에 주문을 많이 하는가?)
	@Select
	({
		" SELECT TO_CHAR(dlvrregdate, 'HH24'), COUNT(*) odrcnt, SUM(menuprice * odrcnt) "
		, "FROM deliveryinfoview GROUP BY TO_CHAR(dlvrregdate, 'HH24') "
	})
	public List<Map<String, Object>> totalcntDateHHTotalpri();
	
	
	
	//배달자별 배달건수(어떤 라이더가 배달을 많이 했는가?)
	@Select
	({
		" SELECT ridphone, COUNT(*) odrcnt "
		, " FROM deliveryinfoview GROUP BY ridphone "
	})
	public List<Map<String, Object>> riderOrderCnt();
	
	
	//식당연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량?(우리가게에는 어떤 메뉴가 가장 잘나가나)
	@Select
	({
		" SELECT menuname, SUM(odrcnt) "
		, " FROM deliveryinfoview WHERE rp = '051-153-3226' GROUP BY menuname "
	})
	public List<Map<String, Object>> RestaurantBestMenu();
	
	//요일별 주문횟수
	@Select
	({
		" SELECT TO_CHAR( orrdate, 'day' ), COUNT(*) odrcnt "
		, " FROM deliveryinfoview GROUP BY TO_CHAR( orrdate, 'day' ) "
	})
	public List<Map<String, Object>> dayOfBestOrder();
	
}
