package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

@Mapper
public interface CustomerMapper {
	
	//회원가입
	@Insert({" INSERT INTO customer(email,password,phone,address,chk) ", 
		 	 " VALUES(#{email},#{password},#{phone},#{address},#{chk}) "})
	public int insertCutomer(Customer customer);
	
	
	//로그인
	@Select({" SELECT c.email, c.phone, c.address  FROM customer c WHERE email=#{email} AND password=#{password} "})
	public Customer customerLogin(Customer customer);
	
	
	
	//회원정보수정
	@Update({" UPDATE customer SET phone=#{phone}, address=#{address} WHERE email=#{email} AND password=#{password} "})
	public int customerUpdate(Customer customer);
	
	
	//암호변경
	@Update({" UPDATE customer SET password=#{newPassword} WHERE email=#{email} AND password=#{password} "})
	public int customerChange(Customer customer );
	
	

}
