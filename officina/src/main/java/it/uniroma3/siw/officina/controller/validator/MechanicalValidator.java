package it.uniroma3.siw.officina.controller.validator;

import it.uniroma3.siw.officina.model.Mechanical;
import it.uniroma3.siw.officina.service.MechanicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MechanicalValidator implements Validator {
	
    @Autowired
    private MechanicalService mechanicalService;
    
    private static final Logger logger = LoggerFactory.getLogger(MechanicalValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
        
        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
            if (this.mechanicalService.alreadyExists((Mechanical)o)) {
                logger.debug("e' un duplicato");
                errors.reject("duplicato");
            }
        }
    }

    
    @Override
    public boolean supports(Class<?> aClass) {
        return Mechanical.class.equals(aClass);
    }
}
