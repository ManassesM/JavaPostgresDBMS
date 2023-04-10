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

import static org.manadev.db.Utils.getOwnerId;
import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.StringFactory.printValue;

public class DatabaseService {

    DatabaseDAO databaseDAO = DAOFactory.createDatabaseDAO();
    UserDAO     userDAO     = DAOFactory.createUserDAO();
    Connection  connection  = DB.getExistingConnection();

    public void listAll() {

        try {
            List<Database> databaseList = databaseDAO.findAllByOwner(ownerId());

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

            System.out.println("Database name");
            String dbName = getInputValue(String.class);
            Database database = new Database(dbName, ownerId());

            databaseDAO.createDB(database);

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void dropDatabase() {
        System.out.println("Database name");
        String dbName = getInputValue(String.class);
        databaseDAO.dropDB(dbName);
    }

    // utils

    private int ownerId() throws SQLException {
        String username = connection.getMetaData().getUserName();
        List<User> users = userDAO.findAll();

        return getOwnerId(users, username);
    }
}
