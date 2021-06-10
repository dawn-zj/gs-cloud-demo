package com.gs.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class ConsumerController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/instances")
	public List<ServiceInstance> instances(){
		List<ServiceInstance> provider = discoveryClient.getInstances("my-provider");
		return provider;
	}

	@GetMapping("/consumer")
	public String test1() {
		// 获取提供者实例
		// List<ServiceInstance> provider = discoveryClient.getInstances("my-provider");
		// int index = ThreadLocalRandom.current().nextInt(provider.size());
		// 调用提供者实例的index方法
		// String url = provider.get(index).getUri() + "/index";
		// return "consumer随机远程调用provier：" + this.restTemplate.getForObject(url, String.class);

		return "consumer随机远程调用provier：" + this.restTemplate.getForObject("http://my-provider/index", String.class);
	}
}
