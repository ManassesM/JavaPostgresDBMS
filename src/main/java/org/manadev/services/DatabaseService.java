package org.manadev.services;

import org.manadev.model.dao.DAOFactory;
import org.manadev.model.dao.DatabaseDAO;
import org.manadev.model.entities.Database;

import java.util.List;

import static org.manadev.utils.StringFactory.printValue;

public class DatabaseService {

    DatabaseDAO databaseDAO = DAOFactory.createDatabaseDAO();

    public void listAll() {
        List<Database> databaseList = databaseDAO.findAll();

        printValue("Databases: ");
        for (Database database : databaseList) {
            System.out.println(database);
        }
        printValue("Total: " + databaseList.size());
    }
}
