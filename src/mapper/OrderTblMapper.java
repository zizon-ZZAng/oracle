package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.OrderTbl;

@Mapper
public interface OrderTblMapper {
	
	//주문하기(기본키, 고객아이디+메뉴의정보)
	@Insert
	({
		" INSERT INTO ordertbl (ortherno,  regdate, cnt, email, menuno) "
		, " VALUES (seq_ordertbl_no.nextval, CURRENT_DATE, #{cnt}, #{email}, #{menuNo}) "
	})
	public int insertOrderTbl(OrderTbl obj);
	
	//주문수량변경
	@Update
	({
		" UPDATE ordertbl "
		, " SET cnt = #{cnt} "
		, " WHERE ortherno = #{ortherNo} AND email = #{email} "
	})
	public int updateOrderTbl(OrderTbl obj);
	
	
	//주문내역조회
	@Select
	({
		" SELECT o.* FROM ordertbl o WHERE email = #{email} "
	})
	public List<OrderTbl> selectOrderTbl (OrderTbl obj);
	
	
	//뷰
	@Select
	({
		" SELECT ov.* FROM ordertblview ov "
	})
	public List<Map<String, Object>> selectOrderTblView();
}
