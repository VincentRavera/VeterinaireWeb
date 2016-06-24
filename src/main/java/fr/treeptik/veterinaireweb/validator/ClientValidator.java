package fr.treeptik.veterinaireweb.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.veterinaireweb.model.Client;

@Component
public class ClientValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Client.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Client personne = (Client) target;
		
		if (personne.getNom() == null || personne.getNom().isEmpty()) {
			errors.rejectValue("nom", "nom.empty");
		}
	}
	

}
