package com.system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ContactDAO {

    @Autowired
    DataSource dataSource;
    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void insert(Contact contact) {
        String sql = "INSERT INTO form(name,msg,email)" +
                " VALUES (?,?,?)";
        Object[] obj = new Object[3];

        obj[0] = contact.getName();
        obj[1] = contact.getMsg();
        obj[2] = contact.getEmail();

        jdbc.update(sql, obj);
    }
}