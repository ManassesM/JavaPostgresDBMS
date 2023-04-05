package org.manadev.model.dao;

import org.manadev.db.DbException;
import org.manadev.model.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll() throws DbException;

    void createUser(User obj) throws DbException;

    void connect(String username, String password) throws DbException;
}
