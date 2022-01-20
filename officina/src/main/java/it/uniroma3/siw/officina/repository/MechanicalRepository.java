package it.uniroma3.siw.officina.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.officina.model.Mechanical;

public interface MechanicalRepository extends CrudRepository<Mechanical, Long> {
	
    public List<Mechanical> findByName(String name);

    public List<Mechanical> findByNameAndSurname(String name, String surname);
}