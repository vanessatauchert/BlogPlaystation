package com.system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDAO {

    @Autowired
    DataSource dataSource;
    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
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

    public Map<String, Object> getContact(int id) {
        String sql = "SELECT * FROM form WHERE form.id = ?";
        Object[] obj = new Object[1];
        obj[0] = id;

        return jdbc.queryForMap(sql, obj);
    }

    public List<Map<String, Object>> getMensagens() {
        String sql = "SELECT * FROM form";
        List<Map<String, Object>> msg = jdbc.queryForList(sql);
        return msg;
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM form WHERE id = ?";
        Object[] obj = new Object[1];
        obj[0] = id;
        jdbc.update(sql, obj);
    }

    public void update(int id, Contact contact) {
        String sql = "UPDATE form SET name = ?, email = ?, msg = ? WHERE id = ?";
        Object[] obj = new Object[4];
        obj[0] = contact.getName();
        obj[1] = contact.getEmail();
        obj[2] = contact.getMsg();
        obj[3] = id;
        jdbc.update(sql, obj);
    }
}