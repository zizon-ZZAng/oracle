package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import dto.Member;

@Mapper
public interface MemberMapper {

@Update({
	"<script> "
	, " UPDATE member SET "
	, " username = (CASE "
		, " <foreach collection = 'list' item='obj' separator=' '> "
			, " WHEN userid=#{obj.userid} THEN #{obj.username} "
		, " </foreach> "
	, " END), "
	, " userage = (CASE "
		, " <foreach collection = 'list' item='obj' separator=' '> "
			, " WHEN userid=#{obj.userid} THEN #{obj.userage} "
		, " </foreach> "
	, " END) "
	, " WHERE userid IN ( "
		, " <foreach collection = 'list' item='obj' separator=','> "
			, " #{obj.userid} "
			, " </foreach>  "
		, " ) "
	, " </script> "
})
public int memberUpdateBatch(@Param("list") List<Member> list);

@Update({
	 " MERGE INTO member "
	, " USING DUAL "
	, "    ON (userid =  #{obj.userid}) "
	, "    WHEN MATCHED THEN "
	, "        UPDATE SET username=#{obj.username}, userage = #{obj.userage} "
	, "    WHEN NOT MATCHED THEN "	
	, "        INSERT (userid, userpw, username, userage, userphone, usergender, userdate) "
	, "            VALUES(#{obj.userid}, #{obj.userpw}, #{obj.username},#{obj.userage}, #{obj.userphone}, #{obj.usergender}, CURRENT_DATE) "

})
public int memberUpSertBatch(@Param("list") List<Member> list);



// 있으면 바꾸고 없으면 안바꾸는 걸 할거임
// 가변부분이 if문에 의해 처리 됨

@Update({
	" <script> ",
	" UPDATE member SET username=#{obj.username} ",
		" <if test = 'obj.userage !=0'> ",
			" , userage=#{obj.userage} ",
			" </if> ",
		" <if test = 'obj.userphone != null'> ",
			" , userphone=#{obj.userphone} ",
			" </if> ",
		" <if test = 'obj.usergender !=null'> ",
			" , usergender=#{obj.usergender} ",
			" </if> ",
			"WHERE userid = #{obj.userid}",
			" </script> "
})
public int memberUpdateOne(@Param("obj") Member obj);	
	

@Select({
    
    "SELECT * FROM member WHERE ${map.column} LIKE '%' || #{map.txt} || '%'"
 })
 public List<Member> memberLikeList(@Param("map") Map<String, String> map);
 
// "SELECT * FROM member WHERE ",  "(${map.column} LIKE '%' || #{map.txt} || '%') OR ",  "(${map.column1} LIKE '%' || #{map.txt1} || '%'"






}
