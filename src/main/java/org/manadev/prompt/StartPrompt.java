package org.manadev.prompt;

import org.manadev.services.UserService;

import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.InputFactory.quitPrompt;
import static org.manadev.utils.StringFactory.printValue;
import static org.manadev.utils.StringFactory.userOptions;

public class StartPrompt {
    UserService service = new UserService();

    public void startPrompt() {
        boolean flag = true;
        userOptions();

        do {
            int option = getInputValue(Integer.class);

            switch (option) {
                case 0 -> userOptions();
                case 1 -> service.listAll();
                case 2 -> service.findById();
                case 3 -> service.createUser();
                case 4 -> {
                    boolean connected = service.connect();
                    if (connected) new DatabasePrompt().databasePrompt();
                }
                default -> flag = quitPrompt("Exiting...");
            }
        } while (flag);

        printValue("Thank you for using JAVA Postgres DBMS");
    }
}
