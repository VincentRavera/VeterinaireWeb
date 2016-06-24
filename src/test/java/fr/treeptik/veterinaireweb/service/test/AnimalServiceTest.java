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
import fr.treeptik.veterinaireweb.model.Animal;
import fr.treeptik.veterinaireweb.service.AnimalService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfiguration.class})
@ActiveProfiles("test")
public class AnimalServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(AnimalServiceTest.class);
	
	@Autowired
	public AnimalService animalService;
	
	@Test
	@Transactional
	public void shouldSaveAndUpdateAnimal(){
		try {
			Animal animal = new Animal();
			animal.setAge(15);
			animal.setEspece("Chat");
			animal.setNom("felix");
			animal.setRace("po");
			Animal res = animalService.save(animal);
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(animal.getAge(), res.getAge());
			Assert.assertEquals(animal.getEspece(), res.getEspece());
			Assert.assertEquals(animal.getNom(), res.getNom());
			Assert.assertEquals(animal.getRace(), res.getRace());
			
			animal.setId(res.getId());
			animal.setAge(30);
			animal.setEspece("A");
			animal.setNom("B");
			animal.setRace("C");
			res = animalService.save(animal);
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(animal.getAge(), res.getAge());
			Assert.assertEquals(animal.getEspece(), res.getEspece());
			Assert.assertEquals(animal.getNom(), res.getNom());
			Assert.assertEquals(animal.getRace(), res.getRace());
			
			
		} catch (Exception e) {
			logger.error("AnimalServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	@Test
	@Transactional
	public void shouldDeleteAnimal(){
		try {
			Animal animal = new Animal();
			animal.setAge(15);
			animal.setEspece("Chat");
			animal.setNom("felix");
			animal.setRace("po");
			animal = animalService.save(animal);
			animalService.delete(animal);
			Animal res = animalService.findById(animal.getId());
			Assert.assertNull(res);
			
			
			
		} catch (Exception e) {
			logger.error("AnimalServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	@Test
	@Transactional
	public void shouldFindAll(){
		try {
			Animal animal = animalService.save(new Animal());
			Animal animal1 = animalService.save(new Animal());
			Animal animal2 = animalService.save(new Animal());
			Animal animal3 = animalService.save(new Animal());
			
			List<Animal> lani = new ArrayList<>();
			lani.add(animal);
			lani.add(animal1);
			lani.add(animal2);
			lani.add(animal3);
			
			List<Animal> lout = animalService.findAll();
			
			Assert.assertNotNull(lout);
			Assert.assertEquals(lani.size(), lout.size());
			
			
		} catch (Exception e) {
			logger.error("AnimalServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	@Test
	@Transactional
	public void shouldFindById(){
		try {
			Animal animal = animalService.save(new Animal());
			animal.setAge(15);
			animal.setEspece("Chat");
			animal.setNom("felix");
			animal.setRace("po");
			animal = animalService.save(animal);
			Animal res = animalService.findById(animal.getId());
			Assert.assertNotNull(res);
			Assert.assertNotNull(res.getId());
			Assert.assertEquals(animal.getAge(), res.getAge());
			Assert.assertEquals(animal.getEspece(), res.getEspece());
			Assert.assertEquals(animal.getNom(), res.getNom());
			Assert.assertEquals(animal.getRace(), res.getRace());
			
			
		} catch (Exception e) {
			logger.error("AnimalServiceTest ERROR"+e.getMessage());
			Assert.fail();
		}
		
	}
	
	
	

}
