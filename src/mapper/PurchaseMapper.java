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
	
	//주문하기
	@Insert({
		" INSERT INTO purchase(no,cnt,code,userid,regdate) ",
		" VALUES(seq_purchase_no.NEXTVAL,#{cnt},#{code},#{userid},CURRENT_DATE) "
	})
	public int insertPurchase(Purchase obj);
	
	//주문수량 변경
	@Update({
		" UPDATE purchase SET cnt=#{cnt} ",
		" WHERE no=#{no} "
	})
	public int updatePurchaseCnt(Purchase obj);
	
	
	//해당 아이디 주문목록조회 purchase 테이블에서 조회
	@Select({
		" SELECT  p.* ",
	    " FROM purchase p WHERE userid=#{userid} "
		})
	public List<Purchase> selectPurchaseList(String userid);
	
	//해당 아이디 주문목록조회 purchaseview 테이블에서 조회
	@Select({
		" SELECT  p.* ",
	    " FROM purchaseview p WHERE userid=#{userid} "
		})
	public List<Map<String, Object>> selectPurchaseviewList(String userid);

	
	
	//성별에 따른 구매수량 조회
	@Select({
		" SELECT usergender 성별, SUM(cnt) 구매수량 ",
		" FROM purchaseview GROUP BY usergender HAVING usergender=#{usergender} "
		 })
	public List<Map<String, Object>> selectPurchaseViewGroupByGender(String usergender);
	//고객별 구매수량, 총구매금액 조회
	@Select({
		" SELECT userid 고객, sum(price*cnt) 총구매금액 ",
		" FROM purchaseview GROUP BY userid ORDER BY userid DESC "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByUserid();
	
	//물품별 구매수량, 구매횟수, 총구매금액
	@Select({
		" SELECT code 물품명, sum(cnt) 구매수량, count(*) 구매횟수, sum(cnt*price) 총구매금액",
		" FROM purchaseview1 GROUP BY code " 	
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode();
	
	
	//월별 구매수량
	@Select({
		" SELECT TO_CHAR(regdate,'MONTH') 월, sum(cnt) 구매수량 ",
		" FROM purchaseview1 GROUP BY TO_CHAR(regdate,'MONTH') "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByMonth();
	
	
	
}
