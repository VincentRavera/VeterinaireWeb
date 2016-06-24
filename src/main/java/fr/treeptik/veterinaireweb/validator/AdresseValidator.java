package fr.treeptik.veterinaireweb.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.veterinaireweb.model.Adresse;

public class AdresseValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Adresse.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Adresse adresse = (Adresse) target;
		if (adresse.getVille() == null || adresse.getVille().isEmpty()) {
			errors.rejectValue("ville", "ville.empty");
		}
		
	}

}
