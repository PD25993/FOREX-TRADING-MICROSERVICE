package com.docker.samplemicroservice.currencyconversionservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.docker.samplemicroservice.currencyconversionservice.models.CurrencyConversion;
import com.docker.samplemicroservice.currencyconversionservice.repositories.ICurrencyConverterRepo;

@RestController
public class CurrencyConversionController {

	@Autowired
	private ICurrencyConverterRepo repository;


	@GetMapping(value="/from/{from}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CurrencyConversion retriveExchangeValue(@PathVariable String from, @PathVariable String to){
		System.out.println("In retriveExchangeValue" + from + " " + to);
		CurrencyConversion currencyConversion = 
				repository.findByFromAndTo(from, to);

		return currencyConversion;
	}

}
