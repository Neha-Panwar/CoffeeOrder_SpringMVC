package com.cococafe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cococafe.model.Sandwich;

@Repository
public interface SandwichRepository extends JpaRepository<Sandwich, Integer>{
	
	@Query("Select s from Sandwich s where s.sandwichName = ?1")
	Optional<Sandwich> findByName(String sandwichName);

}
