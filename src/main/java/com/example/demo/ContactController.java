package com.example.demo;

import ch.qos.logback.core.net.server.Client;
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

import java.util.List;
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

    @GetMapping("/mensagens")
    public String listar(Model model) {
        ContactService cs = context.getBean(ContactService.class);
        List<Map<String, Object>> mensagens = cs.getMensagens();
        model.addAttribute("mensagens", mensagens);
        return "mensagens";
    }

    @PostMapping("/apagar/mensagens/{id}")
    public String apagarMsg(@PathVariable("id") int id) {
        ContactService cs = context.getBean(ContactService.class);
        cs.delete(id);
        return "redirect:/mensagens";
    }

    @GetMapping("/upd/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        ContactService cs = context.getBean(ContactService.class);
        Map<String, Object> regs = cs.getContact(id);
        Contact contact = new Contact(id,
                regs.get("name").toString(),
                regs.get("email").toString(),
                regs.get("msg").toString());
        model.addAttribute("contato", contact);
        model.addAttribute("id", id);
        return "update";
    }

    @PostMapping("/upd/{id}")
    public String redirectAfterEdit(@PathVariable("id") int id,
                                    Model model,
                                    @ModelAttribute Contact contact) {
        ContactService contactService = context.getBean(ContactService.class);
        contactService.redirectAfterEdit(id, contact);
        return "redirect:/mensagens";
    }
}