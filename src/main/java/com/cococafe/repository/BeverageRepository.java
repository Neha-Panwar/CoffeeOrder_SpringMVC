package com.cococafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cococafe.model.Beverage;
import com.cococafe.model.BeverageId;
import com.cococafe.model.BeverageSize;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, BeverageId> {
	
	@Query("Select b.beveragePrice from Beverage b where b.beverageName = ?1 and b.beverageSize = ?2")
	Optional<Double> findPriceByType(String beverageName, BeverageSize beverageSize);

}
