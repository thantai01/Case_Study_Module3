package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IUserDAO implements DAO<User>{
    private static final String SELECT_QUERY = "SELECT * FROM db_project1.user";
    private static final String INSERT_QUERY_1 = "INSERT INTO `user`" + "(`name`,`password`,`role`) VALUE" + "(?,?,?)";
    private static final String INSERT_QUERY_2 =
            "INSERT INTO `user`" +"(`name`,password,address,fullname,sdt,`role`) VALUE" + "(?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "UPDATE `user` SET password=?, address=?, fullname=?, sdt=?,`role`=? WHERE `name`=? ";
    private static final String DELETE_QUERY = "DELETE FROM `user` WHERE `name` = ?";

    private Connection connection;
    {
        try{
            connection = MySQLConnection.getConnection();
        } catch (ClassNotFoundException|SQLException exception) {
            exception.printStackTrace();
        }
    }

    public IUserDAO(){};

    @Override
    public List<User> showALl() throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String userID = rs.getString("name");
            String userPassword = rs.getString("password");
            String userFullName = rs.getString("fullname");
            String userAddress = rs.getString("address");
            String userPhoneNum = rs.getString("sdt");
            int userRole = Integer.parseInt(rs.getString("role"));
            userList.add(new User(userID,userPassword,userFullName,userAddress,userPhoneNum,userRole));
        }
        return userList;
    }

    @Override
    public void insert(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY_1);
        ps.setString(1,user.getUserID());
        ps.setString(2,user.getUserPassword());
        ps.setString(3, String.valueOf(user.getRole()));
        ps.executeUpdate();
    }
    public void insert2(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY_2);
        ps.setString(1,user.getUserID());
        ps.setString(2,user.getUserPassword());
        ps.setString(3,user.getUserAddress());
        ps.setString(4,user.getUserFullName());
        ps.setString(5,user.getUserPhone());
        ps.setString(6, String.valueOf(user.getRole()));
        ps.executeUpdate();
    }
    @Override
    public User select(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        boolean recordDelete;
        PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
        ps.setString(1,name);
        recordDelete = ps.executeUpdate()>0;
        return recordDelete;
    }

    @Override
    public boolean update(User user) throws SQLException {
        boolean updateRecord;
        PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
        ps.setString(1, user.getUserPassword());
        ps.setString(2, user.getUserAddress());
        ps.setString(3, user.getUserFullName());
        ps.setString(4, user.getUserPhone());
        ps.setString(5, String.valueOf(user.getRole()));
        ps.executeUpdate();
        updateRecord = ps.executeUpdate()>0;
        return updateRecord;
    }

    @Override
    public User viewProduct(int id) throws SQLException {
        return null;
    }

    public List<User> userSearch(String name) throws SQLException {
        List<User> searchList =new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT `name`,password FROM `user` WHERE `name`=?");
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String userID = rs.getString("name");
            String userPassword = rs.getString("password");
            String userFullName = rs.getString("fullname");
            String userAddress = rs.getString("address");
            String userPhoneNum = rs.getString("sdt");
            int userRole = Integer.parseInt(rs.getString("role"));
            searchList.add(new User(userID,userPassword,userFullName,userAddress,userPhoneNum,userRole));
        }
        return searchList;
    }
    public void printQLException(SQLException exception) {
        for(Throwable e:exception) {
            if(e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State:" + ((SQLException) e).getSQLState());
                System.err.println("Error Code:" +((SQLException) e).getErrorCode());
                System.err.println("Message:" + e.getMessage());
                Throwable t = exception.getCause();
                while (t!=null) {
                    System.out.println("Cause" + t);
                    t=t.getCause();
                }
            }
        }
    }
}
