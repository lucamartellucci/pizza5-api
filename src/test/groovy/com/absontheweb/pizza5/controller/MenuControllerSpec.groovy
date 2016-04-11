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
        	
	    	response.status == OK.value()
	    	response.contentType == 'application/json'
	    	
	    	def menu = new JsonSlurper().parseText(response.contentAsString)
	    	menu.size() == 2
	    	with(menu[0]){
	    		id == 1
		    	name == 'Margherita'
		    	description == 'Classico senzatempo'
		    	ingredients.size() == 3
		    	ingredients.containsAll(['farina','pomodoro','mozzarella'])
	    	}
	    	
	    	with(menu[1]){
	    		id == 2
		    	name == 'Napoli'
		    	description == 'Semplice e saporita'
		    	ingredients.size() == 4
		    	ingredients.containsAll(['farina','pomodoro','mozzarella','acciughe'])
	    	}
	   		
    } 
    
    def "get the details of a Pizza"() {
    
    	given: 'A pizza Id'
    		Integer id = 1
        
        when: 'REST /menu/pizza/:id URL is hit'
        	def response = mockMvc.perform(get("/api/menu/pizza/${id}")).andReturn().response
        	
        
        then: 'a Pizza is returned'
        	// program and verify the mock in a signle line
        	1 * menuService.getPizza(id) >> margherita
        	
        	// verify with assertions
	    	response.status == OK.value()
	    	response.contentType == 'application/json'
	        
	        def pizza = new JsonSlurper().parseText(response.contentAsString)        
	        with(pizza){
	        	id == 1
		    	name == 'Margherita'
		    	price == 4.5
		    	description == 'Classico senzatempo'
				ingredients.size() == 3
		    	ingredients.containsAll(['farina','pomodoro','mozzarella'])
	        }
    }

}
