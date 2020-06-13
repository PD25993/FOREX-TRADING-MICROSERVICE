package com.docker.samplemicroservice.currencydetailsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.samplemicroservice.currencydetailsservice.models.CurrencyDetails;

public interface ICurrencyDetailsRepo extends JpaRepository<CurrencyDetails, Long>{

	//CurrencyDetails findByFromAndTo(String from ,String to);
	//CurrencyDetails findByForm(String from);
	CurrencyDetails findByCountry(String country);
	
}
