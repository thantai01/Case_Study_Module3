package dao;

import controller.ProductController;
import model.Order;
import model.OrderDetail;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IOrderDetailDAO{
    private Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM OrderDetail";
    private static final String SHOW_BY_ORDER = "select * from orderdetail where idOrder = ?;";
    private static final String SHOW_BY_PRODUCT = "select * from orderdetail where idProduct = ?;";
    {
        try {
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    List<OrderDetail> list = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    public IOrderDetailDAO() {
    }
    public List<OrderDetail> allOrderDetail() throws SQLException {
        ps = connection.prepareStatement(SELECT_QUERY);
        rs = ps.executeQuery();
        while (rs.next()){
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            list.add(new OrderDetail(idProduct,idOrder,quantity));
        }
        return list;
    }
    public List<OrderDetail> showOrderDetailByIdOrder(int id) throws SQLException {
        ps=connection.prepareStatement(SHOW_BY_ORDER);
        ps.setInt(1,id);
        rs = ps.executeQuery();
        while (rs.next()){
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            list.add(new OrderDetail(idProduct,idOrder,quantity));
        }
        return list;
    }
    public List<OrderDetail> showOrderDetailByIdProduct(int id) throws SQLException {
        ps=connection.prepareStatement(SHOW_BY_PRODUCT);
        ps.setInt(1,id);
        rs = ps.executeQuery();
        while (rs.next()){
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            list.add(new OrderDetail(idProduct,idOrder,quantity));
        }
        return list;
    }



}
