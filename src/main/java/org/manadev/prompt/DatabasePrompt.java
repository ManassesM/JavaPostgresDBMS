package org.manadev.prompt;

import org.manadev.services.DatabaseService;

import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.InputFactory.quitPrompt;
import static org.manadev.utils.StringFactory.databaseOptions;

public class DatabasePrompt {

    DatabaseService service = new DatabaseService();

    public void databasePrompt() {
        boolean flag = true;
        databaseOptions();

        do {
            int option = getInputValue(Integer.class);

            switch (option) {
                case 0 -> databaseOptions();
                case 1 -> service.listAll();
                case 2 -> System.out.println("createDB");
                case 3 -> System.out.println("deleteDB");
                case 4 -> System.out.println("updateDB");
                default -> flag = quitPrompt("Returning to user prompt...");
            }
        } while (flag);
    }
}
