package org.manadev.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private final String  username;
    private final int     userId;
    private final boolean createDb;

    public User(String username, int userId, boolean createDb) {
        this.username = username;
        this.userId   = userId;
        this.createDb = createDb;
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
