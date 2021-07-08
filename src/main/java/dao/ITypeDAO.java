package dao;

import model.Product;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ITypeDAO implements DAO<Type>{
    private static final String SELECT_TYPE_BY_ID = "select * from type where id = ?";
    private Connection connection;

    {
        try {
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    public Type viewType(int id) throws SQLException {
        Type type = null;
        ps = connection.prepareStatement(SELECT_TYPE_BY_ID);
        ps.setInt(1, id);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rs.next()) {
            int typeID = rs.getInt("id");
            String typeName = rs.getString("name");
          String description = rs.getString("description");
                type = new Type(typeID,typeName,description);
        }
        return type;
    }

    @Override
    public List<Type> showALl() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void insert(Type type) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Type select(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Type type) throws SQLException {
        return false;
    }

    @Override
    public Type viewProduct(int id) {
        return null;
    }
}
