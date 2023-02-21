package connection;

import java.lang.module.Configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


// 외부에서 쓸 땐 MyBatisContext.getSqlSession();
public class MyBatisContext {
	
	public static SqlSession getSqlSession() {
		try {
			
			// DB접속용 dataSource객체 생성
			BasicDataSource dataSource = new BasicDataSource();
			
			//오라클 기준
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			//@서버주소:포트번호:SID
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");
			dataSource.setUsername("ds210");
			dataSource.setPassword("pw210");
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory(); 
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config = new Configuration(environment);

			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true);	//true면 자동으로 commit을 수행함
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
