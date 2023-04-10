package org.manadev.repositories;

import org.manadev.db.DbException;
import org.manadev.db.Utils;
import org.manadev.model.dao.DatabaseDAO;
import org.manadev.model.entities.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.manadev.utils.StringFactory.printValue;

public class DatabaseRepositoryJDBC implements DatabaseDAO {

    private final Connection conn;

    public DatabaseRepositoryJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Database> findAllByOwner(int ownerId) throws DbException {
        List<Database> databaseList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM pg_database WHERE datdba = " + ownerId;

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String dbName = rs.getString("datname");
                databaseList.add(new Database(dbName, ownerId));
            }

            return databaseList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
            Utils.closeResultSet(rs);
        }

    }

    private List<Database> findAll() throws DbException {
        List<Database> databaseList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM pg_database";

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String dbName = rs.getString("datname");
                int ownerId = rs.getInt("datdba");
                databaseList.add(new Database(dbName, ownerId));
            }

            return databaseList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
            Utils.closeResultSet(rs);
        }

    }

    @Override
    public void createDB(Database db) throws DbException {
        List<Database> databases = findAll();
        Statement st = null;

        for (Database database : databases) {
            if (database.getDbName().contains(db.getDbName()))
                throw new DbException("Database '" + db.getDbName() + "' already exists!");
        }

        String sql = "CREATE DATABASE " + db.getDbName();

        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
        }
    }

    @Override
    public void dropDB(String dbName) throws DbException {
        List<Database> databases = findAll();
        Statement st = null;
        boolean foundDb = false;

        String sql = "DROP DATABASE " + dbName;

        try {

            for (Database database : databases) {
                if (database.getDbName().contains(dbName)) {
                    st = conn.createStatement();
                    st.execute(sql);
                    printValue("Database '" + dbName + "' deleted successfully!");
                    foundDb = true;
                    break;
                }
            }

            if (!foundDb) printValue("Database '" + dbName + "' does not exist!");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
        }
    }
}
