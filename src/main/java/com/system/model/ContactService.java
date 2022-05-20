package com.system.model;

import org.springframework.beans.factory.annotation.Autowired;

public class ContactService {

    @Autowired
    ContactDAO contactDAO;

    public void insertContact(Contact contact) {
        contactDAO.insert(contact);
    }
}
