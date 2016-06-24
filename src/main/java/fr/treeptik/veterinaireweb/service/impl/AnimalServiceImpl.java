package fr.treeptik.veterinaireweb.service.impl;

import java.util.List;

import fr.treeptik.veterinaireweb.dao.AnimalDAO;
import fr.treeptik.veterinaireweb.model.Animal;
import fr.treeptik.veterinaireweb.service.AnimalService;

public class AnimalServiceImpl implements AnimalService{
	
	private AnimalDAO animalDAO;

	public void setAnimalDAO(AnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}

	@Override
	public Animal save(Animal animal) {
		return animalDAO.save(animal);
	}

	@Override
	public void delete(Animal animal) {
		animalDAO.delete(animal);
	}

	@Override
	public List<Animal> findAll() {
		return animalDAO.findAll();
	}

	@Override
	public Animal findById(Integer id) {
		return animalDAO.findOne(id);
	}

}
