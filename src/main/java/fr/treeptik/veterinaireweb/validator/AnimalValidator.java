package fr.treeptik.veterinaireweb.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.treeptik.veterinaireweb.model.Animal;

public class AnimalValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Animal.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Animal animal = (Animal) target;
		
		
	}

}
