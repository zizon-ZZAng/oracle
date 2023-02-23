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
import mapper.MenuMapper;
import mapper.PurchaseMapper;
import mapper.RestaurantMapper;

// MyBatisContext.getSqlSession();
public class MyBatisContext {
	public static SqlSession getSqlSession() {
		try {
			// DB 접속용 dataSource 객체 생성
			BasicDataSource dataSource = new BasicDataSource();
			
			// 오라클 기준
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			// @서버주소:포트번호:SID
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");
			dataSource.setUsername("ds211");
			dataSource.setPassword("pw211");
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config = new Configuration(environment);
			// 만든 mapper 등록
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
