package org.manadev.prompt;

import org.manadev.services.UserService;

import static org.manadev.utils.InputFactory.getInputValue;
import static org.manadev.utils.InputFactory.quitPrompt;
import static org.manadev.utils.StringFactory.userOptions;

public class Start {
    UserService service = new UserService();

    public void startPrompt() {
        boolean flag = true;
        userOptions();

        do {
            int option = getInputValue(Integer.class);

            switch (option) {
                case 0 -> userOptions();
                case 1 -> service.listAll();
                case 2 -> service.createUser();
                case 3 -> service.connect();
                default -> flag = quitPrompt();
            }
        } while (flag);
    }
}
