package com.absontheweb.workshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.absontheweb.workshop.entity.PizzaEntity;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
	
	public PizzaEntity findByName(String name);
	
	public List<PizzaEntity> findByPriceOrderByNameAsc(Double price);

}
