package com.cococafe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cococafe.exception.OrderNotFoundException;
import com.cococafe.model.Beverage;
import com.cococafe.model.BeverageId;
import com.cococafe.model.BeverageSize;
import com.cococafe.model.CoffeeOrder;
import com.cococafe.model.OrderForm;
import com.cococafe.model.OrderStatus;
import com.cococafe.model.Sandwich;
import com.cococafe.model.Topping;
import com.cococafe.repository.BeverageRepository;
import com.cococafe.repository.CoffeeOrderRepository;
import com.cococafe.repository.SandwichRepository;
import com.cococafe.repository.ToppingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

	@Autowired
	CoffeeOrderRepository orderRepo;
	
	@Autowired
	BeverageRepository beverageRepo;
	
	@Autowired
	ToppingRepository toppingRepo;
	
	@Autowired
	SandwichRepository sandwichRepo;
	
	
	@Override
	public List<String> getAllBeverageNames() {
		
		log.info("[loaded Beverage Names]");
		return beverageRepo.findAll().stream().map(Beverage::getBeverageName).distinct().collect(Collectors.toList());
	}
	
	@Override
	public List<String> getAllToppingNames() {
		
		log.info("[loaded Topping Names]");
		return toppingRepo.findAll().stream().map(Topping::getToppingName).distinct().collect(Collectors.toList());
	}
	
	@Override
	public List<String> getAllSandwichNames() {
		
		log.info("[loaded Sandwich Names]");
		return sandwichRepo.findAll().stream().map(Sandwich::getSandwichName).distinct().collect(Collectors.toList());
	}
	
	@Override
	public CoffeeOrder createOrder(OrderForm order) {
		
		String bevName = order.getBeverageName();
		BeverageSize bevSize = order.getBeverageSize();
		String topping = order.getToppingName();
		String sandwich = order.getSandwichName();
		
		double beveragePrice = beverageRepo.findPriceByType(bevName, bevSize).orElse(0d);
		Optional<Beverage> bev = beverageRepo.findById(new BeverageId(bevName, bevSize));
		Optional<Topping> top = toppingRepo.findByName(topping);
		Optional<Sandwich> sand = sandwichRepo.findByName(sandwich);
		double toppingPrice = 0d;
		if(top.isPresent())  toppingPrice = top.get().getToppingPrice();
		double sandwichPrice = 0d;
		if(sand.isPresent())  sandwichPrice = sand.get().getSandwichPrice();
		double orderCost = beveragePrice + toppingPrice + sandwichPrice;
		
		CoffeeOrder coffeeOrder = new CoffeeOrder();
		coffeeOrder.setBeverage(bev.get());
		coffeeOrder.setTopping(top.orElse(null));
		coffeeOrder.setSandwich(sand.orElse(null));
		coffeeOrder.setOrderCost(orderCost);
		coffeeOrder.setOrderStatus(order.getStatus());
		
		if(order.getStatus()==OrderStatus.PENDING)
			log.info("[Order created, status=PENDING]");
		
		else
			log.info("[Order placed successfully, status=PLACED]");
		
		log.info("BeverageName:"+order.getBeverageName()+", BeverageSize:"+order.getBeverageSize()+", Topping:"+order.getToppingName()+", Sandwich:" + order.getSandwichName()+", OrderCost:"+orderCost);
		return orderRepo.save(coffeeOrder);
	}

	@Override
	public CoffeeOrder placeOrder(int orderId) throws OrderNotFoundException{
		
		CoffeeOrder order = orderRepo.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order with id: "+orderId+" not found"));
		
		if(order.getOrderStatus()==OrderStatus.PLACED)
			log.info("OrderId:"+orderId+" already placed, cannot place it again.");
		
		else {
			order.setOrderStatus(OrderStatus.PLACED);
			
			order = orderRepo.save(order);
			log.info("[OrderId:"+orderId+" placed successfully, status=PLACED]");
			log.info("BeverageName:"+order.getBeverage().getBeverageName()+", BeverageSize:"+order.getBeverage().getBeverageSize()+", Topping:"+ (order.getTopping()!=null?order.getTopping().getToppingName():"None")+", Sandwich:" + (order.getSandwich()!=null?order.getSandwich().getSandwichName():"None")+", OrderCost:"+order.getOrderCost());
		
		}
		return order;
		
	}
	
	@Override
	public List<CoffeeOrder> getPlacedOrders() {
		
		log.info("[Fetched all placed orders]");
		return orderRepo.findOrders(OrderStatus.PLACED);
	}
	
	@Override
	public List<CoffeeOrder> getPendingOrders() {

		log.info("[Fetched all pending orders]");
		return orderRepo.findOrders(OrderStatus.PENDING);
	}
	
	@Override
	public OrderForm getOrderDetail(int orderId) throws OrderNotFoundException {
		
		CoffeeOrder orderDetail = orderRepo.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order with id: "+orderId+" not found"));
		
		
		log.info("[Fetched Order Detail : orderId "+orderId+"]");
		OrderForm orderForm = new OrderForm();
		orderForm.setBeverageName(orderDetail.getBeverage().getBeverageName());
		orderForm.setBeverageSize(orderDetail.getBeverage().getBeverageSize());
		log.info("BeverageName: "+orderDetail.getBeverage().getBeverageName());
		log.info("BeverageSize: "+orderDetail.getBeverage().getBeverageSize().toString());
		
		if(orderDetail.getTopping()!=null) {
			log.info("Topping: "+orderDetail.getTopping().getToppingName());
			orderForm.setToppingName(orderDetail.getTopping().getToppingName());
		}
		else {
			log.info("Topping: "+"None");
			orderForm.setToppingName("None");
		}
			
		if(orderDetail.getSandwich()!=null) {
			log.info("Sandwich: "+orderDetail.getSandwich().getSandwichName());
			orderForm.setSandwichName(orderDetail.getSandwich().getSandwichName());
		}
		else {
			log.info("Sandwich: "+"None");
			orderForm.setSandwichName("None");
		}
		
		return orderForm;
	}
		
	
	
	
	@Override
	public List<CoffeeOrder> viewAllOrders() {
		
		log.info("[Fetched all orders]");
		return orderRepo.findAll();
	}

	
	@Override
	public CoffeeOrder updateOrder(int orderId, OrderForm orderForm) throws OrderNotFoundException {
		
		CoffeeOrder coffeeOrder = orderRepo.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order with id: "+orderId+" not found"));
		
		if(coffeeOrder.getOrderStatus()==OrderStatus.PLACED)
			log.info("[OrderId:"+orderId+" cannot be updated, Order already placed]");
		
		else {
			String bevName = orderForm.getBeverageName();
			BeverageSize bevSize = orderForm.getBeverageSize();
			String topping = orderForm.getToppingName();
			String sandwich = orderForm.getSandwichName();
			
			double beveragePrice = beverageRepo.findPriceByType(bevName, bevSize).orElse(0d);
			Optional<Beverage> bev = beverageRepo.findById(new BeverageId(bevName, bevSize));
			Optional<Topping> top = toppingRepo.findByName(topping);
			Optional<Sandwich> sand = sandwichRepo.findByName(sandwich);
			double toppingPrice = 0d;
			if(top.isPresent())  toppingPrice = top.get().getToppingPrice();
			double sandwichPrice = 0d;
			if(sand.isPresent())  sandwichPrice = sand.get().getSandwichPrice();
			double orderCost = beveragePrice + toppingPrice + sandwichPrice;
			
			coffeeOrder.setBeverage(bev.get());
			coffeeOrder.setTopping(top.orElse(null));
			coffeeOrder.setSandwich(sand.orElse(null));
			coffeeOrder.setOrderCost(orderCost);
			
			coffeeOrder = orderRepo.save(coffeeOrder);
			
			log.info("[OrderId: "+orderId+" Updated Successfully]");
			log.info("BeverageName:"+bevName+", BeverageSize:"+bevSize+", Topping:"+topping+", Sandwich:" + sandwich+", OrderCost:"+orderCost);
		}
		return coffeeOrder;
		
	}

	@Override
	public String deleteOrder(int orderId) throws OrderNotFoundException {		
		
		CoffeeOrder coffeeOrder = orderRepo.findById(orderId).orElseThrow(() ->new OrderNotFoundException("Order with id: "+orderId+" not found"));
		
		String msg = "";
		if(coffeeOrder.getOrderStatus()==OrderStatus.PLACED)
			msg = "Order cannot be deleted, Order already placed";
		else {
			msg = "Order Deleted Successfully";
			orderRepo.deleteById(orderId);
		}
		log.info("[OrderId:"+orderId+" - "+msg+"]");
		return msg;
	}


	

	

}
