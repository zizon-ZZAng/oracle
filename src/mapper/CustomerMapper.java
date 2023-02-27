package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

public interface CustomerMapper {

	
	@Insert({
		" INSERT INTO customer( email, password, phone, address, chk) ",
	    " VALUES (#{email}, #{password}, #{phone}, #{address}, #{chk}) "
	})
	public int insertCustomer(Customer obj); 
	
	
	@Select({
		" SELECT c.* FROM customer c WHERE email = #{email} "
	})
	public Customer selectCustomerLogin (Customer obj);
	
	
	@Update({
		" UPDATE customer SET phone = #{phone}, address = #{address} ",
		" WHERE email = #{email} "
	})
	public int updateCustomer(Customer email);
	
	
	@Update({
		" UPDATE customer SET password = #{newpassword} ",
		" WHERE email = #{email} AND password = #{password} "
	})
	public int updateCustomerPw (Customer email);
	
	// 회원탈퇴는 주문을 하면 삭제가 안됨. 주문내역 출처로 인해서... update를 이용해서 정보를 지움.
	@Delete({
		" DELETE FROM customer ",
		" WHERE email=#{email} AND password=#{password} "
	})
	public int deleteCustomer (Customer obj);
}

// -------------------------------------------------------------------------------------
// <교수님이 하신 것>

//@Insert({
//	" INSERT INTO customer(email, password, address, phone, chk) ",
//	" VALUES(#{obj.email}, #{obj.password}, #{obj.address}, #{obj.phone}, #{obj.chk}) "
//})
//public int insertCustomer( @Param("obj") Customer obj );
//
//
//@Update({
//	" UPDATE customer SET address=#{obj.address}, phone=#{obj.phone} ",
//	" WHERE email=#{obj.email} AND password=#{obj.password} "
//})
//public int updateCustomer( @Param("obj") Customer obj );
//
//// 회원탈퇴는 주문을 하면 삭제가 안된다. 주문내역 출처로 인해서 .. UPDATE를 이용해서 정보를 지움,
//@Delete({
//	" DELETE FROM customer ",
//	" WHERE email=#{obj.email} AND password=#{obj.password} "
//})
//public int deleteCustomer( @Param("obj") Customer obj );
//
//
//@Update({
//	" UPDATE customer SET password=#{obj.newPassword} ",
//	" WHERE email=#{obj.email} AND password=#{obj.password} "
//})
//public int updateCustomerPassword( @Param("obj") Customer obj );
//
//
//@Select({
//	" SELECT c.* FROM customer c WHERE c.email=#{email} "
//})
//public Customer selectCustomerOne( @Param("email") String e );
//
//}

