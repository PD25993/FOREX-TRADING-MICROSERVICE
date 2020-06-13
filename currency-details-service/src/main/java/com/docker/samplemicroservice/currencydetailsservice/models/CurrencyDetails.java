package com.docker.samplemicroservice.currencydetailsservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDetails {
	@Id
	private Long id;

	@Column(name="country")
	private String country;
	
	@Column(name="currency")
	private String CountryCurrency;

}
