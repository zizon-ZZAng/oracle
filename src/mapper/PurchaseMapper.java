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
	@Insert({
		" INSERT INTO purchase(no, cnt, code, userid, regdate) ",
		" VALUES(seq_purchase_no.NEXTVAL,#{cnt},#{code},#{userid},CURRENT_DATE) "
	})
	public int insertPurchase(Purchase obj);
	
	
	// 주문수량변경
	@Update({
		" UPDATE purchase SET cnt=#{cnt} ",
		" WHERE no=#{no} AND code=#{code} AND userid=#{userid} "
	})
	public int updatePurchaseCnt(Purchase obj);
	
	
	// 해당 아이디별 주문목록조회 purchase 테이블에서 조회
	@Select({
		" SELECT p.* FROM purchase p WHERE userid=#{userid} "
	})
	public List<Purchase> selectPurchaseList(String userid);
	
	
	// 해당 아이디별 주문목록조회 purchaseview 테이블에서 조회
	@Select({
		" SELECT pv.* FROM purchaseview pv WHERE userid=#{userid} "
	})
	public List<Map<String, Object>> selectPurchaseViewList(String userid);
	
	
	// 성별에 따른 구매수량 조회
	@Select({
		" SELECT usergender, SUM(cnt) FROM purchaseview ",
		" GROUP BY usergender "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByGender();
	
	
	// 고객별 구매수량, 총구매금액 조회
	@Select({
		" SELECT userid, SUM(cnt), SUM(cnt*price) total ",
		" FROM purchaseview1 GROUP BY userid ",
		" ORDER BY userid ASC "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByUserid();
	
	// 물품별 구매수량, 구매횟수, 총구매금액
	@Select({
		" SELECT code, SUM(cnt), COUNT(code), SUM(cnt*price) total ",
		" FROM purchaseview GROUP BY code "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode();
	
	// 월별 구매수량
	@Select({
		" SELECT TO_CHAR(regdate,'MONTH'), SUM(cnt) ",
		" FROM purchaseview ",
		" GROUP BY TO_CHAR(regdate,'MONTH') "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByMonth();
	
}

