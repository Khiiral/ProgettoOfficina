package it.uniroma3.siw.officina.service;

import it.uniroma3.siw.officina.model.Mechanical;
import it.uniroma3.siw.officina.repository.MechanicalRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MechanicalService {
    @Autowired
    private MechanicalRepository mechanicalRepository;
    @Autowired
    private CredentialsService credentialsService;

    @Transactional
    public Mechanical insert(Mechanical mechanical) {
        return this.mechanicalRepository.save(mechanical);
    }

    @Transactional
    public List<Mechanical> allMechanical() {
        return (List<Mechanical>) this.mechanicalRepository.findAll();
    }

    @Transactional
    public Mechanical mechanicalById(Long id) {
        Optional<Mechanical> optional = this.mechanicalRepository.findById(id);
        if (optional.isPresent()) 
            return optional.get();
        else
            return null;        
    }

    @Transactional
    public boolean alreadyExists(Mechanical mechanical) {
        List<Mechanical> mechanicals = this.mechanicalRepository.findByNameAndSurname(mechanical.getName(), mechanical.getSurname());
        if(mechanicals.size() > 0)
        	return true;
        else
        	return false;
    }

    @Transactional
    public CredentialsService getCredentialsService() {
        return this.credentialsService;
    }
}