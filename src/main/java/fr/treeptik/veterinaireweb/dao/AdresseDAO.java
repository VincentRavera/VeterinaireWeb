package fr.treeptik.veterinaireweb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.veterinaireweb.model.Adresse;

public interface AdresseDAO extends JpaRepository<Adresse, Integer>{
	
	List<Adresse> findAll()throws DataAccessException;
	

}
