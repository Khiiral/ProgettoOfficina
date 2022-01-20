package it.uniroma3.siw.officina.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.officina.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}