package org.manadev.model.dao;

import org.manadev.db.DbException;
import org.manadev.model.entities.Database;

import java.util.List;

public interface DatabaseDAO {

    List<Database> findAll(int ownerId) throws DbException;

    void createDatabase(Database db) throws DbException;

    void deleteDatabase(Integer id) throws DbException;

    void createDatabase(Integer id, Database db) throws DbException;

}
