package org.manadev.services;

import org.manadev.db.DB;
import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.User;
import org.manadev.utils.InputFactory;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import static org.manadev.utils.StringFactory.*;

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

    public void createUser() {
        System.out.println("Quit at any time by typing 'quit prompt'");
        try {

            System.out.println("Username");
            String username = InputFactory.getInputValue(String.class);

            System.out.println("Password");
            String password = InputFactory.getInputValue(String.class);

            System.out.println("CreateDB (Y/N): ");
            boolean createDb = InputFactory.getInputValue(String.class).toLowerCase().charAt(0) == 'y';

            int userId = generateId();

            User user = new User(username, userId, createDb, password);
            userDAO.createUser(user);
            printValue("User created!");
            System.out.print(user + "\n\n");
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void connect() {
        List<User> users = userDAO.findAll();

        System.out.println("Username");
        String username = InputFactory.getInputValue(String.class);

        boolean foundUser = false;
        try {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    System.out.println("Password");
                    String password = InputFactory.getInputValue(String.class);
                    userDAO.connect(username, password);
                    printConnectionData(DB.getConnection());
                    foundUser = true;
                    break;
                }
            }
            if (!foundUser) printValue("User " + username + " not found!");
        } catch (SQLException e) {
            System.out.println("Something went wrong while retrieving data from Connection!\n" + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Something went wrong while connecting to database!\n" + e.getMessage());
        }
    }
}
