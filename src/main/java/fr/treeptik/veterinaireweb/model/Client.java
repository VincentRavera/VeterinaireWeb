package fr.treeptik.veterinaireweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nom;

	private String prenom;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="maitre")
	private List<Animal> animal;
	
	@ElementCollection(fetch=FetchType.LAZY)
	private List<String> telephone;
	
	@Transient
	private String telAAjouter;
	

	@OneToOne(fetch=FetchType.LAZY)
	private Adresse adresse;
	
	public String getTelAAjouter() {
		return telAAjouter;
	}
	
	public void setTelAAjouter(String telAAjouter) {
		this.telAAjouter = telAAjouter;
	}
	
	public List<Animal> getAnimal() {
		return animal;
	}
	
	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}
	
	public List<String> getTelephone() {
		return telephone;
	}
	
	public void setTelephone(List<String> telephone) {
		this.telephone = telephone;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}
	
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
