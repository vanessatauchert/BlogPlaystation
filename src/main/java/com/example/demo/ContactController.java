package com.example.demo;

import com.system.model.Contact;
import com.system.model.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@ComponentScan("com.system.model")
public class ContactController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/p3")
    public String contact(Model model) {
        model.addAttribute("contato", new Contact(0, "", "", ""));
        return "pagina3";
    }

    @PostMapping("/p3")
    public String insertMessage(@ModelAttribute Contact c) {
        ContactService cs = context.getBean(ContactService.class);
        cs.insertContact(c);
        return "success";
    }

    @GetMapping("/admin/{id}")
    public String getComment(@PathVariable("id") int id, Model model) {
        ContactService contactService = context.getBean(ContactService.class);
        Map<String, Object> map = contactService.getContact(id);
        model.addAttribute("id", id);
        model.addAttribute("name", map.get("name"));
        model.addAttribute("email", map.get("email"));
        model.addAttribute("msg", map.get("msg"));
        return "admin";
    }

}