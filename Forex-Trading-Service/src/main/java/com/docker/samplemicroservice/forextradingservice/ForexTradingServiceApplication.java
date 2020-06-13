package com.docker.samplemicroservice.forextradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.docker.samplemicroservice.forextradingservice.services.ForexTradingDetailsService;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ForexTradingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexTradingServiceApplication.class, args);
	}
	@Bean
	public ForexTradingDetailsService getForexTradingDetailsService() {
		return new ForexTradingDetailsService();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplateInstance()
	{
		return new RestTemplate();
	}

}
