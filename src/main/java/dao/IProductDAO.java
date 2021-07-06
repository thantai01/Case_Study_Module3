package dao;

import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IProductDAO implements DAO<Product>{
    private static final String SELECT_QUERY = "SELECT * FROM product";
    private static final String INSERT_QUERY_2 =
            "INSERT INTO product" +"(id,name,price,madeIn,image,quantity,idType) VALUE" + "(?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE `user` SET password=?, address=?, fullname=?, sdt=?,`role`=? WHERE `name`=? ";
    private static final String DELETE_QUERY = "DELETE `user` WHERE `name` = ?";

    private Connection connection;
    {
        try{
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException|SQLException exception) {
            exception.printStackTrace();
        }
    }
    PreparedStatement ps =null;
    ResultSet rs = null;
    public IProductDAO(){};

    @Override
    public List<Product> showALl() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
         ps = connection.prepareStatement(SELECT_QUERY);
         rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("id");
            String productName = rs.getString("name");
            int price = rs.getInt("price");
           String madeIn = rs.getString("madeIn");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            int idType = rs.getInt("idType");
            products.add(new Product(productId,productName,price,madeIn,image,quantity,idType));
        }
        return products;
    }

    @Override
    public void insert(Product product) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Product select(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }


}
