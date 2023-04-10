package org.manadev.services;

import org.manadev.db.DB;
import org.manadev.db.DbException;
import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.DatabaseDAO;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.Database;
import org.manadev.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.StringFactory.printValue;

public class DatabaseService {

    DatabaseDAO databaseDAO = DAOFactory.createDatabaseDAO();
    UserDAO     userDAO     = DAOFactory.createUserDAO();

    public void listAll() {

        try {
            Connection connection = DB.getExistingConnection();

            String username = connection.getMetaData().getUserName();
            List<User> users = userDAO.findAll();
            int ownerId = 0;
            for (User user : users) {
                if (user.getUsername().contains(username)) {
                    ownerId = user.getUserId();
                }
            }

            List<Database> databaseList = databaseDAO.findAll(ownerId);

            printValue("Databases: ");
            for (Database database : databaseList) {
                System.out.println(database);
            }
            printValue("Total: " + databaseList.size());

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void createDatabase() {
        System.out.println("Quit at any time by typing 'quit prompt'");

        try {
            String dbName = getInputValue(String.class);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
