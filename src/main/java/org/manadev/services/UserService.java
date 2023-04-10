package org.manadev.services;

import org.manadev.db.DB;
import org.manadev.db.DbException;
import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.User;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.StringFactory.printConnectionData;
import static org.manadev.utils.StringFactory.printValue;

public class UserService {

    UserDAO userDAO = DAOFactory.createUserDAO();

    public void listAll() {
        List<User> users = userDAO.findAll();

        printValue("Users: ");
        for (User user : users) {
            System.out.print(user);
        }
        printValue("Total: " + users.size());
    }

    public void findById() {
        try {
            int userId = getInputValue(Integer.class);
            User user = userDAO.findById(userId);
            System.out.println("\n" + user);
        } catch (DbException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser() {
        System.out.println("Quit at any time by typing 'quit prompt'");

        try {
            List<User> users = userDAO.findAll();

            System.out.println("Username");
            String username = getInputValue(String.class);

            for (User user : users) {
                if (user.getUsername().contains(username))
                    throw new InputMismatchException("User with this name already exists!");
            }

            System.out.println("Password");
            String password = getInputValue(String.class);

            System.out.println("CreateDB (Y/N): ");
            boolean createDb = getInputValue(String.class).toLowerCase().charAt(0) == 'y';

            User user = new User(username, createDb, password);
            userDAO.createUser(user);
            printValue("User created!");
            System.out.print(user + "\n\n");
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean connect() {
        boolean foundUser = false;
        System.out.println("Quit at any time by typing 'quit prompt'");

        try {
            List<User> users = userDAO.findAll();

            System.out.println("Username");
            String username = getInputValue(String.class);

            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Password");
                    String password = getInputValue(String.class);

                    userDAO.connect(username, password);
                    printConnectionData(DB.getExistingConnection());

                    foundUser = true;
                    break;
                }
            }
            if (!foundUser) printValue("User '" + username + "' not found!");
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Something went wrong while retrieving data from Connection!\n" + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Something went wrong while connecting to database!\n" + e.getMessage());
        }
        return foundUser;
    }
}
