package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Purchase;
import dto.Restaurant;

@Mapper
public interface PurchaseMapper {

	// 주문하기
	@Insert({ " INSERT INTO purchase(no,cnt,regdate,code,userid) ",
			" VALUES(seq_purchase_no.NEXTVAL, #{cnt}, CURRENT_DATE, #{code} , #{userid}) " })
	public int insertPurchase(Purchase obj);

	// 주문수량변경
	@Update({ " UPDATE purchase ", " SET cnt=#{cnt} ", " WHERE no = #{no} " })
	public int updatePurchaseCnt(Purchase obj);

	// 해당 아이디 주문목록조회 Purchase 테이블에서 조회
	@Select({ 
		" SELECT userid, no, regdate, code, cnt ", 
		" FROM Purchase ", 
		" WHERE userid = #{userid}" })
	public List<Purchase> selectPurchaseList(String userid);

	// View 해당 아이디 주문목록조회 Purchase 테이블에서 조회
	@Select({ 
		" SELECT p.* ", 
		" FROM Purchaseview p ", 
		" WHERE userid = #{userid}" })
	public List<Map<String, Object>> selectPurchaseViewList(String userid);

	// 성별에 따른 구매수량 조회 (map)
	@Select({ 
		" SELECT usergender, COUNT(*), SUM(total)", 
		" FROM purchaseview1 ", 
		" GROUP BY usergender " })
	public List<Map<String, Object>> selectPurchaseGroupByGender();

	// 고객별 구매수량, 총구매금액 조회
	@Select({
		" SELECT userid, cnt, SUM(total) ",
		" FROM purchaseview1 ",
		" GROUP BY userid, cnt " 
	})
	public List<Map<String, Object>> selectPurchaseGroupByUserid();

	// 물품별 구매수량, 구매횟수, 총구매금액
	@Select({
		" SELECT code, sum(cnt), COUNT(*), sum(total)" ,
		" FROM purchaseview1 " ,
		" GROUP BY code "
	})
	public List<Map<String, Object>> selectPurchaseGroupByCode();

	// 월별구매수량
	@Select({
		" SELECT TO_CHAR(regdate, 'MM'), sum(cnt) " ,
		" FROM purchaseview1 " ,
		" GROUP BY TO_CHAR(regdate, 'MM') "
	})
	public List<Map<String, Object>> selectPurchaseGroupByMonth();

}
