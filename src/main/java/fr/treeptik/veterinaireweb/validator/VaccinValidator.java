package fr.treeptik.veterinaireweb.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.veterinaireweb.model.Vaccin;

public class VaccinValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Vaccin.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
	}

}
