package it.uniroma3.siw.officina.controller.validator;

import it.uniroma3.siw.officina.model.Credentials;
import it.uniroma3.siw.officina.model.User;
import it.uniroma3.siw.officina.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CredentialsValidator implements Validator {
	
    @Autowired
    private CredentialsService credentialsService;
    
    final Integer MAX_USERNAME_LENGTH = 20;
    final Integer MIN_USERNAME_LENGTH = 4;
    final Integer MAX_PASSWORD_LENGTH = 20;
    final Integer MIN_PASSWORD_LENGTH = 6;

    @Override
    public void validate(Object o, Errors errors) {
        Credentials credentials = (Credentials)o;
        String username = credentials.getUsername().trim();
        String password = credentials.getPassword().trim();
        
        if (username.isEmpty()) 
            errors.rejectValue("username", "required");
        else if (username.length() < this.MIN_USERNAME_LENGTH || username.length() > this.MAX_USERNAME_LENGTH) 
            errors.rejectValue("username", "size");
        else if (this.credentialsService.getCredentials(username) != null) 
            errors.rejectValue("username", "duplicate");
       
        
        if (password.isEmpty()) 
            errors.rejectValue("password", "required");
        else if (password.length() < this.MIN_PASSWORD_LENGTH || password.length() > this.MAX_PASSWORD_LENGTH) 
            errors.rejectValue("password", "size");
        
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}