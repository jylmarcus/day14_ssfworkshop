package sg.visa.ssf.day13_ssfworkshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import sg.visa.ssf.day13_ssfworkshop.model.Contacts;
import sg.visa.ssf.day13_ssfworkshop.repository.ContactsRedis;

@Controller
public class formController {

    @Autowired
    ContactsRedis repository;

    @Value("${data.dir}")
    private String dataDir;

    @GetMapping(path="/addContact")
    public String getContactObject(Model model){
        model.addAttribute("contact", new Contacts());
        return "addContact";
    }

    @PostMapping (consumes="application/x-www-form-urlencoded", path="/contact") 
    public String saveContact(@Valid @ModelAttribute("contact") Contacts contact, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "addContact";
        }

        repository.create(contact, model);
        model.addAttribute("successMessage", "Contact saved successfully, with status code: " +HttpStatus.CREATED +".");
        return "showContact";
    }

    @GetMapping("/contacts/{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        Optional<Object> contactOpt = repository.getContact(contactId);
        contactOpt.ifPresentOrElse(
            contact -> model.addAttribute("contact", Contacts.class.cast(contact)),
            () -> model.addAttribute("errorMessage", "Contact not found")
        );

        if(!contactOpt.isPresent()) {
            return "error";
        }
        return "showContact";
    }

    @GetMapping("/contacts")
    public String showContacts(Model model){
        List<Contacts> contactList = repository.getAllContacts(model);
        model.addAttribute("contactList", contactList);
        return "contacts";
    }
}
