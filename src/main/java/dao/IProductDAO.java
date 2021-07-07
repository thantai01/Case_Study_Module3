package dao;

import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IProductDAO implements DAO<Product> {
    private static final String SELECT_QUERY = "SELECT * FROM product";
    private static final String LAST_QUERY = "select * from product \n" +
            "order by id desc\n" +
            "limit 1;";
    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
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

    public IProductDAO() {
    }


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
            products.add(new Product(productId, productName, price, madeIn, image, quantity, idType));
        }
        return products;
    }

    public Product showLastProduct() throws SQLException {
        Product product = null;
        ps = connection.prepareStatement(LAST_QUERY);
        try {
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (rs.next()) {
            int productId = rs.getInt("id");
            String productName = rs.getString("name");
            int price = rs.getInt("price");
            String madeIn = rs.getString("madeIn");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            int idType = rs.getInt("idType");
            product = new Product(productId, productName, price, madeIn, image, quantity, idType);
        }
        return product;
    }

//        public static void main(String[] args) throws SQLException {
//        IProductDAO dao = new IProductDAO();
//        System.out.println(dao.viewProduct(3));;
//    }
    public Product viewProduct(int id) throws SQLException {
        Product product = null;
        ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
        ps.setInt(1, id);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rs.next()) {
            int productId = rs.getInt("id");
            String productName = rs.getString("name");
            int price = rs.getInt("price");
            String madeIn = rs.getString("madeIn");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            int idType = rs.getInt("idType");
            product = new Product(productId, productName, price, madeIn, image, quantity, idType);
        }
        return product;
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
