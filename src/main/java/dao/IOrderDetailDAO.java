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

    {
        try {
            connection = MySQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    List<OrderDetail> list = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public IOrderDetailDAO() {
    }

    public List<OrderDetail> allOrderDetail() throws SQLException {
        list = new ArrayList<>();
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
        list = new ArrayList<>();
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

    private static final String INSERT_ORDERDETAIL = "INSERT INTO orderdetail (`idProduct`, `idOrder`, `quantity`) VALUES (?,?,?)";

    public void insert(OrderDetail orderDetail) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_ORDERDETAIL);
        ps.setInt(1,orderDetail.getIdOrder());
        ps.setInt(2,orderDetail.getIdProduct());
        ps.setInt(3,orderDetail.getQuantity());
        ps.executeUpdate();
    }
    private static final String SELECT_BY_ORDERID="SELECT * FROM ORDERDETAIL WHERE IDORDER = ?";
    public OrderDetail select(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_BY_ORDERID);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int idProduct = rs.getInt("idProduct");
            int idOrder = rs.getInt("idOrder");
            int quantity = rs.getInt("quantity");
            return new OrderDetail(idProduct,idOrder,quantity);
        }
        return null;
    }
    private static final String DELETE_QUERY = "DELETE FROM orderdetail WHERE idProduct = ? and idOrder = ?";
    public boolean delete(int idProduct,int idOrder) throws SQLException {
        boolean recordDelete;
        PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
        ps.setInt(1,idProduct);
        ps.setInt(2,idOrder);
        recordDelete = ps.executeUpdate()>0;
        return recordDelete;
    }
    private static final String UPDATE_QUERY = "UPDATE orderdetail SET quantity = ? WHERE (`idProduct` = ?) and (`idOrder` = ?)";
    public boolean update(OrderDetail orderDetail) throws SQLException {
        boolean updateRecord ;
        PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
        ps.setInt(1,orderDetail.getQuantity());
        ps.setInt(2,orderDetail.getIdProduct());
        ps.setInt(3,orderDetail.getIdOrder());
        ps.executeUpdate();
        updateRecord = ps.executeUpdate()>0;
        return updateRecord;
    }


}
