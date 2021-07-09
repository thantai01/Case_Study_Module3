package dao;

import model.Order;
import model.OrderDetail;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IOrderDAO {
    private Connection connection;

    {
        try {
            connection = MySQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    List<Order> list = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public IOrderDAO() {
    }

    private static final String SHOW_BY_Name = "SELECT * FROM casestudymodule3.order where userName like ?;";

    public List<Order> showOrderByName(String name) throws SQLException {
        list = new ArrayList<>();
        ps = connection.prepareStatement(SHOW_BY_Name);
        ps.setString(1, name);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String time = rs.getString("time");
            list.add(new Order(id, userName, time));
        }
        return list;
    }

    public Order showAllByUsername(String name) throws SQLException {
        list = new ArrayList<>();
        Order order = null;
        ps = connection.prepareStatement(SHOW_BY_Name);
        ps.setString(1, name);
        rs = ps.executeQuery();
        while (rs.next()) {
            int idOrder = rs.getInt("id");
            String userName = rs.getString("userName");
            String time = rs.getString("time");
            order = new Order(idOrder, userName, time);
        }
        return order;
    }

    private static final String FIND_BY_ID = "SELECT * FROM casestudymodule3.order where id = ?;";

    public Order findById(int id) throws SQLException {
        Order order = null;
        ps = connection.prepareStatement(FIND_BY_ID);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            int idOrder = rs.getInt("id");
            String userName = rs.getString("userName");
            String time = rs.getString("time");
            return order = new Order(idOrder, userName, time);
        }
        return null;
    }

    public List<Order> showListOrder(String name) throws SQLException, ClassNotFoundException {
        list = new ArrayList<>();
        IOrderDAO dao = new IOrderDAO();
        IOrderDetailDAO dao1 = new IOrderDetailDAO();
        IUserDAO dao2 = new IUserDAO();
        User user = dao2.select(name);
        List<Order> listOrder = dao.showOrderByName(user.getUserID());
        Order order = dao.showAllByUsername(user.getUserID());
        List<OrderDetail> listOrderDetail = null;
        listOrderDetail = dao1.showOrderDetailByIdOrder(order.getId());
        return listOrder;
    }

    private static final String INSERT_ORDERDETAIL = "INSERT INTO `order` (id, userName, time) VALUES (?,?,?)";

    public void insert(Order order) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_ORDERDETAIL);
        ps.setInt(1,order.getId());
        ps.setString(2,order.getUserName());
        ps.setString(3,order.getTime());
        ps.executeUpdate();
    }
}
