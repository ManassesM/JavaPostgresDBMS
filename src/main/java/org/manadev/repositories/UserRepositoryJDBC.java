package org.manadev.repositories;

import org.manadev.db.DB;
import org.manadev.db.DbException;
import org.manadev.db.Utils;
import org.manadev.model.dao.UserDAO;
import org.manadev.model.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJDBC implements UserDAO {

    private final Connection conn;

    public UserRepositoryJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<User> findAll() throws DbException {
        List<User> users = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM pg_user";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("usename");
                int userId = rs.getInt("usesysid");
                boolean createDb = rs.getBoolean("usecreatedb");

                users.add(new User(username, userId, createDb));
            }
            return users;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
            Utils.closeResultSet(rs);
        }
    }

    @Override
    public User findById(int userId) throws DbException {
        User user;
        Statement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM pg_user WHERE usesysid = " + userId;

            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (!rs.next()) throw new DbException("No user found for this id: '" + userId + "'");
            String username = rs.getString("usename");
            int id = rs.getInt("usesysid");
            boolean createDb = rs.getBoolean("usecreatedb");

            user = new User(username, id, createDb);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            Utils.closeStatement(st);
            Utils.closeResultSet(rs);
        }
        return user;
    }

    @Override
    public void createUser(User obj) throws DbException {
        String sql = "CREATE ROLE " + obj.getUsername() + " WITH LOGIN NOSUPERUSER NOINHERIT " + obj.isCreateDb() + " NOCREATEROLE NOREPLICATION PASSWORD '" + obj.getPassword() + "'";
        Statement st = null;

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
    public void connect(String username, String password) throws DbException {
        DB.getConnection("", username, password);
    }
}
