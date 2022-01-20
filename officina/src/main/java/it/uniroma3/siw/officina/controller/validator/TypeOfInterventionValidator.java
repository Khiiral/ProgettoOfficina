package it.uniroma3.siw.officina.controller.validator;

import it.uniroma3.siw.officina.model.TypeOfIntervention;
import it.uniroma3.siw.officina.service.TypeOfInterventionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TypeOfInterventionValidator implements Validator {
	
    @Autowired
    private TypeOfInterventionService typeOfInterventionService;
    
    private static final Logger logger = LoggerFactory.getLogger(TypeOfInterventionValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "required");
        
        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
            if (this.typeOfInterventionService.alreadyExists((TypeOfIntervention)o)) {
                logger.debug("e' un duplicato");
                errors.reject("duplicato");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TypeOfIntervention.class.equals(aClass);
    }
}
