package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient     注册发现@EnableEurekaClient只适用于eureka注册中心，@EnableDiscoveryClient适用所有其他的注册中心
//@EnableCircuitBreaker  启用hystrix  EnableHystrix被EnableCircuitBreaker取代了

@SpringCloudApplication //这个注解集成了上面三个注解
public class Sp06RibbonApplication {
	//新建RestTemplate实例，放入spring容器
	//@LoadBalanced 会生成一个动态代理，切入负载均衡代码
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(1000);
		factory.setReadTimeout(1000);
		return new RestTemplate(factory);
		//return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

}
