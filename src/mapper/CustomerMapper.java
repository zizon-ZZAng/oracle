package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Customer;

@Mapper
public interface CustomerMapper {

	@Insert({
		" INSERT INTO customer(email, password, phone, address, chk) ",
	    " VALUES(#{email}, #{password}, #{phone}, #{address},#{chk}) "	
	})
	public int insertCustomer(Customer c);
	
	@Select({
		" SELECT c.* FROM customer c ",
		" WHERE c.email =#{email} AND c.password =#{password} "
	})
	public Customer selectLoginCustomer(@Param("email") String e, @Param("password") String p);
	
	@Update({
		" UPDATE customer SET phone =#{phone}, address =#{address} ",
		" WHERE email =#{email} AND password =#{password} "
	})
	public int updateCustomer(Customer c);
	
	@Update({
		" UPDATE customer SET password =#{newPassword} ",
		" WHERE email =#{email} AND password =#{password} "
	})
	public int updatePasswordCustomer(Customer c);
	
	// 회원탈퇴는 주문을 하면 삭제가 안된다. 주문내역 출처로 인해서.. UPDATE를 이용해서 정보를 지움. 지운 후 삭제하라는 말인가?
	@Delete({
		" DELET FROM customer ",
		" WHERE email=#{obj.email} AND password=#{obj.password} "
	})
	public int deleteCustomer(@Param("obj") Customer obj);
}
