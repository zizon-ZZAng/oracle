package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Purchase;

@Mapper
public interface PurchaseMapper {
	// 주문하기
	@Insert({" INSERT INTO purchase(no, cnt, code, userid) ", 
			 " VALUES(seq_purchase_no.NEXTVAL, #{cnt}, #{code}, #{userid}) "})
	public int insertPurchase(Purchase obj);
	
	// 주문수량 변경
	@Update({" UPDATE purchase SET cnt = #{cnt} WHERE no = #{no}"})
	public int updatePurchaseCnt(Purchase obj);
	
	// 해당 아이디 주문목록 조회
	@Select({" SELECT pv.* FROM purchaseview pv ",
			 " WHERE userid = #{userid} "})
	public List<Map<String, Object>> selectPurchaseViewList(String userid);
	
	// 성별에 따른 구매수량 조회 
	@Select({" SELECT usergender, SUM(cnt) FROM purchaseview ",
			 " GROUP BY usergender "})
	public List<Map<String, Object>> selectPurchaseViewGroupByGender();
	
	// 고객별 구매수량, 총구매금액 조회
	@Select({" SELECT userid, SUM(cnt), SUM(price*cnt) FROM purchaseview ",
			 " GROUP BY userid ",
			 " ORDER BY userid "})
	public List<Map<String, Object>> selectPurchaseViewGroupByUserid();
	
	// 물품별 구매수량, 구매횟수, 총구매금액 조회
	@Select({" SELECT code, SUM(cnt), COUNT(*), SUM(price*cnt) ",
			 " FROM purchaseview ",
			 " GROUP BY code "})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode();
	
	// 월별 구매수량
	@Select({" SELECT TO_CHAR(regdate, 'MONTH') month, SUM(cnt) ",
			 " FROM purchaseview ",
			 " GROUP BY TO_CHAR(regdate, 'MONTH') "})
	public List<Map<String, Object>> selectPurchaseViewGroupByMonth();
}
