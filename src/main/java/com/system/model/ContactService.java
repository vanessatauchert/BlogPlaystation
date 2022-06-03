package com.system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContactService {

    @Autowired
    ContactDAO contactDAO;

    public void insertContact(Contact contact) {
        contactDAO.insert(contact);
    }

    public Map<String, Object> getContact(int id) {
        return contactDAO.getContact(id);
    }

    public List<Map<String, Object>> getMensagens() {
        return contactDAO.getMensagens();
    }

    public void delete(int id) {
        contactDAO.deleteContact(id);
    }

    public void redirectAfterEdit(int id, Contact contact) {
        contactDAO.update(id, contact);
    }
}