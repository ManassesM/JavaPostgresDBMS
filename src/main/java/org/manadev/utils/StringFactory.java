package org.manadev.utils;

public class StringFactory {

    public static void printValue(String value) {
        int width = value.length() + 4;
        System.out.printf("""
                                  %s
                                    %s
                                  %s%n""", "-".repeat(width), value, "-".repeat(width));
    }

    public static void userOptions() {
        System.out.print("""
                                 0. Show options
                                 1. List users
                                 2. Create new user
                                 3. Connect to existing user
                                 4. Quit
                                 """);
    }

    public static void exitingPrompt() {
        System.out.println("Exiting prompt...");
        printValue("Thank you for using JAVA Postgres DBMS");
    }
}
