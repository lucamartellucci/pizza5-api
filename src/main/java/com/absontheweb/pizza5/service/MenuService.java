package com.absontheweb.pizza5.service;

import java.util.List;

import com.absontheweb.pizza5.model.Pizza;
import com.absontheweb.pizza5.service.exception.MenuServiceException;

public interface MenuService {
	
	public List<Pizza> getMenu() throws MenuServiceException;

	public Pizza getPizza(Long id)  throws MenuServiceException;

}
