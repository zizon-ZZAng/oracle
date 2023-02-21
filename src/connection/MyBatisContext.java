package connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;


// 외부에서 쓸 땐 MyBatisContext.getSqlSession();
public class MyBatisContext {
	
	public static SqlSession getSqlSession() {
		try {
			
			// DB접속용 dataSource객체 생성
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
