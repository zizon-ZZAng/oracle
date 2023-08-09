package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dto.Item;

@Mapper
public interface ItemMapper {

	@Insert({
		" <script> ",
		" INSERT INTO item(code,name,price,quantity, content, regdate) ",
		" SELECT SEQ_ITEM_CODE.NEXTVAL code, T1.* FROM ( ",
			" <foreach collection = 'list' item='obj' separator=' UNION ALL '> ",
		" SELECT  '${obj.name}' name, '${obj.price}' price, '${obj.quantity}' quantity, '${obj.content}' content, CURRENT_DATE regdate FROM DUAL ",
			" </foreach> ",
		" ) T1 ",
		" </script> "
	})
	public int itemInsertBatch(@Param("list") List<Item> list);
}
