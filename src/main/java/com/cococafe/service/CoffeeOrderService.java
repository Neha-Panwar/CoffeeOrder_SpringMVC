package com.cococafe.service;

import java.util.List;

import com.cococafe.exception.OrderNotFoundException;
import com.cococafe.model.CoffeeOrder;
import com.cococafe.model.OrderForm;

public interface CoffeeOrderService {

	public List<String> getAllBeverageNames();
	
	public List<String> getAllSandwichNames();
	
	public List<String> getAllToppingNames();
	
	public CoffeeOrder createOrder(OrderForm orderform);
	
	public List<CoffeeOrder> getPlacedOrders();
	
	public List<CoffeeOrder> getPendingOrders();
	
	public List<CoffeeOrder> viewAllOrders();
	
	public CoffeeOrder updateOrder(int orderId, OrderForm orderForm) throws OrderNotFoundException;
	
	public String deleteOrder(int orderId) throws OrderNotFoundException;

	public CoffeeOrder placeOrder(int orderId) throws OrderNotFoundException;

	public OrderForm getOrderDetail(int orderId) throws OrderNotFoundException;
	
	

}
