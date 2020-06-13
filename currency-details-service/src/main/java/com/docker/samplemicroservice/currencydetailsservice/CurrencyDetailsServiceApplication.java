package com.docker.samplemicroservice.currencydetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyDetailsServiceApplication.class, args);
	}

}
