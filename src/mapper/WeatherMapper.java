package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Weather;

@Mapper

public interface WeatherMapper {

	@Insert({
			" INSERT INTO WEATHER1 (code, regdate, weather, temperature, no) VALUES(func_SEQ_WEATHER1_CODE_nextval, CURRENT_DATE, #{weather}, #{temperature}, #{no}) " })
	public int weatherInsert(Weather w);

	@Update({
			" UPDATE WEATHER1 SET weather= #{weather}, temperature = #{temperature}, no = #{no} WHERE code = #{code} " })
	public int weatherUpdate(Weather w);

	// 시간 업데이트
	@Update({ " UPDATE WEATHER1 SET regdate = TO_DATE(#{regdate}, 'YYYY-MM-DD-HH24') WHERE code = #{code} " })
	public int weatherUpdateHour(Weather w);

	// 날씨 업데이트
	@Update({ " UPDATE WEATHER1 SET temperature = #{temperature} WHERE code = #{code} " })
	public int weatherUpdateTemp(Weather w);

	@Select({ " SELECT * FROM WEATHER1  " })
	public List<Weather> weatherSelect();

	
	
	// 시간가져오기
	// no는 넣거나 빼거나 필요에 따라
	@Select({ " SELECT no, TO_CHAR(regdate, 'HH24') regdate2 FROM WEATHER1 WHERE no = #{no} " })
	public List<Weather> weatherSelectHOUR(Weather w);

	// 날짜가져오기
	// no는 넣거나 빼거나 필요에 따라
	@Select({ 
		" SELECT no, TO_CHAR(regdate, 'YYYY-MM-DD') regdate2 ",
		" FROM WEATHER1 ",
		" WHERE no = #{no} " 
		})
	public List<Weather> weatherSelectDATE(Weather w);
	
	// 뷰가져오기?
	@Select({ " SELECT wv.* FROM WEATHER1_LOCATION1_VIEW wv " })
	public List<Map<String, Object>> weatherSelectWV();
	
	
	
<<<<<<< Updated upstream
=======
// 뷰로(지역이름,날짜,시간으로) 기온 가져오기
	@Select({ 
			" SELECT temperature, weather",
			" from WEATHER1_LOCATION1_VIEW ",
			" WHERE name = #{name} and w_date = #{w_date} and w_hour = #{w_hour} " })
	public List<Map<String, Object>> weatherSelectWVTemp(Map<String, Object> map);
	
>>>>>>> Stashed changes
	
	@Delete({ " DELETE FROM WEATHER1 WHERE code = #{code} " })
	public int weatherDelete(Weather w);

}