package org.manadev.model.dao;

import org.manadev.db.DB;
import org.manadev.repositories.DatabaseRepositoryJDBC;
import org.manadev.repositories.UserRepositoryJDBC;

import java.sql.Connection;

public class DAOFactory {
    static Connection conn = DB.getExistingConnection();

    public static UserDAO createUserDAO() {
        return new UserRepositoryJDBC(conn);
    }

    public static DatabaseDAO createDatabaseDAO() {
        return new DatabaseRepositoryJDBC(conn);
    }
}
