package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import connection.MyBatisContext;
import dto.Item;
import mapper.ItemMapper;

class ItemMapperTest {
	ItemMapper iMapper = MyBatisContext.getSqlSession().getMapper(ItemMapper.class);
	
	@Test
	void itemInsertBatch() {
		List<Item> list = new ArrayList<>();
		for(int i=0; i<5; i++) {
			Item item = new Item();
			item.setName("품명"+i);
			item.setPrice(1200+i);
			item.setQuantity(2400+i);
			item.setContent("내용"+i);
			list.add(item);
		}
		System.out.println(iMapper.itemInsertBatch(list));
	}
}
