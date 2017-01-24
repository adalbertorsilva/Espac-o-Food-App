package br.org.silva.service;



import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.silva.exception.MissingFieldException;
import br.org.silva.model.FoodTruck;
import br.org.silva.repository.FoodTrucksRepository;
import br.org.silva.utils.Utils;

@Service
public class FoodTrucksService {

	@Autowired
	private FoodTrucksRepository foodTrucksRepository;

	public FoodTruck createFoodTruck(FoodTruck requestFoodTruck) throws IllegalArgumentException, IllegalAccessException, MissingFieldException {
		validateFields(requestFoodTruck);
		return foodTrucksRepository.save(requestFoodTruck);
	}
	
	private void validateFields(FoodTruck requestFoodTruck) throws MissingFieldException, IllegalArgumentException, IllegalAccessException{
		for(Field field : requestFoodTruck.getClass().getDeclaredFields()){
			if(!field.getName().equalsIgnoreCase("id") && !field.getName().equalsIgnoreCase("popularName") && !field.getName().equalsIgnoreCase("urlreflora") && !field.getName().equalsIgnoreCase("objectversion")) {
				validateField(requestFoodTruck, field);
			}
		}
	}
	
	private void validateField(FoodTruck requestFoodTruck, Field field) throws MissingFieldException, IllegalArgumentException, IllegalAccessException{
		
		field.setAccessible(true);
		
		if(field.getType().equals(String.class) && (Utils.isNull(field.get(requestFoodTruck)) || ((String)field.get(requestFoodTruck)).isEmpty())){
			throw new MissingFieldException(field);
		}
		
//		if(field.getType().equals(List.class) && (Utils.isNull(field.get(requestFoodTruck)) || ((List)field.get(requestFoodTruck)).isEmpty())){
//			throw new MissingFieldException(field);
//		}
		
		if(Utils.isNull(field.get(requestFoodTruck))){
			throw new MissingFieldException(field);
		}
	}

	
	
}
