package mapper;

import org.apache.ibatis.annotations.Insert;
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
}
