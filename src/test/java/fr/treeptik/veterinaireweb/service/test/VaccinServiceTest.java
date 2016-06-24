package fr.treeptik.veterinaireweb.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.veterinaireweb.config.ApplicationConfiguration;
import fr.treeptik.veterinaireweb.model.Vaccin;
import fr.treeptik.veterinaireweb.service.VaccinService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class})
@ActiveProfiles("test")
public class VaccinServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(VaccinServiceTest.class);
	
	@Autowired
	private VaccinService vaccinService;
	
	@Test
	@Transactional
	public void shouldSaveAndUpdateAnimal(){
		try {
			Vaccin vaccin = new Vaccin();
			vaccin.setObligatoire(true);
			vaccin.setType("popopopop");
			List<Date> historique = new ArrayList<>();
			historique.add(new Date());
			vaccin.setHistorique(historique);
			Vaccin res = vaccinService.save(vaccin);
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(vaccin.getType(), res.getType());
			Assert.assertEquals(vaccin.getObligatoire(), res.getObligatoire());
			Assert.assertEquals(vaccin.getHistorique().size(), res.getHistorique().size());
			
			vaccin.setId(res.getId());
			vaccin.setObligatoire(true);
			vaccin.setType("A");
			historique.add(new Date());
			vaccin.setHistorique(historique);
			res = vaccinService.save(vaccin);
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(vaccin.getType(), res.getType());
			Assert.assertEquals(vaccin.getObligatoire(), res.getObligatoire());
			Assert.assertEquals(vaccin.getHistorique().size(), res.getHistorique().size());
			
			
		} catch (Exception e) {
			logger.error("VaccinServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	
	@Test
	@Transactional
	public void shouldDeleteAnimal(){
		try {
			Vaccin vaccin = new Vaccin();
			vaccin.setObligatoire(true);
			vaccin.setType("popopopop");
			List<Date> historique = new ArrayList<>();
			historique.add(new Date());
			vaccin.setHistorique(historique);
			vaccin = vaccinService.save(vaccin);
			vaccinService.delete(vaccin);
			Vaccin res = vaccinService.findById(vaccin.getId());
			Assert.assertNull(res);
			
			
			
		} catch (Exception e) {
			logger.error("VaccinServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test
	@Transactional
	public void shouldFindAll(){
		try {
			Vaccin vaccin = vaccinService.save(new Vaccin());
			Vaccin vaccin1 = vaccinService.save(new Vaccin());
			Vaccin vaccin2 = vaccinService.save(new Vaccin());
			Vaccin vaccin3 = vaccinService.save(new Vaccin());
			
			List<Vaccin> lvacc = new ArrayList<>();
			lvacc.add(vaccin);
			lvacc.add(vaccin1);
			lvacc.add(vaccin2);
			lvacc.add(vaccin3);
			
			List<Vaccin> lout = vaccinService.findAll();
			
			Assert.assertNotNull(lout);
			Assert.assertEquals(lvacc.size(), lout.size());
			
			
		} catch (Exception e) {
			logger.error("VaccinServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	
	@Test
	@Transactional
	public void shouldFindById(){
		try {
			Vaccin vaccin = vaccinService.save(new Vaccin());
			vaccin = vaccinService.save(vaccin);
			Vaccin res = vaccinService.findById(vaccin.getId());
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(vaccin.getId(), res.getId());
			
		} catch (Exception e) {
			logger.error("AnimalServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	
	@Test
	@Transactional
	public void shouldFindHistory(){
		try {
			Vaccin vaccin = new Vaccin();
			List<Date> ldate = new ArrayList<>();
			ldate.add(new Date());
			ldate.add(new Date());
			ldate.add(new Date());
			ldate.add(new Date());
			vaccin.setHistorique(ldate);
			vaccin = vaccinService.save(vaccin);
			List<Date> lout = vaccinService.findHistory(vaccin.getId());
			Assert.assertNotNull(lout);
			Assert.assertEquals(lout.size(), ldate.size());
			
			
			
		} catch (Exception e) {
			logger.error("VaccinServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test
	@Transactional
	public void shouldAddToHistory(){
		try {
			Vaccin vaccin = new Vaccin();
			List<Date> ldate = new ArrayList<>();
			ldate.add(new Date());
			ldate.add(new Date());
			ldate.add(new Date());
			ldate.add(new Date());
			vaccin.setHistorique(ldate);
			vaccin.setProchain("11/11/11");
			Vaccin vaccin2 = vaccinService.addHisto(vaccin);
			Assert.assertNotNull(vaccin2);
			Assert.assertNull(vaccin2.getProchain());
			Assert.assertEquals(vaccin2.getHistorique().size(), 5);
			
			
			
		} catch (Exception e) {
			logger.error("VaccinServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
