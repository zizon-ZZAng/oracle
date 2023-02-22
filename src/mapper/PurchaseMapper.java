package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Purchase;

public interface PurchaseMapper {

	//주문하기
	@Insert({" INSERT INTO purchase(no, cnt, regdate, code, userid) " ,
			 " VALUES (seq_purchase_no.NEXTVAL, #{cnt}, CURRENT_DATE, #{code}, #{userid}) "})
	
	public int insertPurchase(Purchase obj);
	
	//주문수량변경
	@Update({" UPDATE purchase SET cnt=#{cnt} " , 
		 	 " WHERE no=#{no} "})
	public int updatePurchaseCnt(Purchase obj);
	
	//아이디별 주문목록 조회
	@Select({" SELECT * FROM purchase ",
			 " WHERE userid=#{userid} ORDER BY no ASC " }) // 'SS아이디별'이라 조건이 필요했음
	public List<Purchase> selectPurchaseList(String userid);
	
	//아이디별 주문목록 조회(2) sql view가 자바에선 map으로 사용하면 된다.
	@Select({" SELECT * FROM purchaseview ",
			 " WHERE userid=#{userid} ORDER BY no ASC " }) 
	public List<Map<String, Object>> selectPurchaseViewList(String userid);
	
	//성별에 따른 구매수량 조회
	@Select({ " SELECT usergender, SUM(cnt) FROM purchaseview ",
			  " GROUP BY usergender HAVING usergender=#{usergender}" })
	public List<Map<String, Object>> selectPurchaseViewGroupByGender(String usergender);
	
	//고객별 구매수량, 총구매금액 조회
	@Select({ " SELECT userid, SUM(cnt), SUM(total) FROM purchaseview1 ",
			  " GROUP BY userid HAVING userid=#{userid} " })
	public List<Map<String, Object>> selectPurchaseViewGroupByUserid(String userid);
	
	
	//물품별 구매수량, 구매횟수, 총구매금액
	@Select({ " SELECT code, SUM(cnt), COUNT(*), SUM(total) FROM purchaseview1 ",
			  " GROUP BY code HAVING code=#{code}"})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode(long code);
	
	
	//월별 구매수량
	@Select({ " SELECT TO_CHAR(regdate, 'MONTH') month, SUM(cnt) FROM purchaseview1 ",
			  " GROUP BY TO_CHAR(regdate, 'MONTH')"})
	public List<Map<String, Object>> selectPurchaseViewGroupByMonth();
	
	
	
	//상품별 주문수량 개수가 20개 이상인 것 주문수량합계
	@Select({" SELECT code, name, SUM(cnt) FROM purchaseview1 ",
			 " GROUP BY code, name HAVING SUM(cnt) >= 20 "		})
	public List<Map<String, Object>> selectPurchaseViewGroupByCode2();
	

	
}
