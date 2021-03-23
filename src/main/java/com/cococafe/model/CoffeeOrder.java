package com.cococafe.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class CoffeeOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="beverage_name"),
		@JoinColumn(name="beverage_size")
	})
	private Beverage beverage;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="topping_id")
	private Topping topping;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sandwich_id")
	private Sandwich sandwich;
	
	private double orderCost;
	
	private OrderStatus orderStatus;
}

