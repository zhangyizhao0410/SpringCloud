package com.tedu.sp04.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.sp04.order.feignclient.ItemFeignService;
import com.tedu.sp04.order.feignclient.UserFeignService;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
	
	//feign动态生成接口实现类
	@Autowired
	private ItemFeignService itemService;
	@Autowired
	private UserFeignService userService;
	
	//根据订单id获取订单数据
	//要包含用户信息
	//要包含订单中的商品信息
	@Override
	public Order getOrder(String orderId) {
		//调用user-service获取用户信息
		JsonResult<User> user = userService.getUser(7);
		//调用item-service获取商品信息
		JsonResult<List<Item>> items = itemService.getItems(orderId);
		Order order = new Order();
		order.setId(orderId);
		order.setItems(items.getData());
		order.setUser(user.getData());
		return order;
	}
	
	/**
	 * 添加订单时
	 * 要修改商品库存
	 * 要修改用户积分
	 */
	@Override
	public void addOrder(Order order) {
		//调用item-service减少商品库存
		itemService.decreaseNumber(order.getItems());
		//TODO: 调用user-service增加用户积分
		userService.addScore(order.getUser().getId(), 100);
		log.info("保存订单："+order);
	}

}
