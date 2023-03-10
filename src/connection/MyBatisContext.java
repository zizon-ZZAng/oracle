package connection;



import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

<<<<<<< Updated upstream
import mapper.ClothesCateMapper;
import mapper.ClothesMapper;
import mapper.LocationMapper;
import mapper.MemberMapper;
import mapper.RecommendMapper;
import mapper.WeatherMapper;
=======
import mapper.CustomerMapper;
import mapper.DeliveryMapper;
import mapper.ItemMapper;
import mapper.MenuMapper;
import mapper.OrdertblMapper;
import mapper.PurchaseMapper;
import mapper.RestaurantMapper;
import mapper.RiderMapper;
import mapper1.ClothesCateMapper;
import mapper1.ClothesMapper;
import mapper1.LocationMapper;
import mapper1.MemberMapper;
import mapper1.RecommendMapper;
import mapper1.WeatherMapper;
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
			
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config= new Configuration(environment);

=======
			
			TransactionFactory transactionFactory = new JdbcTransactionFactory();
			Environment environment = new Environment("development", transactionFactory, dataSource);
			Configuration config = new Configuration(environment);
			
			// 만든 매퍼 등록
//			config.addMapper(RestaurantMapper.class);
//			config.addMapper(MenuMapper.class);
//			config.addMapper(PurchaseMapper.class);
//			config.addMapper(CustomerMapper.class);
//			config.addMapper(OrdertblMapper.class);
//			config.addMapper(RiderMapper.class);
//			config.addMapper(DeliveryMapper.class);		
//			config.addMapper(MemberMapper.class);		
//			config.addMapper(ItemMapper.class);		
//			config.addMapper(ClothesMapper.class);		
//			config.addMapper(ClothesCateMapper.class);		
			
			
>>>>>>> Stashed changes
			config.addMapper(MemberMapper.class);
			config.addMapper(RecommendMapper.class);
			config.addMapper(ClothesMapper.class);
			config.addMapper(ClothesCateMapper.class);
			config.addMapper(WeatherMapper.class);
			config.addMapper(LocationMapper.class);
<<<<<<< Updated upstream
=======
			
>>>>>>> Stashed changes
			
			
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
			return factory.openSession(true);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
