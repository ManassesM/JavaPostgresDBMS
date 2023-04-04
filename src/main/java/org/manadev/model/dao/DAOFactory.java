package org.manadev.model.dao;

import org.manadev.db.DB;
import org.manadev.implementation.UserDAOJDBC;

public class DAOFactory {

    public static UserDAO createUserDAO() {
        return new UserDAOJDBC(DB.getConnection());
    }
}
