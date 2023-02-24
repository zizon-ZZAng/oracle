package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.UpperCase;

import dto.Order;

public interface OrderMapper {
	//주문하기 (기본키 고객아이디+메뉴의정보)
	@Insert({
		" INSERT INTO ordertbl (no,cnt,email,menuno) ",
	    " VALUES(seq_ordertbl_no.NEXTVAL ,#{cnt},#{email},#{menuno}) "
	})
	public int insertOrder(Order o);
	

	//주문수량 변경
	@Update({
		" UPDATE ordertbl SET cnt=#{cnt}",
	    " WHERE email = #{email} AND menuno=#{menuno} " 
	})
	public int updateOrder(Order o);

	    
	//주문내역조회
	@Select({
		" SELECT o.* FROM ordertbl o WHERE o.email=#{email} "
	})
	public List<Order> selectOrderList(String email);
	
	//뷰 보기
	@Select({
		" SELECT * FROM ordertblview "
	})
	public List<Map<String, Object>> selectView();
	
	
}
