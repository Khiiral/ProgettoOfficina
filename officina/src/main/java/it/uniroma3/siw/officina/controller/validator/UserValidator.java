package it.uniroma3.siw.officina.controller.validator;

import it.uniroma3.siw.officina.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	
    final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        String name = user.getName().trim();
        String surname = user.getSurname().trim();
        
        if (name.isEmpty()) 
            errors.rejectValue("name", "required");
        else if (name.length() < this.MIN_NAME_LENGTH || name.length() > this.MAX_NAME_LENGTH) 
            errors.rejectValue("name", "size");
        
        if (surname.isEmpty()) 
            errors.rejectValue("surname", "required");
        else if (surname.length() < this.MIN_NAME_LENGTH || surname.length() > this.MAX_NAME_LENGTH) 
            errors.rejectValue("surname", "size");
        
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}
