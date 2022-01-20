package it.uniroma3.siw.officina.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.officina.model.Intervention;
import it.uniroma3.siw.officina.model.User;

public interface InterventionRepository extends CrudRepository<Intervention, Long> {
	
    public Optional<List<Intervention>> findByClient(User client);

    public Optional<Intervention> findById(Long id);

}
