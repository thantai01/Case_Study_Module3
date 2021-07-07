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
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    List<Order> list = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public IOrderDAO() {
    }
    private static final String SHOW_BY_Name = "SELECT * FROM casestudymodule3.order where userName like ?;";
    private static final String SHOW_BY_ID = "SELECT * FROM casestudymodule3.order where userName like ?;";
   public List<Order> showOrderByName(String name) throws SQLException {
        ps = connection.prepareStatement(SHOW_BY_Name);
        ps.setString(1,name);
        rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String time = rs.getString("time");
            list.add(new Order(id,userName,time));
        }
        return list;
   }
    public Order showAllOrderDetail(String name) throws SQLException {
       Order order =null;
       ps = connection.prepareStatement(SHOW_BY_ID);
       ps.setString(1,name);
       rs=ps.executeQuery();
       while (rs.next()){
           int idOrder = rs.getInt("id");
           String userName = rs.getString("userName");
           String time = rs.getString("time");
           order = new Order(idOrder,userName,time);
       }
       return order;
    }
    public List<Order> showListOrder() throws SQLException {
        IOrderDAO dao = new IOrderDAO();
        IOrderDetailDAO dao1 = new IOrderDetailDAO();
        IUserDAO dao2 = new IUserDAO();
        User user = dao2.selectUser("hung");
        List<Order> listOrder = dao.showOrderByName(user.getUserID());
        Order order = dao.showAllOrderDetail(user.getUserID());
        List<OrderDetail> listOrderDetail = null;
        listOrderDetail = dao1.showOrderDetailByIdOrder(order.getId());
        return listOrder;
    }

}
