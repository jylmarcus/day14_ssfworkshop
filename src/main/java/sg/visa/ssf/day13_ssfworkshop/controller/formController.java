package sg.visa.ssf.day13_ssfworkshop.controller;

import java.util.List;

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
import sg.visa.ssf.day13_ssfworkshop.service.createContact;

@Controller
public class formController {

    @Autowired
    createContact service;

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

        service.create(contact, dataDir, model);
        model.addAttribute("successMessage", "Contact saved successfully, with status code: " +HttpStatus.CREATED +".");
        return "showContact";
    }

    @GetMapping("/contacts/{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        List<Contacts> contactList = service.getContacts(dataDir);
        Contacts returnContact = contactList.stream().filter(contact -> contactId.equals(contact.getId())).findFirst().orElse(null);
        if (returnContact == null) {
            model.addAttribute("errorMessage", "Contact not found");
            return "error";
        } 
        model.addAttribute("contact", returnContact);
        return "showContact";

    }

    @GetMapping("/contacts")
    public String showContacts(Model model){
        List<Contacts> contactList = service.getContacts(dataDir);
        model.addAttribute("contactList", contactList);
        return "contacts";
    }
}
