package com.absontheweb.pizza5.controller;

import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.Specification
import com.absontheweb.pizza5.service.MenuService
import com.absontheweb.pizza5.controller.MenuController
import com.absontheweb.pizza5.model.Pizza


class MenuControllerSpec extends Specification {

	def margherita = new Pizza(id:1, name:'Margherita', price: 4.5, description:'Classico senzatempo', ingredients:['farina','pomodoro','mozzarella'])
	def napoli = new Pizza(id:2, name:'Napoli', price: 5.5, description:'Semplice e saporita', ingredients:['farina','pomodoro','mozzarella','acciughe'])

	def menuService = Mock(MenuService) 
	
	MenuController menuCtrl = new MenuController();
	MockMvc mockMvc = standaloneSetup(menuCtrl).build()
	
	def setup() {
        menuCtrl.menuService = menuService
    }
    
    def "get the Menu"() {
    
    	when: 'REST /menu URL is hit'
    		def response = mockMvc.perform(get("/api/menu")).andReturn().response
        	
        	
        then: 'the menu is returned'
        
        	1 * menuService.getMenu() >> [margherita, napoli]
        	
        	println response
	    	response.status == OK.value()
	    	response.contentType == 'application/json'
	    	
	    	def menu = new JsonSlurper().parseText(response.contentAsString)
	    	menu.size() == 2
	    	menu[0].id == 1
	    	menu[0].name == 'Margherita'
	    	menu[0].description == 'Classico senzatempo'
	    	menu[0].ingredients.size() == 3
	    	menu[0].ingredients.containsAll(['farina','pomodoro','mozzarella'])
	    	
	   		menu[1].id == 2
	    	menu[1].name == 'Napoli'
	    	menu[1].description == 'Semplice e saporita'
	    	menu[1].ingredients.size() == 4
	    	menu[1].ingredients.containsAll(['farina','pomodoro','mozzarella','acciughe'])
    } 
    
    def "get the details of a Pizza"() {
    
    	given: 'A pizza Id'
    		Integer id = 1
        
        when: 'REST /menu/pizza/:id URL is hit'
        	def response = mockMvc.perform(get("/api/menu/pizza/${id}")).andReturn().response
        	
        
        then: 'a Pizza is returned'
        
        	1 * menuService.getPizza(id) >> margherita
        	
        	println response
	    	response.status == OK.value()
	    	response.contentType == 'application/json'
	        
	        def pizza = new JsonSlurper().parseText(response.contentAsString)        
	    	pizza.id == 1
	    	pizza.name == 'Margherita'
	    	pizza.price == 4.5
	    	pizza.description == 'Classico senzatempo'
			pizza.ingredients.size() == 3
	    	pizza.ingredients.containsAll(['farina','pomodoro','mozzarella'])
    }

}
