package org.manadev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.manadev.db.Utils.loadProperties;

public class DB {

    static         Properties props = loadProperties();
    private static Connection conn  = null;

    private DB() {}

    public static Connection getExistingConnection() {
        if (conn == null) {
            String user = props.getProperty("DB_USER");
            String pw = props.getProperty("DB_PASSWORD");

            getConnection("", user, pw);
        }
        return conn;
    }

    public static Connection getConnection(String dbName, String user, String password) throws DbException {
        try {
            String url = props.getProperty("DB_URL") + ((dbName.isBlank()) ? "postgres" : dbName);
            props.setProperty("user", user);
            props.setProperty("password", password);

            conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return conn;
    }
}
