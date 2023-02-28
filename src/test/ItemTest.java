package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Item;
import mapper.ItemMapper;

class ItemTest {

	ItemMapper iMapper 
	= MyBatisContext.getSqlSession().getMapper(ItemMapper.class);
	
	@Test
	void itemInsertBatch() {
		List<Item> list = new ArrayList<>();
		for(int i=0; i<5; i++) {
			Item item = new Item();
			item.setContent("내용");
			item.setName("이름");
			item.setPrice(1200);
			item.setQuantity(2400);
			list.add(item);
		}
		int ret = iMapper.itemInsertBatch(list);
		System.out.println(ret);
		
	}

}
