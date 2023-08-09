package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

public interface CustomerMapper {
	// 고객 회원가입
	@Insert({" INSERT INTO customer(email, password, phone, address, chk) ",
			 " VALUES(#{email}, #{password}, #{phone}, #{address}, #{chk}) "})
	public int insertCustomer(Customer c);
	
	// 고객 로그인
	@Select({" SELECT c.* ",
			 " FROM customer c ",
			 " WHERE email = #{email} AND password = #{password} "})
	public Customer loginCustomer(@Param("email") String email, @Param("password") String password);
	
	// 고객 회원정보수정
	@Update({" UPDATE customer ",
			 " SET phone = #{phone}, address = #{address} ",
			 " WHERE email = #{email} AND password = #{password} "})
	public int updateCustomer(Customer c);
	
	// 고객 암호 변경
	@Update({" UPDATE customer ",
			 " SET password = #{newPassword} ",
			 " WHERE email = #{email} AND password = #{password} "})
	public int updateCustomerPW(Customer c);	
}