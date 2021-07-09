package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> showALl() throws SQLException, ClassNotFoundException;

    void insert(T t) throws SQLException, ClassNotFoundException;

    T select(String name) throws SQLException, ClassNotFoundException;

    boolean delete(String name) throws SQLException, ClassNotFoundException;

    boolean update(T t) throws SQLException;

    T viewProduct(int id) throws SQLException;
}
