package org.manadev.model.dao;

import org.manadev.db.DbException;
import org.manadev.model.entities.Database;

import java.util.List;

public interface DatabaseDAO {

    List<Database> findAllByOwner(int ownerId) throws DbException;

    void dropDB(String dbName) throws DbException;

    void createDB(Database db) throws DbException;
}
