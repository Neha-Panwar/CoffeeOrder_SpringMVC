package com.cococafe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@IdClass(BeverageId.class)
@Table(name = "beverages")
public class Beverage {
	
	@Id
	private String beverageName;
	
	@Id
	private BeverageSize beverageSize;
	
	private double beveragePrice;
	
	@OneToMany(mappedBy = "beverage",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CoffeeOrder> coffeeOrders;

}
