package fr.treeptik.veterinaireweb.controller;

import java.text.ParseException;
import java.util.Date;
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

import fr.treeptik.veterinaireweb.model.Vaccin;
import fr.treeptik.veterinaireweb.service.VaccinService;
import fr.treeptik.veterinaireweb.validator.VaccinValidator;

@Controller
@RequestMapping(value = "/vaccins")
public class VaccinController {

	private Logger logger = LoggerFactory.getLogger(VaccinController.class);

	@Autowired
	private VaccinService vaccinService;

	public void setVaccinService(VaccinService vaccinService) {
		this.vaccinService = vaccinService;
	}

	private VaccinValidator vaccinValidator;

	@InitBinder
	public void init(WebDataBinder bind) {
		bind.setValidator(vaccinValidator);
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView initIndex() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/list-vaccins.html", method = RequestMethod.GET)
	public ModelAndView watchAllVaccin() {
		ModelAndView modelAndView = new ModelAndView("list-vaccins");
		List<Vaccin> listVaccin = vaccinService.findAll();
		modelAndView.addObject("vaccins", listVaccin);
		return modelAndView;

	}

	@RequestMapping(value = "/edit-vaccin.html", method = RequestMethod.GET)
	public ModelAndView initFromVaccin(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView modelAndView = new ModelAndView("vaccin");
		if (id != null) {
			modelAndView.addObject("vaccin", vaccinService.findById(id));
		} else {
			modelAndView.addObject("vaccin", new Vaccin());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/add-vaccin.html", method = RequestMethod.POST)
	public ModelAndView saveVaccin(@ModelAttribute("vaccin") @Validated Vaccin vaccin, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.debug("Errors Vaccin input");
			return this.initFromVaccin(vaccin.getId());
		}
		logger.debug(vaccin.getType() + vaccin.getId());
		vaccinService.save(vaccin);
		return this.watchAllVaccin();
	}

	@RequestMapping(value = "/delete-vaccin.html", method = RequestMethod.GET)
	public ModelAndView deleteVaccin(@RequestParam(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:list-animals.html");
		Vaccin vaccin = vaccinService.findById(id);
		vaccinService.delete(vaccin);
		return modelAndView;
	}

	@RequestMapping(value = "/list-vaccin-history.html", method = RequestMethod.GET)
	public ModelAndView findhistoryOfVaccin(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("list-vaccin-history");
		Vaccin vaccin = vaccinService.findById(id);
		List<Date> listVaccin = vaccinService.findHistory(vaccin.getId());
		modelAndView.addObject("vaccin", vaccin);
		modelAndView.addObject("historys", listVaccin);
		return modelAndView;
	}

	@RequestMapping(value = "/edit-vaccin-history.html", method = RequestMethod.GET)
	public ModelAndView initFromVaccinHisto(@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("vaccin_date");
		modelAndView.addObject("vaccin", vaccinService.findById(id));
		return modelAndView;
	}

	@RequestMapping(value = "/add-vaccin-history.html", method = RequestMethod.POST)
	public ModelAndView saveVaccinHist(@ModelAttribute("vaccin") @Validated Vaccin vaccin,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.debug("Errors Date input");
			return this.initFromVaccin(vaccin.getId());
		}
		String date = vaccin.getProchain();
		vaccin = vaccinService.findById(vaccin.getId());
		vaccin.setProchain(date);
		try {
			vaccin = vaccinService.addHisto(vaccin);
			vaccinService.save(vaccin);
			return this.findhistoryOfVaccin(vaccin.getId());
		} catch (ParseException e) {
			logger.info("Date is not correctly parsed");
			e.printStackTrace();
			return this.initFromVaccinHisto(vaccin.getId());
		}
	}

}
