package br.org.silva.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.org.silva.FoodAppApplication;
import br.org.silva.constants.StatusEnum;
import br.org.silva.model.FoodTruck;
import br.org.silva.repository.FoodTrucksRepository;
import br.org.silva.vo.ErrorResponse;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=FoodAppApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestFoodTrucksController {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private FoodTrucksRepository foodTrucksRepository; 
	
	@Test @Transactional
	public void foodTruckMustHaveAName(){
		FoodTruck foodTruck = new FoodTruck();
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/food_trucks", foodTruck, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void foodTruckMustHaveAStatus(){
		FoodTruck foodTruck = new FoodTruck();
		foodTruck.setName("Ariba ! Comida Mexicana");
		foodTruck.setStatus(null);
		
		ResponseEntity<ErrorResponse> response = testRestTemplate.postForEntity("/food_trucks", foodTruck, ErrorResponse.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
		assertNotNull(response.getBody());
	}
	
	@Test @Transactional
	public void foodTruckMustBePersisted(){
		FoodTruck foodTruck = new FoodTruck();
		foodTruck.setName("Ariba ! Comida Mexicana");
		
		ResponseEntity<FoodTruck> response = testRestTemplate.postForEntity("/food_trucks", foodTruck, FoodTruck.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertNotNull(response.getBody().getId());
		assertNotNull(response.getBody().getStatus());
		assertTrue(response.getBody().getStatus().equals(StatusEnum.ENABLED));
	}
	
	@Test @Transactional
	public void foodTruckMustBeUpdated(){
		FoodTruck foodTruck = new FoodTruck();
		foodTruck.setName("Ariba ! Comida Mexicana");
		
		ResponseEntity<FoodTruck> response = testRestTemplate.postForEntity("/food_trucks", foodTruck, FoodTruck.class);
		
		Long id = response.getBody().getId();
		
		response.getBody().setStatus(StatusEnum.DISABLED);
		
		testRestTemplate.put("/food_trucks", response.getBody());
		
		assertFalse(foodTrucksRepository.findOne(id).getStatus().equals(StatusEnum.ENABLED));
	}

}
