package org.manadev.services;

import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.User;

import java.util.List;

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
}
