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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.veterinaireweb.config.ApplicationConfiguration;
import fr.treeptik.veterinaireweb.model.Client;
import fr.treeptik.veterinaireweb.service.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@ActiveProfiles("test")
public class ClientServiceTest {

	private Logger logger = LoggerFactory.getLogger(ClientServiceTest.class);

	@Autowired
	private ClientService clientService;

	@Test
	@Transactional
	public void shouldSavePersonne() {

		try {

			Client personne = new Client();
			personne.setNom("Dupont");
			personne.setPrenom("Pierre");
			String tel = "060606";
			List<String> ltel = new ArrayList<>();
			ltel.add(tel);
			personne.setTelephone(ltel);
			personne = clientService.save(personne);

			Assert.assertNotNull(personne.getId());
			Assert.assertEquals("Dupont", personne.getNom());
			Assert.assertEquals("Pierre", personne.getPrenom());
			Assert.assertEquals(ltel, personne.getTelephone());

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	@Test
	@Transactional
	public void shouldUpdatePersonne() {

		try {

			Client personne = new Client();
			personne.setNom("Dupont");
			personne.setPrenom("Pierre");
			personne = clientService.save(personne);

			Assert.assertNotNull(personne.getId());
			Assert.assertEquals("Dupont", personne.getNom());
			Assert.assertEquals("Pierre", personne.getPrenom());
			
			personne.setNom("Moales");
			personne.setPrenom("David");
			personne = clientService.save(personne);
			
			Assert.assertNotNull(personne.getId());
			Assert.assertEquals("Moales", personne.getNom());
			Assert.assertEquals("David", personne.getPrenom());
			

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Transactional
	public void shouldDeletePersonne() {

		try {

			Client personne = new Client();
			personne.setNom("Dupont");
			personne.setPrenom("Pierre");
			personne = clientService.save(personne);

			Assert.assertNotNull(personne.getId());
			Assert.assertEquals("Dupont", personne.getNom());
			Assert.assertEquals("Pierre", personne.getPrenom());
			
			Integer id = personne.getId();
			clientService.delete(id);
			personne = null;
			personne = clientService.findById(id);
			
			Assert.assertNotNull(id);
			Assert.assertNull(personne);

		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	@Test
	@Transactional
	public void shouldFindAll() {
		
		try {
			Client client = new Client();
			Client client2 = new Client();
			List<Client> lc = new ArrayList<>();
			client.setNom("Paul");
			client.setPrenom("Pal");
			client = clientService.save(client);
			client2.setNom("Dave");
			client2.setNom("Dove");
			client2 = clientService.save(client2);
			lc.add(client);
			lc.add(client2);
			List<Client> lcout = clientService.findAll();
			Assert.assertEquals(lc.size(), lcout.size());
			
			
			
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql",
			executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql",
			executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudFindPersonne() {
		try {
			Client personne = clientService.findById(1);
			Assert.assertEquals("Dupuis", personne.getNom());
			Assert.assertEquals("Paul", personne.getPrenom());
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql",
			executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql",
			executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shoudFindAllOrderByNameDesc() {
		try {
			List<Client> personnes = clientService.findAllOrderByNameDesc();
			for (int i = 0; i < personnes.size() - 1; i++) {
				Boolean test = false;
				test = personnes.get(i).getNom()
						.compareTo(personnes.get(i+1).getNom())>=0;
				Assert.assertTrue(test);
				
			}
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	@Test
	@Sql(scripts = "classpath:/init-test.sql",
			executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(scripts="classpath:/clean.sql",
			executionPhase=ExecutionPhase.AFTER_TEST_METHOD)
	public void shouldFindByName() {
		try {
			List<Client> personnes = clientService.findByName("Dupuis");
			
			for (Client personne : personnes) {
				Assert.assertEquals("Dupuis", personne.getNom());
			
			}
			//example boucle Lambda
			personnes.stream().forEach(p -> {Assert.assertEquals("Dupuis", p.getNom());});
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test
	@Transactional
	public void shouldFindPhones() {
		
		try {
			Client client = new Client();
			Client client2 = new Client();
			List<Client> lc = new ArrayList<>();
			List<String> nums = new ArrayList<>();
			List<String> nums2 = new ArrayList<>();
			nums.add("0001");
			nums2.add("0002");
			nums2.add("0005");
			client.setNom("Paul");
			client.setPrenom("Pal");
			client.setTelephone(nums);
			client2.setTelephone(nums2);
			client = clientService.save(client);
			client2.setNom("Dave");
			client2.setNom("Dove");
			client2 = clientService.save(client2);
			lc.add(client);
			lc.add(client2);
			List<String> lnumout = clientService.findTelephones(client);
			List<String> lnumout2 = clientService.findTelephones(client2);
			Assert.assertEquals(lnumout.size(), nums.size());
			Assert.assertEquals(lnumout2.size(), nums2.size());
			Assert.assertTrue(lnumout.equals(nums));
			Assert.assertTrue(lnumout2.equals(nums2));
			
			
			
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
			Assert.fail();
		}
	}
		@Test
		@Transactional
		public void shouldAddPhone() {
			
			try {
				Client client = new Client();
				
				List<String> nums = new ArrayList<>();
				
				nums.add("0001");
				
				client.setNom("Paul");
				client.setPrenom("Pal");
				client.setTelephone(nums);
				
				client = clientService.save(client);
				
				List<String> lnumout = clientService.findTelephones(client);
				
				Assert.assertEquals(lnumout.size(), nums.size());
				
				Assert.assertTrue(lnumout.equals(nums));
				
				
				
				
			} catch (Exception e) {
				logger.error("exception : " + e.getMessage());
				Assert.fail();
			}
		}
		
		@Test
		@Transactional
		public void shouldAddAPhone() {
			
			try {
				Client client = new Client();
				List<String> nums = new ArrayList<>();
				nums.add("0001");
				client.setNom("Paul");
				client.setPrenom("Pal");
				client.setTelephone(nums);
				client = clientService.save(client);
				client.setTelAAjouter("060606");
				Client client2 = clientService.addNumber(client);
				List<String> lnumout = clientService.findTelephones(client);
				Assert.assertEquals(lnumout.size(), client2.getTelephone().size());
				Assert.assertTrue(lnumout.equals(client2.getTelephone()));

				
				
				
			} catch (Exception e) {
				logger.error("exception : " + e.getMessage());
				Assert.fail();
			}
		}
		
		
}
