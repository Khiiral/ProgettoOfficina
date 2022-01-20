package it.uniroma3.siw.officina.controller.validator;

import it.uniroma3.siw.officina.model.Intervention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class InterventionValidator implements Validator {
	
    private static final Logger logger = LoggerFactory.getLogger(InterventionValidator.class);

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "client", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mechanical", "required");
        
        if (!errors.hasErrors()) {
            logger.debug("confermato: valori non nulli");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Intervention.class.equals(aClass);
    }
}