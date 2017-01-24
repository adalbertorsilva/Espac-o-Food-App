package br.org.silva.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.silva.model.FoodTruck;

public interface FoodTrucksRepository extends CrudRepository<FoodTruck, Long> {

}
