package org.manadev.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Database implements Serializable {

    private final String dbName;
    private final int    ownerId;

    public Database(String dbName, int ownerId) {
        this.dbName  = dbName;
        this.ownerId = ownerId;
    }

    public String getDbName() {
        return dbName;
    }
    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database = (Database) o;
        return Objects.equals(dbName, database.dbName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dbName);
    }

    @Override
    public String toString() {
        return String.format("Database:\t%s %nOwner:\t\t%d %n-%n", dbName, ownerId);
    }
}
