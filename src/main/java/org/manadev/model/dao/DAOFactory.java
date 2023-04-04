package org.manadev.model.dao;

import org.manadev.db.DB;
import org.manadev.repositories.UserRepositoryJDBC;

public class DAOFactory {

    public static UserDAO createUserDAO() {
        return new UserRepositoryJDBC(DB.getConnection());
    }
}
