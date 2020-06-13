package com.docker.samplemicroservice.currencydetailsservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.docker.samplemicroservice.currencydetailsservice.models.CurrencyDetails;
import com.docker.samplemicroservice.currencydetailsservice.models.CurrencyDetailsToReturn;
import com.docker.samplemicroservice.currencydetailsservice.repositories.ICurrencyDetailsRepo;


@RestController
public class CurrencyDetailsController {
	
	@Autowired
	private ICurrencyDetailsRepo repository;
	

	@GetMapping(value="/from/{country}/to/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CurrencyDetailsToReturn retriveExchangeValue(@PathVariable String country, @PathVariable String to){

		CurrencyDetailsToReturn currencyDetailsToReturn = new CurrencyDetailsToReturn();
		CurrencyDetails currencyDetailsForm = 
				repository.findByCountry(country);
		//System.out.println(currencyDetailsForm.getCountry()+" : "+currencyDetailsForm.getCountryCurrency());
		country=to;
		CurrencyDetails currencyDetailsTo = 
				repository.findByCountry(country);
//		System.out.println(currencyDetailsForm.getCountry()+" : "+currencyDetailsForm.getCountryCurrency()+" : "+
//				currencyDetailsTo.getCountry()+" : "+currencyDetailsTo.getCountryCurrency());
		
		currencyDetailsToReturn.setFrom(currencyDetailsForm.getCountry());
		currencyDetailsToReturn.setTo(currencyDetailsTo.getCountry());
		currencyDetailsToReturn.setFromCountryCurrency(currencyDetailsForm.getCountryCurrency());
		currencyDetailsToReturn.setToCountryCurrency(currencyDetailsTo.getCountryCurrency());
		return currencyDetailsToReturn;
		
	}


}
