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
//    private static final String SELECT_QUERY_PRODUCT_TYPE = "SELECT * FROM type";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE `id` = ?";
    private static final String INSERT_QUERY_2 =
            "INSERT INTO product" +"(id,name,price,madeIn,image,quantity,idType) VALUE" + "(?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE product SET name=?, price=?, madeIn=?, image=?, quantity=?, idType =? WHERE `id`=? ";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE `id` = ?";

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
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY_2);
        ps.setString(1,product.getName());
        ps.setInt(2,product.getPrice());
        ps.setString(3, product.getMadeIn());
        ps.setString(4, product.getImage());
        ps.setInt(5, product.getQuantity());
        ps.setInt(6,product.getIdType());
        ps.executeUpdate();
    }


    @Override
    public Product select(String id) throws SQLException, ClassNotFoundException {
        Product product = null;
        PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String p_id = rs.getString("id");
            String p_name = rs.getString("name");
            String p_price = rs.getString("price");
            String p_madeIn = rs.getString("madeIn");
            String p_image = rs.getString("image");
            String p_quantity = rs.getString("quantity");
            String p_type = rs.getString("idType");
            product =
                    new Product(Integer.parseInt(p_id),
                            p_name,Integer.parseInt(p_price),
                            p_madeIn,p_image,
                            Integer.parseInt(p_quantity),
                            Integer.parseInt(p_type));
        }
        return product;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean recordDelete;
        PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
        ps.setString(1,id);
        recordDelete = ps.executeUpdate()>0;
        return recordDelete;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean updateRecord;
        PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
        ps.setString(1, product.getName());
        ps.setInt(2, product.getPrice());
        ps.setString(3, product.getMadeIn());
        ps.setString(4, product.getImage());
        ps.setInt(5, product.getQuantity());
        ps.setInt(6, product.getIdType());
        ps.executeUpdate();
        updateRecord = ps.executeUpdate()>0;
        return updateRecord;
    }


}
