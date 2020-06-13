package com.docker.samplemicroservice.forextradingservice.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.docker.samplemicroservice.forextradingservice.models.CurrencyConversion;
import com.docker.samplemicroservice.forextradingservice.models.CurrencyDetailsToReturn;
import com.docker.samplemicroservice.forextradingservice.models.ForexExchangeDetailsBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

public class ForexTradingDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallBackForexTradingDetails",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			})
	public ForexExchangeDetailsBean getForexTradingDetailsByFromAndToCountryName(String from, String to) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ForexExchangeDetailsBean finalResult = new ForexExchangeDetailsBean();

//		ResponseEntity<CurrencyDetailsToReturn> responseEntity  = restTemplate.getForEntity(
//				"http://localhost:8083/from/{from}/to/{to}", CurrencyDetailsToReturn.class,
//				uriVariables);
		ResponseEntity<CurrencyDetailsToReturn> responseEntity  = restTemplate.getForEntity(
				"http://currency-details-service/from/{from}/to/{to}", CurrencyDetailsToReturn.class,
				uriVariables);
		CurrencyDetailsToReturn currencyDetailsToReturn = responseEntity.getBody();
		
//		System.out.println("from : " + currencyDetailsToReturn.getFromCountryCurrency());
//		System.out.println("to : "+ currencyDetailsToReturn.getToCountryCurrency());
		
		Map<String, String> uriVariablesForCurrencyConversion = new HashMap<>();
		uriVariablesForCurrencyConversion.put("from", currencyDetailsToReturn.getFromCountryCurrency());
		uriVariablesForCurrencyConversion.put("to", currencyDetailsToReturn.getToCountryCurrency());

		CurrencyConversion currencyConversion = this.getDetailsFromCurrencyConversion(uriVariablesForCurrencyConversion);

		finalResult.setFromCountryName(currencyDetailsToReturn.getFrom());
		finalResult.setToCountryName(currencyDetailsToReturn.getTo());
		finalResult.setFromCountryCurrency(currencyDetailsToReturn.getFromCountryCurrency());
		finalResult.setToCountryCurrency(currencyDetailsToReturn.getToCountryCurrency());
		finalResult.setConversionMultiple(currencyConversion.getConversionMultiple());
		
		return finalResult;

	}

	@HystrixCommand(fallbackMethod = "getFallBackCurrencyConversion",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			})
	private CurrencyConversion getDetailsFromCurrencyConversion(Map<String, String> uriVariablesForCurrencyConversion) {

		CurrencyConversion currencyConversion=new CurrencyConversion();
		ResponseEntity<CurrencyConversion> responseEntity  = restTemplate.getForEntity(
				"http://currency-conversion-service/from/{from}/to/{to}", CurrencyConversion.class,
				uriVariablesForCurrencyConversion);
		currencyConversion = responseEntity.getBody();
		return currencyConversion;
	}
	
	public ForexExchangeDetailsBean getFallBackForexTradingDetails(String from, String to)
	{
		ForexExchangeDetailsBean forexExchangeDetailsBean  = new ForexExchangeDetailsBean();
		
		Map<String, String> uriVariablesForCurrencyConversion = new HashMap<>();
		uriVariablesForCurrencyConversion.put("from", "Unknown Source Country");
		uriVariablesForCurrencyConversion.put("to", "Unknown Destination Country");
		
		CurrencyConversion currencyConversion = this.getFallBackCurrencyConversion(uriVariablesForCurrencyConversion);

		forexExchangeDetailsBean.setFromCountryName(from);
		forexExchangeDetailsBean.setToCountryName(to);
		forexExchangeDetailsBean.setFromCountryCurrency("Unknown Courrency");
		forexExchangeDetailsBean.setToCountryCurrency("Unknown Courrency");
		forexExchangeDetailsBean.setConversionMultiple(currencyConversion.getConversionMultiple());
		
		return forexExchangeDetailsBean;
	}
	
	public CurrencyConversion getFallBackCurrencyConversion(Map<String, String> uriVariablesForCurrencyConversion)
	{
		CurrencyConversion currencyConversion  = new CurrencyConversion();
		currencyConversion.setId(null);
		currencyConversion.setFrom("Unknown Source Country");
		currencyConversion.setTo("Unknow Destination Country");
		currencyConversion.setConversionMultiple(null);
		return currencyConversion;
	}
	
	

}
