package fr.treeptik.veterinaireweb.service;

import java.util.List;

import fr.treeptik.veterinaireweb.model.Animal;

public interface AnimalService {
	Animal save(Animal animal);
	void delete(Animal animal);
	List<Animal> findAll();
	Animal findById(Integer id);
}
