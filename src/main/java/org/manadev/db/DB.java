package org.manadev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.manadev.db.Utils.loadProperties;

public class DB {

    private static Connection conn;

    public static Connection getConnection() {
        return conn;
    }

    public static Connection getConnection(String url, String user, String password) throws DbException {
        try {
            Properties props = loadProperties();

            props.setProperty("user", user);
            props.setProperty("password", password);

            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return conn;
    }
}
