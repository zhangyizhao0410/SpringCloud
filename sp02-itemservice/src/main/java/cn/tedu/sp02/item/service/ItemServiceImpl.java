package cn.tedu.sp02.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
	
	//根据订单编号，获取订单中的商品列表
	@Override
	public List<Item> getItems(String orderId) {
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(new Item(1, "商品 1",1));
		list.add(new Item(2, "商品 2",2));
		list.add(new Item(3, "商品 3",3));
		list.add(new Item(4, "商品 4",4));
		list.add(new Item(5, "商品 5",5));
		return list;
	}
	
	//减少库存
	@Override
	public void decreaseNumbers(List<Item> list) {
		for(Item item : list) {
			log.info("减少库存 - "+item);
		}
	}
}
