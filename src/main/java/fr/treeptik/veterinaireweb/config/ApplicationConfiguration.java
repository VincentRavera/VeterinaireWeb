package fr.treeptik.veterinaireweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import fr.treeptik.veterinaireweb.dao.AdresseDAO;
import fr.treeptik.veterinaireweb.dao.AnimalDAO;
import fr.treeptik.veterinaireweb.dao.ClientDAO;
import fr.treeptik.veterinaireweb.dao.VaccinDAO;
import fr.treeptik.veterinaireweb.service.AdresseService;
import fr.treeptik.veterinaireweb.service.AnimalService;
import fr.treeptik.veterinaireweb.service.ClientService;
import fr.treeptik.veterinaireweb.service.MixedService;
import fr.treeptik.veterinaireweb.service.VaccinService;
import fr.treeptik.veterinaireweb.service.impl.AdresseServiceImpl;
import fr.treeptik.veterinaireweb.service.impl.AnimalServiceImpl;
import fr.treeptik.veterinaireweb.service.impl.ClientServiceImpl;
import fr.treeptik.veterinaireweb.service.impl.MixedServiceImpl;
import fr.treeptik.veterinaireweb.service.impl.VaccinServiceImpl;

@Configuration
@Import(value = { DataConfiguration.class , SecurityConfiguration.class})
@PropertySource(value = "classpath:/config.properties")
public class ApplicationConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	@Bean
	public ClientService clientService(ClientDAO clientDAO){
		ClientServiceImpl personneServiceImpl = new ClientServiceImpl();
		personneServiceImpl.setClientDAO(clientDAO);
		return personneServiceImpl;
	}
	
	@Bean
	public AdresseService adresseService(AdresseDAO adresseDAO){
		AdresseServiceImpl adresseServiceImpl = new AdresseServiceImpl();
		adresseServiceImpl.setAdresseDAO(adresseDAO);
		return adresseServiceImpl;
	}
	
	@Bean
	public AnimalService animalService(AnimalDAO animalDAO){
		AnimalServiceImpl animalServiceImpl = new AnimalServiceImpl();
		animalServiceImpl.setAnimalDAO(animalDAO);
		return animalServiceImpl;
	}
	
	@Bean
	public VaccinService vaccinService(VaccinDAO vaccinDAO){
		VaccinServiceImpl vaccinServiceImpl = new VaccinServiceImpl();
		vaccinServiceImpl.setVaccinDAO(vaccinDAO);
		return vaccinServiceImpl;
	}
	
	@Bean
	public MixedService mixedService(){
		MixedServiceImpl mixedServiceImpl = new MixedServiceImpl();
		mixedServiceImpl.setAdresseService(new AdresseServiceImpl());
		mixedServiceImpl.setAnimalService(new AnimalServiceImpl());
		mixedServiceImpl.setClientService(new ClientServiceImpl());
		return mixedServiceImpl;
	}
	

}
