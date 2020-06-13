package com.docker.samplemicroservice.forextradingservice.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
	
	private Long id;
	
	private String from;

	private String to;

	private BigDecimal conversionMultiple;

}
