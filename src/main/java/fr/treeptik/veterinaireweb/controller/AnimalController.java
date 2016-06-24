package fr.treeptik.veterinaireweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.veterinaireweb.model.Animal;
import fr.treeptik.veterinaireweb.service.AnimalService;
import fr.treeptik.veterinaireweb.validator.AnimalValidator;

@Controller
@RequestMapping(value = "/animals")
public class AnimalController {
	private Logger logger = LoggerFactory.getLogger(AnimalController.class);

	@Autowired
	private AnimalService animalService;
	
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}

	private AnimalValidator animalValidator;
	
	@InitBinder
	public void init(WebDataBinder bind) {
		bind.setValidator(animalValidator);
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView initIndex() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/list-animals.html", method = RequestMethod.GET)
	public ModelAndView watchAllAnimal() {
		ModelAndView modelAndView = new ModelAndView("list-animals");
		List<Animal> listAnimal = animalService.findAll();
		modelAndView.addObject("animals", listAnimal);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/edit-animal.html", method = RequestMethod.GET)
	public ModelAndView initFromAnimal(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView modelAndView = new ModelAndView("animal");
		if (id != null) {
			modelAndView.addObject("animal", animalService.findById(id));
		} else {
			modelAndView.addObject("animal", new Animal());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/add-animal.html", method = RequestMethod.POST)
	public ModelAndView saveAnimal(@ModelAttribute("animal") @Validated Animal animal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.debug("Errors animal input");
			return this.initFromAnimal(animal.getId());
		}
		logger.debug(animal.getNom()+animal.getAge()+animal.getEspece()+animal.getRace());
		animalService.save(animal);
		return this.watchAllAnimal();
	}
	
	@RequestMapping(value="/delete-animal.html", method=RequestMethod.GET)
	public ModelAndView deleteAnimal(@RequestParam(value="id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:list-animals.html");
		Animal animal = animalService.findById(id);
		animalService.delete(animal);
		return modelAndView;
	}
	
	
}
