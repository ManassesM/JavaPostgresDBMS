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

public class DatabaseRepositoryJDBC implements DatabaseDAO {

    private final Connection conn;

    public DatabaseRepositoryJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Database> findAll() throws DbException {
        List<Database> databaseList = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM pg_database";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String dbName = rs.getString("datname");
                int ownerId = rs.getInt("oid");

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
    public void createDatabase(Database db) throws DbException {

    }
    @Override
    public void deleteDatabase(Integer id) throws DbException {

    }
    @Override
    public void createDatabase(Integer id, Database db) throws DbException {

    }
}
