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
	
	//회원가입
	@Insert({" INSERT INTO customer(email,password,phone,address,chk) ", 
		 	 " VALUES(#{email},#{password},#{phone},#{address},#{chk}) "})
	public int insertCutomer(Customer customer);
	
	
	//로그인
	@Select({" SELECT c.email, c.phone, c.address  FROM customer c WHERE email=#{email} AND password=#{password} "})
	public Customer customerLogin(Customer customer);
	
	//로그인이나, 1개 조회일 경우는 Customer, Reataurant 와 같이 설정하는거임 왜냐? 수많은 목록 중에 하나를 찾는거니깐 (검증느낌??) int를 쓸 필요가 없다 
	// 조회가 안되거나 되거나는 Customer로 List아님
	
	
	//회원정보수정
	@Update({" UPDATE customer SET phone=#{customer.phone}, address=#{customer.address} WHERE email=#{customer.email} AND password=#{customer.password} "})
	public int customerUpdate(@Param("customer") Customer customer);
	
	
	//암호변경
	@Update({" UPDATE customer SET password=#{customer.newPassword} WHERE email=#{customer.email} AND password=#{customer.password} "})
	public int customerChange(@Param("customer") Customer customer );
	
	
	//회원탈퇴는 주문을 하면 삭제가 안된다. 주문 내역 출처로 인해서.. UPDATE를 이용해서 정보를 지움
	@Delete({" DELETE FROM customer ", " WHERE email=#{customer.email} AND password=#{customerpassword} "})
	public int deleteCustomer(@Param("customer") Customer customer);
	
	
	//1명 조회
	
	

}
