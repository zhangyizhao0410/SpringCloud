package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

//通过指定服务id，可以知道想那一台主机转发调用
//http://localhost:8001
//http://localhost:8002
@FeignClient(name="item-service",fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	
	/**
	 * feign 利用熟悉的spring mvc注解
	 * 来反向生成访问路径 
	 * 根据Mapper中指定的路径，在主机地址后面凭借路径
	 * //http://localhost:8001/{orderId}
	 * 根据@PathVariable配置，把参数数据，拼接到路径当中
	 * 向拼接的路径。来转发调用
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);
	
	/*
	 * 根据配置，拼接下面的路径，并向下面路径转发请求
	 * 在协议体中，携带商品参数
	 * //http://localhost:8001/decreaseNumber
	 */
	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
