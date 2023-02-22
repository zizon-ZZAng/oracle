package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Purchase;

@Mapper
public interface PurchaseMapper {

	// 주문하기
	@Insert({
		" INSERT INTO purchase (no, cnt, code, userid, regdate) ",
		" VALUES (seq_purchase_no.NEXTVAL, #{cnt}, #{code}, #{userid}, #{regdate}) "
	})
	public int insertPurchase( Purchase obj );
	
	// 주문수량 변경
	@Update({
		" UPDATE purchase SET cnt = #{cnt} ", 
		" WHERE no = #{no} AND code =#{code} AND userid =#{userid} "
	})
	public int updatePurchase( Purchase obj );
	
	// 해당 아이디별 주문목록 조회 purchase테이블에서 조회
	@Select({
		" SELECT p.* FROM purchase p WHERE userid = #{userid} "
	})
	public List<Purchase> selectPurchaseList( String userid );
	
	// 해당 아이디별 주문목록 조회 purchaseview테이블에서 조회
	@Select({
			" SELECT p.* FROM purchaseview p WHERE userid = #{userid} "
	})
	public List <Map<String, Object>> selectPurchaseViewList( String userid );
	
	// 주문 취소
	@Delete({
		" DELETE FROM purchase WHERE no = #{no} AND code =#{code} AND userid =#{userid} "
	})
	public int deletePurchase (Purchase obj);
	
	// 성별에 따른 구매수량 조회
	@Select({
		" SELECT usergender 성별, SUM(cnt) 구매수량 FROM purchaseview GROUP BY usergender "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByGender();
	
	// 고객별 구매수량, 총구매금액 조회
	@Select({
		" SELECT userid 고객, SUM(cnt) 구매수량, SUM(price*cnt) 총구매금액 FROM purchaseview GROUP BY userid "
		
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByUserid();
	
	// 물품별 구매수량, 구매횟수, 총구매금액
	@Select({
		" SELECT code 물품, SUM(cnt) 구매수량, COUNT(*) 구매횟수, SUM(price*cnt) 총구매금액 FROM purchaseview GROUP BY code "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode();
	
	// 월별 구매수량
	@Select({
		" SELECT TO_CHAR (regdate,'MM') month , SUM(cnt) 구매수량 FROM purchaseview GROUP BY TO_CHAR (regdate,'MM') "
	})
	public List<Map<String, Object>> selectPurchaseViewGroupByMonth();
}
