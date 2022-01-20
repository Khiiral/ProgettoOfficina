package it.uniroma3.siw.officina.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.officina.model.TypeOfIntervention;

public interface TypeOfInterventionRepository extends CrudRepository<TypeOfIntervention, Long> {
	
    public List<TypeOfIntervention> findByName(String name);
}