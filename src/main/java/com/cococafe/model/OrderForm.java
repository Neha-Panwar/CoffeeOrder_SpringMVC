package com.cococafe.model;

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

public class OrderForm {

	
	private String beverageName;
	private BeverageSize beverageSize;
	private String toppingName;
	private String sandwichName;
	private OrderStatus status;
	
}