package org.manadev.model.dao;

import org.manadev.model.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();

}
