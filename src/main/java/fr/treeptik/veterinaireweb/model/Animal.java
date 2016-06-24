package fr.treeptik.veterinaireweb.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Animal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	
	private String espece;
	
	private String race;
	
	private Integer age;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Client maitre;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="animal")
	private List<Vaccin> vaccin;

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

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Client getMaitre() {
		return maitre;
	}

	public void setMaitre(Client maitre) {
		this.maitre = maitre;
	}

	public List<Vaccin> getVaccin() {
		return vaccin;
	}

	public void setVaccin(List<Vaccin> vaccin) {
		this.vaccin = vaccin;
	}

	

}
