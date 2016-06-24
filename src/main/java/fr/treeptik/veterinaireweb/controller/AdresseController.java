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

import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.service.AdresseService;
import fr.treeptik.veterinaireweb.validator.AdresseValidator;

@Controller
@RequestMapping(value = "/adresses")
public class AdresseController {
	private Logger logger = LoggerFactory.getLogger(AdresseController.class);

	@Autowired
	private AdresseService adresseService;

	private AdresseValidator adresseValidator;

	@InitBinder
	public void init(WebDataBinder bind) {
		bind.setValidator(adresseValidator);
	}

	public void setAdresseService(AdresseService adresseService) {
		this.adresseService = adresseService;
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView initIndex() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/list-adresses.html", method = RequestMethod.GET)
	public ModelAndView watchAllAdress() {
		ModelAndView modelAndView = new ModelAndView("list-adresse");
		List<Adresse> listAdresse = adresseService.findAll();
		modelAndView.addObject("adresses", listAdresse);
		return modelAndView;
	}

	@RequestMapping(value = "/edit-adresse.html", method = RequestMethod.GET)
	public ModelAndView initFromAdresse(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView modelAndView = new ModelAndView("adresse");
		if (id != null) {
			modelAndView.addObject("adresse", adresseService.findById(id));
		} else {
			modelAndView.addObject("adresse", new Adresse());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add-adresse.html", method = RequestMethod.POST)
	public ModelAndView saveAdresse(@ModelAttribute("client") @Validated Adresse adresse, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("adresse");
			modelAndView.addObject("adresse", adresse);
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:list-adresses.html");
		logger.debug(adresse.getNumero()+adresse.getRue()+adresse.getVille()+adresse.getPays());
		adresseService.save(adresse);
		return modelAndView;
	}

	
	@RequestMapping(value="/delete-adresse.html", method=RequestMethod.GET)
	public ModelAndView deleteAdresse(@RequestParam(value="id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:list-clients.html");
		Adresse adresse = adresseService.findById(id);
		adresseService.delete(adresse);
		return modelAndView;
	}

	
	
	
	
	
	
	
}
