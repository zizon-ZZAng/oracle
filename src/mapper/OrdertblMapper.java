package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Ordertbl;

public interface OrdertblMapper {
	// 주문하기(기본키 : 주문번호 no, 외래키 : 고객이메일 email, 외래키 : 메뉴번호 menuno)
	@Insert({" INSERT INTO ordertbl(no, cnt, email, menuno) ",
			 " VALUES(seq_ordertbl_no.NEXTVAL, #{cnt}, #{email}, #{menuno}) "})
	public int insertOrdertbl(Ordertbl order);
	
	// 주문수량 변경(해당고객의)
	@Update({" UPDATE ordertbl ",
			 " SET cnt = #{cnt} ",
			 " WHERE no = #{no} AND email = #{email} "})
	public int updateOrdertblCnt(@Param("no") long no,
								 @Param("email") String email,
								 @Param("cnt") long cnt);
	
	// 주문내역 조회(해당고객의)
	@Select({" SELECT o.* FROM ordertbl o ",
			 " WHERE email = #{email} "})
	public List<Ordertbl> selectOrdertblList(@Param("email") String email);
}
