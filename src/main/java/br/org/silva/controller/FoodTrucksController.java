package br.org.silva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.silva.exception.MissingFieldException;
import br.org.silva.model.FoodTruck;
import br.org.silva.service.FoodTrucksService;
import br.org.silva.vo.ErrorResponse;

@CrossOrigin(origins = {"http://localhost:8080", "http://e0618828.ngrok.io"})
@RestController
@RequestMapping("/food_trucks")
public class FoodTrucksController {

	@Autowired
	private FoodTrucksService foodTrucksService; 
	
	@PostMapping
	public ResponseEntity<FoodTruck> createFoodTruck(@RequestBody FoodTruck requestFoodTruck) throws IllegalArgumentException, IllegalAccessException, MissingFieldException{
		return new ResponseEntity<FoodTruck>( foodTrucksService.createFoodTruck(requestFoodTruck), HttpStatus.OK);
	}
	
	@PutMapping
	public void update(@RequestBody FoodTruck requestFoodTruck) throws IllegalArgumentException, IllegalAccessException, MissingFieldException{
		foodTrucksService.createFoodTruck(requestFoodTruck);
	}
	
	@ExceptionHandler(MissingFieldException.class)
	public ResponseEntity<ErrorResponse> missingFieldExceptionHandler(MissingFieldException ex){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
	}

}
