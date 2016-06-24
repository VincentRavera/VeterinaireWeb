package fr.treeptik.veterinaireweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Vaccin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ElementCollection(fetch=FetchType.LAZY)
	private List<Date> historique;
	
	@Transient
	private String prochain;
	
	private String type;
	
	private Boolean obligatoire;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Animal animal;
	
	public Boolean getObligatoire() {
		return obligatoire;
	}
	public void setObligatoire(Boolean obligatoire) {
		this.obligatoire = obligatoire;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Date> getHistorique() {
		return historique;
	}
	public void setHistorique(List<Date> historique) {
		this.historique = historique;
	}
	public String getProchain() {
		return prochain;
	}
	public void setProchain(String prochain) {
		this.prochain = prochain;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
