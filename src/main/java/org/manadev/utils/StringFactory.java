package org.manadev.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Random;

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

    public static int generateId() {
        Random random = new Random();
        long timestamp = System.currentTimeMillis();
        int randomInt = random.nextInt(1000000);
        return (int) (timestamp * 1000000 + randomInt);
    }

    public static void printConnectionData(Connection conn) throws SQLException {
        DatabaseMetaData metadata = conn.getMetaData();
        String block = String.format("URL: %s\n  User: %s", metadata.getURL(), metadata.getUserName());
        printValue(block);
    }
}
