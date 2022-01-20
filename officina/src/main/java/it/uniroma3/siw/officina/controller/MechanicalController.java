package it.uniroma3.siw.officina.controller;

import it.uniroma3.siw.officina.controller.validator.MechanicalValidator;
import it.uniroma3.siw.officina.model.Mechanical;
import it.uniroma3.siw.officina.service.MechanicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MechanicalController {
	
    @Autowired
    private MechanicalService mechanicalService;
    
    @Autowired
    private MechanicalValidator mechanicalValidator;
    

    @RequestMapping(value="/admin/mechanical", method=RequestMethod.GET)
    public String addMechanical(Model model) {
        model.addAttribute("mechanical", new Mechanical());
        return "mechanicalForm";
    }

    @RequestMapping(value="/mechanicalAll", method=RequestMethod.GET)
    public String getMechanicalsAll(Model model) {
        model.addAttribute("mechanicals", this.mechanicalService.allMechanical());
        return "mechanicalsAll";
    }

    @RequestMapping(value="/mechanical", method=RequestMethod.GET)
    public String getMechanicals(Model model) {
        model.addAttribute("mechanicals", this.mechanicalService.allMechanical());
        model.addAttribute("role", this.mechanicalService.getCredentialsService().getRoleAuthenticated());
        return "mechanicals";
    }

    @RequestMapping(value="/mechanical/{id}", method=RequestMethod.GET)
    public String getMechanical(@PathVariable("id") Long id, Model model) {
        model.addAttribute("mechanical", this.mechanicalService.mechanicalById(id));
        model.addAttribute("role", this.mechanicalService.getCredentialsService().getRoleAuthenticated());
        return "mechanical";
    }

    @RequestMapping(value="/admin/mechanical", method=RequestMethod.POST)
    public String addMechanical(@ModelAttribute("mechanical") Mechanical mechanical, Model model, BindingResult bindingResult) {
    	
        this.mechanicalValidator.validate(mechanical, bindingResult);
        
        if (!bindingResult.hasErrors()) {
            this.mechanicalService.insert(mechanical);
            model.addAttribute("mechanicals", this.mechanicalService.allMechanical());
            model.addAttribute("role", this.mechanicalService.getCredentialsService().getRoleAuthenticated());
            return "redirect:/mechanical";
        }
        return "mechanicalForm";
    }
}
