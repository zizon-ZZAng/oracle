package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface ViewMapper {
	// deliveryinfoview를 이용한 통계 구하기
	
	// 메뉴별 전체 주문 수량 및 전체금액 (어떤 메뉴가 잘 팔리는가?)
	@Select({" SELECT menuno, mname, SUM(cnt), SUM(price*cnt) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY menuno, mname "})
	public List<Map<String, Object>> selectGroupBYMenu();
	
	// 고객별 전체 주문 횟수 및 전체금액 (어떤 고객이 vip인가?)
	@Select({" SELECT email, COUNT(*), SUM(price*cnt) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY email "})
	public List<Map<String, Object>> selectGroupBYEmail(); 
	
	// 주문 시간대별 주문횟수 및 전체금액 (어느 시간대에 주문을 많이 하는가?)
	@Select({" SELECT TO_CHAR(regdate, 'HH24'), COUNT(*), SUM(price*cnt) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY TO_CHAR(regdate, 'HH24') "})
	public List<Map<String, Object>> selectGroupBYRegdate(); 
	
	// 배달시간대별 배달 횟수 (어느 시간대에 배달을 많이 하는가?)
	@Select({" SELECT TO_CHAR(dvdate, 'HH24'), COUNT(*) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY TO_CHAR(dvdate, 'HH24') "})
	public List<Map<String, Object>> selectGroupBYDvdate();
	
	// 배달자별 배달건수 (어느 배달기사가 배달을 많이 했는가?)
	@Select({" SELECT riderphone, COUNT(*) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY riderphone"})
	public List<Map<String, Object>> selectGroupBYRider();
	
	// 식당 연락처가 전달되면 해당 식당의 메뉴별 전체 주문 수량 (우리 가게에는 어떤 메뉴가 잘 나가나?)
	@Select({" SELECT menuno, mname, SUM(cnt) ",
			 " FROM deliveryinfoview ",
			 " WHERE rphone = #{rphone} ",
			 " GROUP BY menuno, mname "})
	public List<Map<String, Object>> selectGroupBYRestaurant(String rphone);
	
	// 요일별 주문횟수
	@Select({" SELECT TO_CHAR(regdate, 'DAY'), COUNT(*) ",
			 " FROM deliveryinfoview ",
			 " GROUP BY TO_CHAR(regdate, 'DAY') "})
	public List<Map<String, Object>> selectGroupBYDay();
}
