package fr.treeptik.veterinaireweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.veterinaireweb.dao.AdresseDAO;
import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.service.AdresseService;

public class AdresseServiceImpl implements AdresseService{
	
	private AdresseDAO adresseDAO;

	public void setAdresseDAO(AdresseDAO adresseDAO) {
		this.adresseDAO = adresseDAO;
	}

	@Override
	@Transactional
	public Adresse save(Adresse adresse) {
		return adresseDAO.save(adresse);
		
	}
	@Override
	@Transactional
	public void delete(Adresse adresse) {
		adresseDAO.delete(adresse);
		
	}

	@Override
	public Adresse findById(Integer id) {
		return adresseDAO.findOne(id);
	}

	@Override
	public List<Adresse> findAll() {
		return adresseDAO.findAll();
	}

}
