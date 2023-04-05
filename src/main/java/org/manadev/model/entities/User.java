package org.manadev.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private final String  username;
    private final int     userId;
    private final boolean createDb;
    private       String  password;

    public User(String username, int userId, boolean createDb) {
        this.username = username;
        this.userId   = userId;
        this.createDb = createDb;
    }

    public User(String username, int userId, boolean createDb, String password) {
        this.username = username;
        this.userId   = userId;
        this.createDb = createDb;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public int getUserId() {
        return userId;
    }
    public String isCreateDb() {
        return createDb ? "CREATEDB" : "NOCREATEDB";
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User: " + username + "\nID: " + userId + "\nCreate Database: " + createDb;
    }
}
