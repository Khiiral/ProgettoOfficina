package it.uniroma3.siw.officina.controller;

import it.uniroma3.siw.officina.controller.validator.TypeOfInterventionValidator;
import it.uniroma3.siw.officina.model.TypeOfIntervention;
import it.uniroma3.siw.officina.service.TypeOfInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TypeOfInterventionController {
	
    @Autowired
    private TypeOfInterventionService typeOfInterventionService;
    
    @Autowired
    private TypeOfInterventionValidator typeOfInterventionValidator;
    

    @RequestMapping(value="/admin/typeOfIntervention", method=RequestMethod.GET)
    public String addTypeOfIntervention(Model model) {
        model.addAttribute("typeOfIntervention", new TypeOfIntervention());
        return "typeOfInterventionForm";
    }

    @RequestMapping(value="/typeOfInterventionAll", method=RequestMethod.GET)
    public String getTypeOfInterventionAll(Model model) {
        model.addAttribute("typeOfInterventions", this.typeOfInterventionService.allTypeOfIntervention());
        return "typeOfInterventionsAll";
    }

    @RequestMapping(value="/typeOfInterventionAll/{id}", method=RequestMethod.GET)
    public String getTypeOfInterventionAll(@PathVariable("id") Long id, Model model) {
        model.addAttribute("typeOfIntervention", this.typeOfInterventionService.typeOfInterventionById(id));
        return "typeOfInterventionAll";
    }

    @RequestMapping(value="/typeOfIntervention", method=RequestMethod.GET)
    public String getTypeOfInterventions(Model model) {
        model.addAttribute("typeOfInterventions", this.typeOfInterventionService.allTypeOfIntervention());
        model.addAttribute("role", this.typeOfInterventionService.getCredentialsService().getRoleAuthenticated());
        return "typeOfInterventions";
    }

    @RequestMapping(value="/typeOfIntervention/{id}", method=RequestMethod.GET)
    public String getTypeOfIntervention(@PathVariable("id") Long id, Model model) {
        model.addAttribute("typeOfIntervention", this.typeOfInterventionService.typeOfInterventionById(id));
        model.addAttribute("role", this.typeOfInterventionService.getCredentialsService().getRoleAuthenticated());
        return "typeOfIntervention";
    }

    @RequestMapping(value="/admin/typeOfIntervention", method=RequestMethod.POST)
    public String addTypeOfIntervention(@ModelAttribute("typeOfIntervention") TypeOfIntervention typeOfIntervention, Model model, BindingResult bindingResult) {
    	
        this.typeOfInterventionValidator.validate(typeOfIntervention, bindingResult);
        
        if (!bindingResult.hasErrors()) {
            this.typeOfInterventionService.insert(typeOfIntervention);
            model.addAttribute("typeOfInterventions", this.typeOfInterventionService.allTypeOfIntervention());
            model.addAttribute("role", this.typeOfInterventionService.getCredentialsService().getRoleAuthenticated());
            return "redirect:/typeOfIntervention";
        }
        return "typeOfInterventionForm";
    }
}
