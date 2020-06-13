package com.docker.samplemicroservice.forextradingservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDetailsToReturn {

	private String from;

	private String to;

	private String fromCountryCurrency;

	private String toCountryCurrency;

}
