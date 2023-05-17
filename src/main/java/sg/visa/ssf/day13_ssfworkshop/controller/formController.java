package sg.visa.ssf.day13_ssfworkshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.visa.ssf.day13_ssfworkshop.model.Contacts;

@Controller
@RequestMapping(path="/")
public class formController {

    @GetMapping
    public String bindContact(Model model){
        model.addAttribute("contact", new Contacts());
        return "index";
    }

    @PostMapping (consumes="application/x-www-form-urlencoded", path="/contact") 
    public String saveContact(@Valid Contacts contact, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "index";
        }

        model.addAttribute("successMessage", "Contact saved successfully, with status code: " +HttpStatus.CREATED +".");
        return "index";
    }
}
