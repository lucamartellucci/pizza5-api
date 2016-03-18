package com.absontheweb.workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.absontheweb.workshop.model.Pizza;
import com.absontheweb.workshop.service.MenuService;

@RestController
@RequestMapping("/api")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value = "/menu", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pizza>> getMenu() throws Exception {
		try {
			
			return ResponseEntity.ok()
					.header("Access-Control-Allow-Origin", "*")
					.body(menuService.getMenu());
			
		} catch (Exception e) {
			throw new Exception("Unable to load the menu", e);
		}
	}
	
	
	@RequestMapping(value = "/menu/pizza/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pizza> getPizza(@PathVariable(value="id") Long id) throws Exception {
		try {

			return ResponseEntity.ok()
					.header("Access-Control-Allow-Origin", "*")
					.body(menuService.getPizza(id));

		} catch (Exception e) {
			throw new Exception("Unable to load the menu", e);
		}
	}
	
}
