package zizonWeather;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiGet {
	
	// 4. API 호출하고 데이터 받기 (DELETE)

	public static void main(String[] args) {
		String url = "http://203.247.66.28/url/fct_shrt_reg.php?tmfc=0&authKey=6b89a8e82e7dc3a444ec0d0b0dbbd21ef86bc5c2151e15f9a5629a3ca147bb56cda47ea0a5305014474e1404bd68f65128fb2b80ce121bc85e4909c0b5c175a9";

		httpTestByMethod(url, "GET");
		
		httpTestByMethod(url, "POST");
		
		httpTestByMethod(url, "DELETE");
	}
	
	public static void httpTestByMethod(String url, String method) {
		
		HttpUtils htppUtils = new HttpUtils();
		String result = "";
		HttpURLConnection conn = htppUtils.getHttpURLConnection(url, method);;

		if("GET".equalsIgnoreCase(method)){
			
			// conn.setDoInput(true); //URL 연결에서 데이터를 읽을지에 대한 설정 ( defualt true )
			result = htppUtils.getHttpRespons(conn);
		}else if("POST".equalsIgnoreCase(method)) {
			
			conn.setDoOutput(true); //URL 연결시 데이터를 사용할지에 대한 설정 ( defualt false )
			try (DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());){
				
				String str = "{\"user\" : \"kimchy\",    "
						+ "\"post_date\" : \"2009-11-15T14:12:12\",    "
						+ "\"message\" : \"trying out Elasticsearch\"}";
				
				dataOutputStream.writeBytes(str);
				dataOutputStream.flush();
				
				result = htppUtils.getHttpRespons(conn);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("DELETE".equalsIgnoreCase(method)) {			
			result = htppUtils.getHttpRespons(conn);
		}
		
		System.out.println("Method = " + method + "/ result = " + result);
	}
}


class HttpUtils {
	
	public HttpURLConnection getHttpURLConnection(String strUrl, String method) {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(strUrl);
			
			conn = (HttpURLConnection) url.openConnection();
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
				//정상적으로 데이터를 받았을경우
				sb = readResopnseData(conn.getInputStream());
			}else{
				//정상적으로 데이터를 받지 못한 경우 
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
				
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
	
	
	//3. API 호출하고 데이터 받기 (POST)

//	conn.setDoOutput(true); //URL 연결시 데이터를 사용할지에 대한 설정 ( defualt false )
//	try (DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());){
//
//	  String str = "{\"user\" : \"kimchy\",    "
//	  + "\"post_date\" : \"2009-11-15T14:12:12\",    "
//	  + "\"message\" : \"trying out Elasticsearch\"}";
//
//	  dataOutputStream.writeBytes(str);
//	  dataOutputStream.flush();
//
//	  result = htppUtils.getHttpRespons(conn);
//
//	} catch (IOException e) {
//	  // TODO Auto-generated catch block
//	  e.printStackTrace();
//	}
	
}