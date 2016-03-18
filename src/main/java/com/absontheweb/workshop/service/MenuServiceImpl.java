package com.absontheweb.workshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.absontheweb.workshop.entity.IngredientEntity;
import com.absontheweb.workshop.entity.PizzaEntity;
import com.absontheweb.workshop.model.Pizza;
import com.absontheweb.workshop.repository.PizzaRepository;
import com.absontheweb.workshop.service.exception.MenuServiceException;

@Service
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	PizzaRepository pizzaRepo;
	
	@Override
	public List<Pizza> getMenu() throws MenuServiceException {
		List<PizzaEntity> pizzaEntities = pizzaRepo.findAll(new Sort(Sort.Direction.ASC, "name") );
		return toPizzas(pizzaEntities);
	}

	@Override
	public Pizza getPizza(Long id) throws MenuServiceException {
		return toPizza(pizzaRepo.getOne(id));
	}

	private List<Pizza> toPizzas(List<PizzaEntity> pizzaEntities) {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		for (PizzaEntity pizzaEntity : pizzaEntities) {
			pizzas.add(toPizza(pizzaEntity));
		}
		return pizzas;
	}

	private Pizza toPizza(PizzaEntity pizzaEntity) {
		Pizza pizza = new Pizza();
		BeanUtils.copyProperties(pizzaEntity, pizza, "ingredients");
		
		List<IngredientEntity> ingredients = pizzaEntity.getIngredients();
		if (ingredients != null) {
			pizza.setIngredients(new ArrayList<>());
			for (IngredientEntity ingredientEntity : ingredients) {
				pizza.getIngredients().add(ingredientEntity.getName());
			}
		}
		return pizza;
	}


}
