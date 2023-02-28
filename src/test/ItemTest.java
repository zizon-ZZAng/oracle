package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Item;
import mapper.ItemMapper;

class ItemTest {
	ItemMapper mapper = MyBatisContext.getSqlSession().getMapper(ItemMapper.class);	

	@Test
	void itemInsertBatch() {
		List<Item> list = new ArrayList<>();
		for(int i=0; i<5; i++) {
			Item item = new Item();
			item.setContent("sodyd");
			item.setName("dlfma");
			item.setQuantity("?");
			item.setContent("sodyd");
			item.setPrice(6);
			list.add(item);
		}
		
	int ret  =  mapper.itemInsertBatch(list);
	System.out.println(ret);
	}

}
