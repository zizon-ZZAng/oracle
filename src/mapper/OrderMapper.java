package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Menu;
import dto.Order;

@Mapper
public interface OrderMapper {
	
	//주문하기
	@Insert({"INSERT INTO ordertbl(no,cnt,email,menuno) ", 
			 " VALUES(seq_ordertbl_no.NEXTVAL,#{cnt},#{email},#{menuno}) " })
	public int insertOrder(Order order);
	
	
	//주문수량 변경
	@Update({" UPDATE ordertbl SET cnt=#{cnt} WHERE no=#{no} AND email=#{email} "})
	public int updateOrder(Order order);
	
	//주문내역 조회
	@Select({"SELECT * FROM ordertbl WHERE email=#{email}"})
	public List<Map<String, Object>> selectOrder(String email);
	
	//(view) 주문내역 조회
	@Select({"SELECT * FROM ordertblview WHERE email=#{email}"})
	public List<Map<String, Object>> selectOrderView (String email);
	
	
	
	
}
