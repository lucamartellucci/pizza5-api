package com.absontheweb.workshop.service;

import java.util.List;

import com.absontheweb.workshop.model.Pizza;
import com.absontheweb.workshop.service.exception.MenuServiceException;

public interface MenuService {
	
	public List<Pizza> getMenu() throws MenuServiceException;

	public Pizza getPizza(Long id)  throws MenuServiceException;

}
