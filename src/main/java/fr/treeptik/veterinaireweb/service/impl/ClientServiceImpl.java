package fr.treeptik.veterinaireweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.veterinaireweb.dao.ClientDAO;
import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.model.Client;
import fr.treeptik.veterinaireweb.service.ClientService;

public class ClientServiceImpl implements ClientService {
	
	private ClientDAO clientDAO;

	public ClientDAO getClientDAO() {
		return clientDAO;
	}
	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Override
	@Transactional
	public Client save(Client client) {
		client = clientDAO.save(client);
		return client;
	}
	@Override
	public Client findById(Integer id) {	
		return clientDAO.findOne(id);
	}
	@Override
	@Transactional
	public void delete(Integer id) {
		Client client = this.findById(id);
		clientDAO.delete(client);
		
	}
	@Override
	public List<Client> findByName(String name) {
		List<Client> clients = clientDAO.findByNom(name);
		return clients;
	}
	@Override
	public List<Client> findAllOrderByNameDesc() {
		return clientDAO.findAllOrderByNomDesc();
	}
	@Override
	public List<Client> findAll() {
		return clientDAO.findAll();
	}
	@Override
	public List<String> findTelephones(Client client) {
		return clientDAO.findTelephone(client.getId());
	}
	@Override
	public Client addNumber(Client client) {
		List<String> list = clientDAO.findTelephone(client.getId());
		list.add(client.getTelAAjouter());
		client.setTelAAjouter(null);
		client.setTelephone(list);
		return client;
	}
	@Override
	public Adresse findAdresse(Integer id) {
		return clientDAO.findAdresse(id);
	}
	
	

}
