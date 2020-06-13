package com.docker.samplemicroservice.forextradingservice.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForexExchangeDetailsBean {
	private String fromCountryName;
	private String toCountryName;
	private String fromCountryCurrency;
	private String toCountryCurrency;
	private BigDecimal conversionMultiple;

}
