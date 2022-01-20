package it.uniroma3.siw.officina.controller;

import it.uniroma3.siw.officina.controller.validator.InterventionValidator;
import it.uniroma3.siw.officina.model.Intervention;
import it.uniroma3.siw.officina.service.InterventionService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InterventionController {
	
    @Autowired
    private InterventionService interventionService;
    
    @Autowired
    private InterventionValidator interventionValidator;
    

    @RequestMapping(value="/admin/intervention", method=RequestMethod.GET)
    public String addIntervention(Model model) {
        model.addAttribute("intervention", new Intervention());
        model.addAttribute("clients", this.interventionService.getUserService().getAllUsers());
        model.addAttribute("mechanicals", this.interventionService.getMechanicalService().allMechanical());
        model.addAttribute("typeOfInterventions", this.interventionService.getTypeOfInterventionService().allTypeOfIntervention());
        return "interventionForm";
    }

    @RequestMapping(value="/intervention/{id}", method=RequestMethod.GET)
    public String getIntervention(@PathVariable("id") Long id, Model model) {
        model.addAttribute("intervention", this.interventionService.interventionById(id));
        model.addAttribute("role", this.interventionService.getCredentialsService().getRoleAuthenticated());
        return "intervention";
    }

    @RequestMapping(value="/admin/interventions", method=RequestMethod.GET)
    public String getInterventions(Model model) {
        model.addAttribute("interventions", this.interventionService.allInterventions());
        model.addAttribute("role", this.interventionService.getCredentialsService().getRoleAuthenticated());
        return "interventions";
    }

    @RequestMapping(value="/interventions/client", method=RequestMethod.GET)
    public String getInterventionByClient(Model model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("interventions", this.interventionService.interventionByClient(this.interventionService.getUserService().getUserByUsername(userDetails.getUsername())));
        return "interventions";
    }

    @RequestMapping(value="/admin/intervention", method=RequestMethod.POST)
    public String addIntervention(@ModelAttribute("intervention") Intervention intervention,
    								Model model, BindingResult bindingResult) {
    	
        this.interventionValidator.validate(intervention, bindingResult);
        
        if (!bindingResult.hasErrors()) {
            intervention.setDateOfPrenotation(LocalDate.now());
            this.interventionService.insert(intervention);
            model.addAttribute("interventions", this.interventionService.allInterventions());
            model.addAttribute("role", this.interventionService.getCredentialsService().getRoleAuthenticated());
            return "redirect:/admin/interventions";
        }
        return "interventionForm";
    }
}