package fr.treeptik.veterinaireweb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.veterinaireweb.model.Animal;

public interface AnimalDAO extends JpaRepository<Animal, Integer>{
	
	List<Animal> findAll() throws DataAccessException;
}
