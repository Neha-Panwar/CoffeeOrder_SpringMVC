package com.cococafe.controller;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cococafe.exception.OrderNotFoundException;
import com.cococafe.model.BeverageSize;
import com.cococafe.model.CoffeeOrder;
import com.cococafe.model.OrderForm;
import com.cococafe.model.OrderStatus;
import com.cococafe.service.CoffeeOrderService;


@Controller
@SessionAttributes("name")
public class CoffeeOrderController {
	
	@Autowired
	CoffeeOrderService service;


	@GetMapping(value = "/createOrder")
	public String showCoffeeOrderForm(@ModelAttribute("orderForm")OrderForm orderForm){
		
		return "user-order-form";
	}
	
	@PostMapping(value = "/createOrder")
	public String createCoffeeOrder(@ModelAttribute("orderForm")OrderForm orderForm, Model model, RedirectAttributes redirAttrs){
		
		service.createOrder(orderForm);
		
		String msg="";
		if(orderForm.getStatus()==OrderStatus.PENDING)
			msg="Saved order successfully!! Pending for review.";
		else
			msg="Order Placed Successfully!!";

		redirAttrs.addFlashAttribute("successMsg",msg);
		return "redirect:/createOrder";
	}
	
	
	@GetMapping(value = "/reviewOrder")
	public ModelAndView reviewCoffeeOrder() {
		List<CoffeeOrder> orderList = service.getPendingOrders();
		ModelAndView model = new ModelAndView("review-coffee-orders");
		model.addObject("orders", orderList);
		return model;
	}
	
	@GetMapping(value = "/confirmOrder/{orderId}")
	public String confirmCoffeeOrder(@PathVariable int orderId, RedirectAttributes redirAttrs) throws OrderNotFoundException{
	    
		service.placeOrder(orderId);
		
		redirAttrs.addFlashAttribute("successMsg","Order Placed Successfully!!");
		return "redirect:/reviewOrder";
	}
	

	@GetMapping(value = "/deleteOrder/{orderId}")
	public String deleteCoffeeOrder(@PathVariable int orderId, RedirectAttributes redirAttrs) throws OrderNotFoundException{
		
		String msg = service.deleteOrder(orderId);
		
		redirAttrs.addFlashAttribute("successMsg",msg);
		return "redirect:/reviewOrder";
	}
	
	@GetMapping(value = "/updateOrder")
	public String showUpdateOrderPage(@RequestParam int orderId, ModelMap model) throws OrderNotFoundException{
		
		OrderForm orderForm = service.getOrderDetail(orderId);
		
		model.put("orderForm", orderForm);
		return "user-order-form";

	}
	
	@PostMapping(value = "/updateOrder")
	public String updateOrderDetail(@RequestParam int orderId, @ModelAttribute("orderForm")OrderForm orderForm, RedirectAttributes redirAttrs) throws OrderNotFoundException{
		
		service.updateOrder(orderId, orderForm);
		
		redirAttrs.addFlashAttribute("successMsg","Order Updated!!");
		return "redirect:/reviewOrder";
	}
	
	@GetMapping(value = "/orderHistory")
	public ModelAndView showOrderHistory(){
		
		List<CoffeeOrder> orderList = service.getPlacedOrders();
		ModelAndView model = new ModelAndView("order-history");
		model.addObject("orders", orderList);
		return model;
	}
	
	
	@ModelAttribute("beverageSizeList")
	public Set<String> populateBeverageSizeEnumList() {
		return EnumSet.allOf(BeverageSize.class).stream().map(a -> a.name()).collect(Collectors.toSet());

	}

	@ModelAttribute("beverageList")
	public List<String> populateBeverageList() {
		
		return service.getAllBeverageNames();

	}
	
	@ModelAttribute("toppingList")
	public List<String> populateToppingList() {
		
		List<String> toppingList = service.getAllToppingNames();
		toppingList.add("None");
		return toppingList;

	}
	
	@ModelAttribute("sandwichList")
	public List<String> populateSandwichList() {
		
		List<String> sandwichList = service.getAllSandwichNames();
		sandwichList.add("None");
		return sandwichList;
	}
}
