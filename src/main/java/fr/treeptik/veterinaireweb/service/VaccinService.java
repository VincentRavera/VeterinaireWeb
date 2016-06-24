package fr.treeptik.veterinaireweb.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import fr.treeptik.veterinaireweb.model.Vaccin;

public interface VaccinService {
	Vaccin save(Vaccin vaccin);
	void delete(Vaccin vaccin);
	List<Vaccin> findAll();
	Vaccin findById(Integer id);
	List<Date> findHistory(Integer id);
	Vaccin addHisto(Vaccin vaccin) throws ParseException;

}
