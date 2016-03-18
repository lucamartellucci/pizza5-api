package com.absontheweb.pizza5.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.absontheweb.pizza5.model.Pizza;
import com.absontheweb.pizza5.service.MenuService;

@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { ControllerTestConfig.class } )
@WebAppConfiguration
public class MenuControllerTest {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetPizza() throws Exception {
		
		Pizza pizza = new Pizza();
		pizza.setId(1L);
		pizza.setName("Margherita");
		pizza.setPrice(4.5);
		pizza.setDescription("Classico senzatempo");
		pizza.setIngredients(Arrays.asList("farina","pomodoro","mozzarella"));
		
		when(menuService.getPizza(1L)).thenReturn(pizza);
		
		
		this.mockMvc.perform( get( "/api/menu/pizza/1" ).accept( MediaType.parseMediaType( "application/json;charset=UTF-8" ) ) )
			.andDo( print() )
			.andExpect( status().isOk() )
	        .andExpect( content().contentType( "application/json;charset=UTF-8" ) )
	        .andExpect( jsonPath( "$.name").value("Margherita"))
	        .andExpect( jsonPath( "$.ingredients[0]" ).value( "farina" ) )
			.andExpect( jsonPath( "$.ingredients[1]" ).value( "pomodoro" ) )
			.andExpect( jsonPath( "$.ingredients[2]" ).value( "mozzarella" ) );
		
		verify(menuService).getPizza(1L);
	}

}
