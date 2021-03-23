package com.cococafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cococafe.model.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Integer>{

	@Query("Select t from Topping t where t.toppingName = ?1")
	Optional<Topping> findByName(String toppingName);

}
