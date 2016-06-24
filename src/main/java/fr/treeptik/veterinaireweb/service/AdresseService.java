package fr.treeptik.veterinaireweb.service;

import java.util.List;

import fr.treeptik.veterinaireweb.model.Adresse;

public interface AdresseService {

	Adresse save(Adresse adresse);
	void delete(Adresse adresse);
	Adresse findById(Integer id);
	List<Adresse> findAll();
}
