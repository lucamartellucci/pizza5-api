package com.absontheweb.pizza5.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import com.absontheweb.pizza5.entity.IngredientEntity;
import com.absontheweb.pizza5.entity.PizzaEntity;
import com.absontheweb.pizza5.model.Pizza;
import com.absontheweb.pizza5.repository.PizzaRepository;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
	
	@Mock
	private PizzaRepository pizzaRepo;
	
	@InjectMocks
	private MenuServiceImpl menuServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		reset(pizzaRepo);
	}
	
	@Test
	public void testGetMenu() throws Exception {
		
		PizzaEntity margherita = new PizzaEntity();
		margherita.setId(1L);
		margherita.setName("Margherita");
		margherita.setDescription("classico italiano");
		margherita.setIngredients(Arrays.asList(
				new IngredientEntity(1L,"tomatoes"),
				new IngredientEntity(1L,"flour"),
				new IngredientEntity(1L,"mozzarella"),
				new IngredientEntity(1L,"basil")));
		
		when(pizzaRepo.findAll(new Sort(Sort.Direction.ASC, "name"))).thenReturn(Arrays.asList(margherita));
		
		List<Pizza> menu = menuServiceImpl.getMenu();
		assertThat(menu.size(), is(1));
		Pizza pizzarita = menu.get(0);
		assertThat(pizzarita.getId(), is(1L));
		assertThat(pizzarita.getName(), is("Margherita"));
		
		verify(pizzaRepo).findAll(new Sort(Sort.Direction.ASC, "name"));
	}
	
	
}
