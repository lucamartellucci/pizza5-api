package com.absontheweb.pizza5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.absontheweb.pizza5.entity.PizzaEntity;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
	
	public PizzaEntity findByName(String name);
	
	public List<PizzaEntity> findByPriceOrderByNameAsc(Double price);

}
