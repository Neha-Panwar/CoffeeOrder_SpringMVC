package com.cococafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cococafe.model.CoffeeOrder;
import com.cococafe.model.OrderStatus;

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {
	
	@Query("Select c from CoffeeOrder c where c.orderStatus = ?1")
	List<CoffeeOrder> findOrders(OrderStatus status);


}
