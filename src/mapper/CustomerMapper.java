package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

@Mapper
public interface CustomerMapper {

	// 고객 회원가입
	@Insert({ " INSERT INTO customer(email, password, phone, address, chk) ",
			" VALUES(#{email}, #{password}, #{phone}, #{address}, #{chk}) " })
	public int insertCustomer(Customer obj);

	// 고객 로그인
	@Select
		({ 
		" SELECT c.* FROM customer c " 
		, " WHERE email = #{email} AND password = #{password} " 
		})
	public Customer selectCustomerLogin(Customer obj);

	//고객 회원정보수정
	@Update
	({ 
		" UPDATE customer " ,
		" SET phone= #{phone}, address= #{address} ",
		" WHERE email = #{password} AND password = #{password} "
	})
	public int updateCustomer(Customer obj);
	
	//고객 암호변경
	@Update
	({
		" UPDATE customer "
		, " SET password = #{newpassword} "
		, " WHERE email = #{email} AND password = #{password} " 
		
	})
	public int updateCustomerPw(Customer obj);
	
}
