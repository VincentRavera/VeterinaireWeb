package fr.treeptik.veterinaireweb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.veterinaireweb.model.Adresse;
import fr.treeptik.veterinaireweb.model.Client;

public interface ClientDAO extends JpaRepository<Client, Integer> {
	
	@Query("SELECT p FROM Client p WHERE p.nom=:nom")
	List<Client> findByNom2(@Param("nom") String nom) throws DataAccessException;
	
	List<Client> findByNom(String nom) throws DataAccessException;

	@Query("SELECT p FROM Client p ORDER BY p.nom DESC")
	List<Client> findAllOrderByNomDesc() throws DataAccessException;
	
	List<Client> findAll() throws DataAccessException;
	
	@Query("SELECT t FROM Client c left join c.telephone t WHERE c.id=:id")
	List<String> findTelephone(@Param("id") Integer id) throws DataAccessException;

	@Query("SELECT a FROM Client c LEFT JOIN FETCH Adresse a WHERE c.id=:id")
	Adresse findAdresse(@Param("id")Integer id) throws DataAccessException;
}
