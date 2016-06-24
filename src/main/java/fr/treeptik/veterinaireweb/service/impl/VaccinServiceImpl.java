package fr.treeptik.veterinaireweb.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.treeptik.veterinaireweb.dao.VaccinDAO;
import fr.treeptik.veterinaireweb.model.Vaccin;
import fr.treeptik.veterinaireweb.service.VaccinService;

public class VaccinServiceImpl implements VaccinService{
	
	private VaccinDAO vaccinDAO;

	public void setVaccinDAO(VaccinDAO vaccinDAO) {
		this.vaccinDAO = vaccinDAO;
	}

	@Override
	public Vaccin save(Vaccin vaccin) {
		return vaccinDAO.save(vaccin);
	}

	@Override
	public void delete(Vaccin vaccin) {
		vaccinDAO.delete(vaccin);
		
	}

	@Override
	public List<Vaccin> findAll() {
		return vaccinDAO.findAll();
	}

	@Override
	public Vaccin findById(Integer id) {
		return vaccinDAO.findOne(id);
	}

	@Override
	public List<Date> findHistory(Integer id) {
		return vaccinDAO.findHistory(id);
	}

	@Override
	public Vaccin addHisto(Vaccin vaccin) throws ParseException {
		List<Date> dates = vaccin.getHistorique();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
		Date date = dateFormat.parse(vaccin.getProchain());
		
		dates.add(date);
		
		vaccin.setHistorique(dates);
		vaccin.setProchain(null);
		return vaccin;
	}

}
