package com.docker.samplemicroservice.forextradingservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.docker.samplemicroservice.forextradingservice.models.ForexExchangeDetailsBean;
import com.docker.samplemicroservice.forextradingservice.services.ForexTradingDetailsService;

@RestController
public class ForexTradingDetailsController {
	
	@Autowired
	private ForexTradingDetailsService forexTradingDetailsService;
	
	@GetMapping(value="/ForexDetails/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ForexExchangeDetailsBean getFinalForexDetailsByParams(@PathVariable String from, @PathVariable String to){
		
		System.out.println("Inside ForexTradingDetailsController -> getFinalForexDetailsByParams" + from +" "+to);
		return forexTradingDetailsService.getForexTradingDetailsByFromAndToCountryName(from, to);
		
	}

}
