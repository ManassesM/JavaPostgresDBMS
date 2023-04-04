package org.manadev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.manadev.db.Utils.loadProperties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();

                String url = props.getProperty("DB_URL");
                props.setProperty("user", props.getProperty("DB_USER"));
                props.setProperty("password", props.getProperty("DB_PASSWORD"));

                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
}
