package fr.treeptik.veterinaireweb.service.test;

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
import fr.treeptik.veterinaireweb.model.Client;
import fr.treeptik.veterinaireweb.service.AdresseService;
import fr.treeptik.veterinaireweb.service.ClientService;
import fr.treeptik.veterinaireweb.service.MixedService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@ActiveProfiles("test")
public class MixedServiceTest {
	
	@Autowired
	private MixedService mixedService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AdresseService adresseService;
	
	private Logger logger = LoggerFactory.getLogger(MixedServiceTest.class);
	
	@Test
	@Transactional
	public void shouldSaveClientWithAdresse() {
		try {
			Client client = new Client();
			client.setNom("qdqzd00");
			client.setPrenom("dqzdqdzq0");
			Adresse adresse = new Adresse();
			adresse.setPays("qDQDQD");
			client.setAdresse(adresse);
			logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Client client2 = mixedService.saveClientWithAdresse(client);
			
			
			client = clientService.findById(client2.getId());
			adresse = adresseService.findById(client2.getAdresse().getId());
			
			Adresse adrOut = clientService.findAdresse(client.getId());
			Assert.assertEquals(adresse.getPays(), adrOut.getPays());
			
			
			
		} catch (Exception e) {
			logger.debug("exception : " + e.getMessage());
			Assert.fail();
		}
	}
	
	

}
