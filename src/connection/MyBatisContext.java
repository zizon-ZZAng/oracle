package connection;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import mapper.CateMapper;
import mapper.ClothesMapper;
import mapper.LocationMapper;
import mapper.MemberMapper;
import mapper.RecommendMapper;
import mapper.WeatherMapper;

public class MyBatisContext {
	
	private Connection conn;

	public static SqlSession getSqlSession() {
		try {

			// DB접속용 dataSource객체 생성
			BasicDataSource dataSource = new BasicDataSource();

			// 오라클 기준
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			// @서버주소:포트번호:SID
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");
			dataSource.setUsername("ds236");
			dataSource.setPassword("pw236");

			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config = new Configuration(environment);
			
			// 만든 매퍼 등록
			config.addMapper(MemberMapper.class);
			config.addMapper(ClothesMapper.class);
			config.addMapper(CateMapper.class);
			config.addMapper(WeatherMapper.class);
			config.addMapper(LocationMapper.class);
			config.addMapper(RecommendMapper.class);

			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true); // true면 자동으로 commit을 수행함

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	//Connection 객체를 리턴하는 메소드
	public Connection getConn() {
		return conn;
		
	}

}
