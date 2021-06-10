package com.gs.provider.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {

	@Bean
	public IRule getRule() {
		// 实现带有权重的负载均衡策略
		return new BalanceWeightRule();
	}
}
