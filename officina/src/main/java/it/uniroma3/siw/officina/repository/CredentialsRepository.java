package it.uniroma3.siw.officina.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.officina.model.Credentials;
import it.uniroma3.siw.officina.model.User;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
    public Optional<Credentials> findByUsername(String username);

    public Optional<Credentials> findByUser(User user);
}