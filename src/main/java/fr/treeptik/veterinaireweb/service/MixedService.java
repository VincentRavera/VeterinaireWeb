package fr.treeptik.veterinaireweb.service;

import fr.treeptik.veterinaireweb.model.Client;

public interface MixedService {
	Client saveClientWithAdresse(Client client);
	Client saveClientWithAnimals(Client client);

}
