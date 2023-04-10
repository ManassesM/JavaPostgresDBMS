package org.manadev.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

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
                                 2. Find user
                                 3. Create new user
                                 4. Connect to existing user
                                 5. Quit
                                 """);
    }

    public static void databaseOptions() {
        System.out.print("""
                                 0. Show options
                                 1. List databases
                                 2. Create new database
                                 3. Delete database
                                 4. Update database
                                 5. Quit
                                 """);
    }

    public static void printConnectionData(Connection conn) throws SQLException {
        DatabaseMetaData metadata = conn.getMetaData();
        String block = String.format("URL: %s\n  User: %s", metadata.getURL(), metadata.getUserName());
        printValue(block);
    }
}
