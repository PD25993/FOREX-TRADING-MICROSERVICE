package com.docker.samplemicroservice.currencyconversionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.docker.samplemicroservice.currencyconversionservice.models.CurrencyConversion;

public interface ICurrencyConverterRepo extends JpaRepository<CurrencyConversion, Long>{
	CurrencyConversion findByFromAndTo(String from, String to);

}
