package it.uniroma3.siw.officina.service;

import it.uniroma3.siw.officina.model.Intervention;
import it.uniroma3.siw.officina.model.User;
import it.uniroma3.siw.officina.repository.InterventionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private UserService userService;
    @Autowired
    private MechanicalService mechanicalService;
    @Autowired
    private TypeOfInterventionService typeOfInterventionService;

    @Transactional
    public Intervention insert(Intervention intervention) {
        return this.interventionRepository.save(intervention);
    }

    @Transactional
    public List<Intervention> allInterventions() {
        return (List<Intervention>) this.interventionRepository.findAll();
    }

    @Transactional
    public Intervention interventionById(Long id) {
        Optional<Intervention> optional = this.interventionRepository.findById(id);
        if (optional.isPresent()) 
            return optional.get();
        else
        	return null;
    }

    @Transactional
    public List<Intervention> interventionByClient(User client) {
        Optional<List<Intervention>> optional = this.interventionRepository.findByClient(client);
        if (optional.isPresent()) 
            return optional.get();
        else
        	return null;
    }

    @Transactional
    public boolean alreadyExists(Intervention intervention) {
        Optional<Intervention> interventions = this.interventionRepository.findById(intervention.getId());
        if(interventions.get()!=null)
        	return true;
        else
        	return false;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public MechanicalService getMechanicalService() {
        return this.mechanicalService;
    }

    public TypeOfInterventionService getTypeOfInterventionService() {
        return this.typeOfInterventionService;
    }

    public CredentialsService getCredentialsService() {
        return this.credentialsService;
    }
}
