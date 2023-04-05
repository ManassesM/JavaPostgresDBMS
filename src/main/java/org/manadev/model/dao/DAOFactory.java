package org.manadev.model.dao;

import org.manadev.db.DB;
import org.manadev.repositories.UserRepositoryJDBC;

import java.util.Properties;

import static org.manadev.db.Utils.loadProperties;

public class DAOFactory {

    public static UserDAO createUserDAO() {
        Properties props = loadProperties();
        return new UserRepositoryJDBC(DB.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USER"), props.getProperty("DB_PASSWORD")));
    }
}
