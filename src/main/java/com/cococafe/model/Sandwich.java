package com.cococafe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sandwiches")
public class Sandwich {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sandwichId;
	
	private String sandwichName;
	
	private double sandwichPrice;
	
	@OneToMany(mappedBy = "sandwich",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CoffeeOrder> coffeeOrders;

}
