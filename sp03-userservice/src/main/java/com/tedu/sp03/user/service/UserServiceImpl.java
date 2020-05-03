package com.tedu.sp03.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;
/*
 * 只有加了@RefreshScope 注解bean实例，才会被重新注入新的配置数据
 */
@RefreshScope
@Slf4j
@Service
public class UserServiceImpl implements UserService {
	//yml配置的测试用户数据注入进来
	@Value("${sp.user-service.users}")
	private String userJson;
	
	@Override
	public User getUser(Integer id) {
		log.info("users json string : "+userJson);
		List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
		for (User u : list) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		
		return new User(id, "name-"+id, "pwd-"+id);
	}

	@Override
	public void addScore(Integer id, Integer score) {
		// 这里增加积分
		log.info("user "+id+" - 增加积分 "+score);
	}

}
