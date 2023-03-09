package connection;



import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import mapper.ClothesCateMapper;
import mapper.ClothesMapper;
import mapper.ClothessetMapper;
import mapper.LocationMapper;
import mapper.MemberMapper;
import mapper.RecommendMapper;
import mapper.WeatherMapper;

//MyBatisContext.getSqlSession();
public class MyBatisContext {
	public static SqlSession getSqlSession() {
		try {
			//DB접속용 dataSource 객체 생성
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			//@서버주소:포트번호:SID
			dataSource.setUrl("jdbc:oracle:thin:@1.234.5.158:11521:xe");
			dataSource.setUsername("ds236");
			dataSource.setPassword("pw236");
			
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config= new Configuration(environment);

			config.addMapper(MemberMapper.class);
			config.addMapper(RecommendMapper.class);
			config.addMapper(ClothesMapper.class);
			config.addMapper(ClothesCateMapper.class);
			config.addMapper(WeatherMapper.class);
			config.addMapper(LocationMapper.class);
			config.addMapper(ClothessetMapper.class);
			
			
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
