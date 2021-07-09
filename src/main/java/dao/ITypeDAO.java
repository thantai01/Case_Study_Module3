package dao;

import model.Product;
import model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITypeDAO implements DAO<Type>{
    private static final String SELECT_TYPE_BY_ID = "select * from type where id = ?";
    private static final String SELECT_All_TYPE_QUERY = "SELECT * FROM type";
    private static final String LAST_QUERY = "select * from product \n" +
            "order by id desc\n" +
            "limit 1;";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
    private static final String SELECT_QUERY_PRODUCT_TYPE = "SELECT * FROM type";
    private static final String INSERT_INTO_TYPE_QUERY =
            "INSERT INTO type " +"(idType,name,description) VALUE" + "(?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE product SET name=?, price=?, madeIn=?, image=?, quantity=?, idType =? WHERE `id`=? ";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE `id` = ?";
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
        List<Type> typeList = new ArrayList<>();
        ps = connection.prepareStatement(SELECT_All_TYPE_QUERY);
        rs = ps.executeQuery();
        while (rs.next()) {
            int idType = rs.getInt("id");
            String nameType = rs.getString("name");
            String description = rs.getString("description");

            typeList.add(new Type(idType,nameType,description));
        }
        return typeList;
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

//    @Override
//    public Type viewProduct(int id)throws SQLException{
//        return null;
//    }

}
