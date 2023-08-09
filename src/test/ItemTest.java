package test;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Item;
import mapper.ItemMapper;

class ItemTest {
	
	ItemMapper iMapper= MyBatisContext.getSqlSession().getMapper(ItemMapper.class);
	
	@Test
	void itemInsertBatch() {
		List<Item> list = new ArrayList<>();
		for(int i=1;i<5;i++) {
			Item item = new Item();
			item.setContent("내용");
			item.setName("아이템");
			item.setPrice(15000);
			item.setQuantity(2643);
			list.add(item);
			
		}
		int ret= iMapper.itemInsertBatch(list);
		System.out.println(ret);
	}

}
