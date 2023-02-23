package connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import dto.Purchase;
import mapper.CustomerMapper;
import mapper.MenuMapper;
import mapper.PurchaseMapper;
import mapper.RestaurantMapper;

public class MyBatisContext {

	public static SqlSession getSqlSession() {
		try {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
			// 오라클 기준
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");	// @서버주소:포트번호:SID 
			dataSource.setUsername("ds204");
			dataSource.setPassword("pw204");
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config = new Configuration(environment);
			config.addMapper(RestaurantMapper.class);	
			config.addMapper(MenuMapper.class);
			config.addMapper(PurchaseMapper.class);
			config.addMapper(CustomerMapper.class);
			
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true); // true이면 자동으로 commit을 수행함.
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
