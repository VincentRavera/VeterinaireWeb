package fr.treeptik.veterinaireweb.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.veterinaireweb.model.Vaccin;

public interface VaccinDAO extends JpaRepository<Vaccin, Integer>{

	
	List<Vaccin> findAll() throws DataAccessException;
	
	@Query("SELECT v FROM Vaccin v left join v.historique h WHERE v.id=:id")
	List<Date> findHistory(@Param("id") Integer id) throws DataAccessException;
}
