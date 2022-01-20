package it.uniroma3.siw.officina.service;

import it.uniroma3.siw.officina.model.TypeOfIntervention;
import it.uniroma3.siw.officina.repository.TypeOfInterventionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeOfInterventionService {
    @Autowired
    private TypeOfInterventionRepository typeOfInterventionRepository;
    @Autowired
    private CredentialsService credentialsService;

    @Transactional
    public TypeOfIntervention insert(TypeOfIntervention typeOfIntervention) {
        return this.typeOfInterventionRepository.save(typeOfIntervention);
    }

    @Transactional
    public List<TypeOfIntervention> allTypeOfIntervention() {
        return (List<TypeOfIntervention>) this.typeOfInterventionRepository.findAll();
    }

    @Transactional
    public boolean alreadyExists(TypeOfIntervention typeOfIntervention) {
        List<TypeOfIntervention> typeOfInterventions = this.typeOfInterventionRepository.findByName(typeOfIntervention.getName());
        if(typeOfInterventions.size() > 0)
        	return true;
        else
        	return false;
    }

    @Transactional
    public CredentialsService getCredentialsService() {
        return this.credentialsService;
    }

    @Transactional
    public TypeOfIntervention typeOfInterventionById(Long id) {
        Optional<TypeOfIntervention> optional = this.typeOfInterventionRepository.findById(id);
        if (optional.isPresent())
        	return optional.get();
        else
        	return null;
    }
      
}