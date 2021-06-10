package com.gs.provider.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Nacos 自定义负载策略
 */
public class BalanceWeightRule extends AbstractLoadBalancerRule {
	@Autowired
	private NacosDiscoveryProperties discoveryProperties;

	@Override
	public Server choose(Object o) {
		// 获取负载均衡的对象
		BaseLoadBalancer baseLoadBalancer = (BaseLoadBalancer) getLoadBalancer();

		// 获取当前调用的微服务的名称
		String serviceName = baseLoadBalancer.getName();

		// 获取Nocas服务发现的相关组件API
		NamingService namingService = discoveryProperties.namingServiceInstance();

		try {
			// 获取一个基于 nacos client 实现权重的负载均衡算法
			Instance instance = namingService.selectOneHealthyInstance(serviceName);
			System.out.println("选择的实例是port={" + instance.getPort() + "},instance={" + instance + "}");

			// 返回一个nacos的server
			return new NacosServer(instance);
		} catch (NacosException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void initWithNiwsConfig(IClientConfig iClientConfig) {

	}
}
