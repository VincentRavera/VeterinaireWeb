package fr.treeptik.veterinaireweb.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.model.Animal;
import fr.treeptik.veterinaireweb.model.Client;
import fr.treeptik.veterinaireweb.service.AdresseService;
import fr.treeptik.veterinaireweb.service.AnimalService;
import fr.treeptik.veterinaireweb.service.ClientService;
import fr.treeptik.veterinaireweb.service.MixedService;

public class MixedServiceImpl implements MixedService{

	@Autowired
	private ClientService clientService;
	@Autowired
	private AdresseService adresseService;
	@Autowired
	private AnimalService animalService;
	
	private Logger logger = LoggerFactory.getLogger(MixedServiceImpl.class);
	
	
	
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setAdresseService(AdresseService adresseService) {
		this.adresseService = adresseService;
	}

	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}

	@Override
	@Transactional
	public Client saveClientWithAdresse(Client client) {
		if(client.getId() == null){
			logger.info("MixedService: Client don't have id");
			logger.info("MixedService: Hazardous output");
		}
		if(client.getAdresse() != null){
			logger.debug("MixedService: All FINE !!!!!!!!!!!!");
			Adresse adresse = client.getAdresse();
			adresse.setClient(client);
			clientService.save(client);
			adresseService.save(adresse);
			return client;
		}
		logger.warn("MixedService: Client don't have Adresse to save");
		logger.warn("MixedService: Null Pointer Exception Expected !!!");
		return null;
	}

	@Override
	@Transactional
	public Client saveClientWithAnimals(Client client) {
		if(client.getAnimal() == null || client.getAnimal().isEmpty()){
			logger.warn("MixedService: Client don't have Animals to save");
			logger.warn("MixedService: Null Pointer Exception Expected !!!");
			return null;
		}
		if(client.getId() == null){
			logger.info("MixedService: Client don't have id");
			logger.info("MixedService: Hazardous output");
		}
		List<Animal> animals = client.getAnimal();
		clientService.save(client);
		for (Animal animal : animals) {
			animal.setMaitre(client);
			animalService.save(animal);
		}
		return client;
	}

}
