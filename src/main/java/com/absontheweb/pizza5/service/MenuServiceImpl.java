package com.absontheweb.pizza5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.absontheweb.pizza5.entity.IngredientEntity;
import com.absontheweb.pizza5.entity.PizzaEntity;
import com.absontheweb.pizza5.model.Pizza;
import com.absontheweb.pizza5.repository.PizzaRepository;
import com.absontheweb.pizza5.service.exception.MenuServiceException;

@Service
@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	@Override
	public List<Pizza> getMenu() throws MenuServiceException {
		List<PizzaEntity> pizzaEntities = pizzaRepo.findAll(new Sort(Sort.Direction.ASC, "name") );
		return toPizzas(pizzaEntities);
	}

	@Override
	public Pizza getPizza(Long id) throws MenuServiceException {
		return toPizza(pizzaRepo.getOne(id));
	}

	protected List<Pizza> toPizzas(List<PizzaEntity> pizzaEntities) {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		for (PizzaEntity pizzaEntity : pizzaEntities) {
			pizzas.add(toPizza(pizzaEntity));
		}
		return pizzas;
	}

	protected Pizza toPizza(PizzaEntity pizzaEntity) {
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
