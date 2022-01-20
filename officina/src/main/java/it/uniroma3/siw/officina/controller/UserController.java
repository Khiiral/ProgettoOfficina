package it.uniroma3.siw.officina.controller;

import it.uniroma3.siw.officina.model.User;
import it.uniroma3.siw.officina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
    @Autowired
    private UserService userService;
    

    @RequestMapping(value="/admin/client", method=RequestMethod.GET)
    public String getClients(Model model) {
        model.addAttribute("clients", this.userService.getAllUsers());
        model.addAttribute("role", this.userService.getCredentialsService().getRoleAuthenticated());
        return "clients";
    }

    @RequestMapping(value="/admin/editClient/{id}", method=RequestMethod.GET)
    public String editClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", this.userService.getUser(id));
        return "clientEditForm";
    }

    @RequestMapping(value="/admin/client/update/{id}", method=RequestMethod.POST)
    public String update(@ModelAttribute("user") User user, Model model) {
        this.userService.saveUser(user);
        return "redirect:/admin/client";
    }
}