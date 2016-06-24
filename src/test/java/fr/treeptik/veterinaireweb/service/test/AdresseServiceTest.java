package fr.treeptik.veterinaireweb.service.test;

import java.util.ArrayList;
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
import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.service.AdresseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class})
@ActiveProfiles("test")
public class AdresseServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(AdresseServiceTest.class);
	
	@Autowired
	private AdresseService adresseService;
	
	@Test
	@Transactional
	public void shouldSaveAddresse() {
		try {
			Adresse adresse = new Adresse();
			adresse.setNumero(15);
			adresse.setRue("bd Boss");
			adresse.setVille("Kontag");
			adresse.setPays("Pologne");
			
			Adresse adresseout = adresseService.save(adresse);
			
			Assert.assertNotNull(adresseout);
			Assert.assertEquals(adresse.getNumero(), adresseout.getNumero());
			Assert.assertEquals(adresse.getRue(), adresseout.getRue());
			Assert.assertEquals(adresse.getVille(), adresseout.getVille());
			Assert.assertEquals(adresse.getPays(), adresseout.getPays());
			
		} catch (Exception e) {
			logger.error("AdresseServiceTest ERROR : "+e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test
	@Transactional
	public void shouldUpdateAdress(){
		try {
			Adresse adresse = new Adresse();
			adresse.setNumero(15);
			adresse.setRue("bd Boss");
			adresse.setVille("Kontag");
			adresse.setPays("Pologne");
			Adresse adresseout = adresseService.save(adresse);
			
			Assert.assertNotNull(adresseout.getId());
			
			adresseout.setNumero(10);
			adresseout.setRue("AV APP");
			adresseout.setVille("Gontra");
			adresseout.setPays("Russie");
			Adresse adresse2 = adresseService.save(adresseout);
			
			Assert.assertNotNull(adresseout);
			Assert.assertEquals(adresseout.getId(), adresse2.getId());
			Assert.assertEquals(adresseout.getNumero(), adresse2.getNumero());
			Assert.assertEquals(adresseout.getRue(), adresse2.getRue());
			Assert.assertEquals(adresseout.getVille(), adresse2.getVille());
			Assert.assertEquals(adresseout.getPays(), adresse2.getPays());
			
			
		} catch (Exception e) {
			logger.debug("ERROR !!!"+e.getMessage());
			Assert.fail();
		}
		
		
	}
	@Test
	@Transactional
	public void shouldFindById() {

		try {

			Adresse adresse = new Adresse();
			adresse.setNumero(15);
			adresse.setRue("bd Boss");
			adresse.setVille("Kontag");
			adresse.setPays("Pologne");
			Adresse adresse2 = adresseService.save(adresse);

			Assert.assertNotNull(adresse2.getId());
			Assert.assertEquals(adresse.getNumero(), adresse2.getNumero());
			Assert.assertEquals(adresse.getRue(), adresse2.getRue());
			Assert.assertEquals(adresse.getVille(), adresse2.getVille());
			Assert.assertEquals(adresse.getPays(), adresse2.getPays());
			
			Integer id = adresse2.getId();
			adresse = null;
			adresse = adresseService.findById(id);
			
			Assert.assertEquals(adresse.getNumero(), adresse2.getNumero());
			Assert.assertEquals(adresse.getRue(), adresse2.getRue());
			Assert.assertEquals(adresse.getVille(), adresse2.getVille());
			Assert.assertEquals(adresse.getPays(), adresse2.getPays());
			Assert.assertEquals(adresse2.getId(), adresse.getId());
			
			

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Transactional
	public void shouldDeletePersonne() {

		try {

			Adresse adresse = new Adresse();
			adresse.setNumero(15);
			adresse.setRue("bd Boss");
			adresse.setVille("Kontag");
			adresse.setPays("Pologne");
			Adresse adresse2 = adresseService.save(adresse);

			Assert.assertNotNull(adresse2.getId());
			Assert.assertEquals(adresse.getNumero(), adresse2.getNumero());
			Assert.assertEquals(adresse.getRue(), adresse2.getRue());
			Assert.assertEquals(adresse.getVille(), adresse2.getVille());
			Assert.assertEquals(adresse.getPays(), adresse2.getPays());
			
			Integer id = adresse2.getId();
			adresseService.delete(adresse2);
			adresse2 = null;
			adresse2 = adresseService.findById(id);
			
			Assert.assertNull(adresse2);
			Assert.assertNotNull(id);
			

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	
	@Test
	@Transactional
	public void shouldFindAll() {

		try {

			Adresse adresse = new Adresse();
			adresse.setNumero(15);
			adresse.setRue("bd Boss");
			adresse.setVille("Kontag");
			adresse.setPays("Pologne");
			
			Adresse adresse2 = new Adresse();
			adresse2.setId(55);
			adresse2.setRue("av sss");
			adresse2.setVille("ville");
			adresse2.setPays("pays");
			
			List<Adresse> ladr = new ArrayList<>();
			
			adresse = adresseService.save(adresse);
			adresse = adresseService.save(adresse2);
			
			ladr.add(adresse);
			ladr.add(adresse2);
			
			List<Adresse> lout = adresseService.findAll();
			
			Assert.assertEquals(lout.size(), ladr.size());


		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	
	

}
