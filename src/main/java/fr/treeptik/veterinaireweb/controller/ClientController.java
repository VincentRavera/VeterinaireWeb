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

import fr.treeptik.veterinaireweb.model.Client;
import fr.treeptik.veterinaireweb.service.ClientService;
import fr.treeptik.veterinaireweb.validator.ClientValidator;

@Controller
@RequestMapping(value="/clients")
public class ClientController {
	
	private Logger logger = LoggerFactory.getLogger(ClientController.class);
	private ClientValidator clientValidator;
	@Autowired
	private ClientService clientService;
	
	@Autowired
	public void setPersonneValidator(ClientValidator clientValidator) {
		this.clientValidator = clientValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder bind) {
		bind.setValidator(clientValidator);
	}



	public void setPersonneService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@RequestMapping(value="/index.html", method= RequestMethod.GET)
	public ModelAndView initIndex(){
		return new ModelAndView("index");
	}
	@RequestMapping(value="/list-clients.html", method= RequestMethod.GET)
	public ModelAndView initListClient(){
		ModelAndView modelAndView = new ModelAndView("list-clients");
		List<Client> nameDesc = clientService.findAll();
		modelAndView.addObject("clients", nameDesc);
		return modelAndView;
	}
	@RequestMapping(value="/list-client-phone.html", method= RequestMethod.GET)
	public ModelAndView findNumbersOfClient(@RequestParam(value="id", required=true) Integer id){
		ModelAndView modelAndView = new ModelAndView("list-client-phone");
		Client client = clientService.findById(id);
		List<String> listNumero = clientService.findTelephones(client);
		modelAndView.addObject("client", client);
		modelAndView.addObject("numeros", listNumero);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit-client.html", method=RequestMethod.GET)
	public ModelAndView initFromClient(@RequestParam(value="id", required=false) Integer id) {
		String client = "client";
		ModelAndView modelAndView = new ModelAndView(client);
		if (id != null) {
			modelAndView.addObject(client, clientService.findById(id));
		}else {
			modelAndView.addObject(client, new Client());
		}
		return modelAndView;
	}
	@RequestMapping(value="/edit-phone.html", method=RequestMethod.GET)
	public ModelAndView editphone(@RequestParam(value="id", required=true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("phone");
		modelAndView.addObject("client", clientService.findById(id));
		return modelAndView;
	}
	
	@RequestMapping(value="/add-client.html", method=RequestMethod.POST)
	public ModelAndView saveClient(
			@ModelAttribute("client")@Validated Client client,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("client");
			modelAndView.addObject("client", client);
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:list-clients.html");
		logger.debug(""+client.getNom()+client.getPrenom()+client.getId());
		clientService.save(client);
		return modelAndView;
	}
	@RequestMapping(value="/add-phone.html", method=RequestMethod.POST)
	public ModelAndView savePone(
			@ModelAttribute("client")@Validated Client client,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.debug("Invalid client");
			return editphone(client.getId());
		}
		ModelAndView modelAndView = new ModelAndView("redirect:list-client-phone.html?id="+client.getId());
		logger.debug(client.getNom()+client.getTelAAjouter());
		client = clientService.addNumber(client);
		Client client2 = clientService.findById(client.getId());
		client2.setTelephone(client.getTelephone());
		clientService.save(client2);
		return modelAndView;
	}

	
	
	@RequestMapping(value="/delete-client.html", method=RequestMethod.GET)
	public ModelAndView deleteClient(@RequestParam(value="id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:list-clients.html");
		clientService.delete(id);
		return modelAndView;
	}

}
