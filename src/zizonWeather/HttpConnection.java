package zizonWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnection {

public static void main(String[] args) {
		
		HttpUtils htppUtils = new HttpUtils();
		
		String url = "http://203.247.66.28/url/fct_shrt_reg.php?tmfc=0&authKey=6b89a8e82e7dc3a444ec0d0b0dbbd21ef86bc5c2151e15f9a5629a3ca147bb56cda47ea0a5305014474e1404bd68f65128fb2b80ce121bc85e4909c0b5c175a9";
		String method = "GET";
		String result = "";
		HttpURLConnection conn = null;
		
        //HttpURLConnection 객체 생성
		conn = htppUtils.getHttpURLConnection(url, method);
		//URL 연결에서 데이터를 읽을지에 대한 설정 ( defualt true )
        // conn.setDoInput(true); 
		//API에서 받은 데이터를 StringBuilder 형태로 리턴하여 줍니다. 
        result = htppUtils.getHttpRespons(conn);
        //해당 정보를 확인합니다.
		System.out.println("GET = " + result);

	}
}

class HttpUtils {
	
	public HttpURLConnection getHttpURLConnection(String strUrl, String method) {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(strUrl);

			conn = (HttpURLConnection) url.openConnection(); //HttpURLConnection 객체 생성
			conn.setRequestMethod(method); //Method 방식 설정. GET/POST/DELETE/PUT/HEAD/OPTIONS/TRACE
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
	
	public String getHttpRespons(HttpURLConnection conn) {
		StringBuilder sb = null;

		try {
			if(conn.getResponseCode() == 200) {
            // 정상적으로 데이터를 받았을 경우
            	//데이터 가져오기
				sb = readResopnseData(conn.getInputStream());
			}else{
            // 정상적으로 데이터를 받지 못했을 경우
            
            	//오류코드, 오류 메시지 표출
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
				//오류정보 가져오기
				sb = readResopnseData(conn.getErrorStream());
				System.out.println("error : " + sb.toString());
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.disconnect(); //연결 해제
		};
		if(sb == null) return null;
        
		return sb.toString();
	}
	
	public StringBuilder readResopnseData(InputStream in) {
		if(in == null ) return null;

		StringBuilder sb = new StringBuilder();
		String line = "";
		
		try (InputStreamReader ir = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(ir)){
			while( (line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb;
	}
}


