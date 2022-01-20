package it.uniroma3.siw.officina.service;

import it.uniroma3.siw.officina.model.User;
import it.uniroma3.siw.officina.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    private CredentialsService credentialsService;

    @Transactional
    public User getUser(Long id) {
        Optional<User> result = this.userRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.userRepository.findAll();
        for(User user : iterable)
            	result.add(user);
        return result;
    }
   
    @Transactional
    public CredentialsService getCredentialsService() {
        return this.credentialsService;
    }

    @Transactional
    public User getUserByUsername(String username) {
        return this.credentialsService.findByUsername(username).get().getUser();
    }
}
