package connection;



import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import mapper.CustomerMapper;
import mapper.DeliveryMapper;
import mapper.ItemMapper;
import mapper.MemberMapper;
import mapper.MenuMapper;
import mapper.OrderMapper;
import mapper.PurchaseMapper;
import mapper.RestaurantMapper;
import mapper.RiderMapper;

//MyBatisContext.getSqlSession();
public class MyBatisContext {
	public static SqlSession getSqlSession() {
		try {
			//DB접속용 dataSource 객체 생성
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			//@서버주소:포트번호:SID
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");
			dataSource.setUsername("ds228");
			dataSource.setPassword("pw228");
			
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config= new Configuration(environment);
			config.addMapper(RestaurantMapper.class);
			config.addMapper(MenuMapper.class);
			config.addMapper(PurchaseMapper.class);
			config.addMapper(CustomerMapper.class);
			config.addMapper(OrderMapper.class);
			config.addMapper(RiderMapper.class);
			config.addMapper(DeliveryMapper.class);
			config.addMapper(MemberMapper.class);
			config.addMapper(ItemMapper.class);
			
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
