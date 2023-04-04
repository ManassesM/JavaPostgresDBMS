package org.manadev.prompt;

import org.manadev.services.UserService;

import java.util.Scanner;

public class Start {
    Scanner     sc      = new Scanner(System.in);
    UserService service = new UserService();
    public void startPrompt() {
        System.out.print("""
                                 1. List users
                                 2. Create new user
                                 3. Connect to existing user
                                 4. Quit
                                 """);

        System.out.print("Choose option: ");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> service.listAll(); // listUsers();
            case 2 -> System.out.println("create user"); // createUser();
            case 3 -> System.out.println("connect user"); // connect();
            default -> System.out.println("quit"); // quit
        }
    }
}
