package mapper;

import java.util.List;

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
	@Select({"SELECT * FROM purchase ",
			 " WHERE userid=#{userid} ORDER BY no ASC " }) // 아이디별이라 조건이 필요했음
	public List<Purchase> selectPurchaseList(String userid);
	
	
	
	

	
}
