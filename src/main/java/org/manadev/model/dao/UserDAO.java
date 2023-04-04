package org.manadev.model.dao;

import org.manadev.model.entities.User;

import java.util.List;

public interface UserDAO {

    void insert(User obj);
    void deleteById(Integer id);
    User findById(Integer id);
    List<User> findAll();

}
