package dao;

import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IOrderDetailDAO {
    private Connection connection;
    private static final String SELECT_QUERY = "SELECT * FROM OrderDetail";
    private static final String SHOW_BY_ORDER = "select * from orderdetail where idOrder = ?;";
    private static final String SHOW_BY_PRODUCT = "select * from product p\n" +
            "join orderdetail od on p.id = od.idProduct\n" +
            "where od.idOrder = ?";

    {
        try {
            connection = MySQLConnection.getConnection();
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
        while (rs.next()) {
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            list.add(new OrderDetail(idProduct, idOrder, quantity));
        }
        return list;
    }

    public List<OrderDetail> showOrderDetailByIdOrder(int id) throws SQLException {
        ps = connection.prepareStatement(SHOW_BY_ORDER);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            list.add(new OrderDetail(idProduct, idOrder, quantity));
        }
        return list;
    }
    private static final String SELECT_ORDERDETAIL_BY_IDORDER = "select * from OrderDetail where idOrder = ? and idProduct = ?";
    OrderDetail orderDetail = null;
    public OrderDetail orderDetailByIdOrder(int idO,int idP) throws SQLException {

        ps = connection.prepareStatement(SELECT_ORDERDETAIL_BY_IDORDER);
        ps.setInt(1, idO);
        ps.setInt(2, idP);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rs.next()) {
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            orderDetail = new OrderDetail(idProduct, idOrder, quantity) ;
        }
        return orderDetail;
    }
    private static final String SELECT_ORDERDETAIL_BY_IDPRODUCT = "select * from OrderDetail where idProduct = ?";
    public OrderDetail orderDetailByIdProduct(int id) throws SQLException {
        ps = connection.prepareStatement(SELECT_ORDERDETAIL_BY_IDPRODUCT);
        ps.setInt(1, id);
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (rs.next()) {
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            orderDetail = new OrderDetail(idProduct, idOrder, quantity) ;
        }
        return orderDetail;
    }

}