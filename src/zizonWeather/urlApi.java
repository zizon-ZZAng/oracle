package zizonWeather;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class urlApi {
	
	// 1. HttpURLConnection 객체 생성
	HttpURLConnection getHttpURLConnection() {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://203.247.66.28/url/fct_shrt_reg.php?tmfc=0&authKey=6b89a8e82e7dc3a444ec0d0b0dbbd21ef86bc5c2151e15f9a5629a3ca147bb56cda47ea0a5305014474e1404bd68f65128fb2b80ce121bc85e4909c0b5c175a9");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); //Method 방식 설정. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
			conn.setConnectTimeout(5000); //연결제한 시간 설정. 5초 간 연결시도
			conn.setRequestProperty("Content-Type", "application/json");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	


}
