package org.manadev.services;

import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.User;
import org.manadev.utils.InputFactory;

import java.util.List;

import static org.manadev.utils.StringFactory.generateId;
import static org.manadev.utils.StringFactory.printValue;

public class UserService {

    UserDAO userDAO = DAOFactory.createUserDAO();

    public void listAll() {
        List<User> users = userDAO.findAll();

        printValue("Users: ");
        for (User user : users) {
            System.out.printf(user + "\n-\n");
        }
        printValue("Total: " + users.size());
    }

    public void createUser() {
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
    }
}
