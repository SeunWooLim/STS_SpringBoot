package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalancePeriodDto {
	private String id;
	private String name;
	private java.math.BigInteger balance; 
}
