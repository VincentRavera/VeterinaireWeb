package fr.treeptik.veterinaireweb.service;

import java.util.List;

import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.model.Client;

public interface ClientService {
	
	Client save(Client personne);
	void delete(Integer id);
	Client findById(Integer id);
	List<Client> findByName(String name);
	List<Client> findAllOrderByNameDesc();
	List<String> findTelephones(Client client);
	List<Client> findAll();
	Client addNumber(Client client);
	Adresse findAdresse(Integer id);
	

}
