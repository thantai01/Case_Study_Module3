package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private static final String LOGIN_QUERY = "SELECT name,password,role FROM `user` WHERE `name` =? AND `password` =?";
    private static Connection connection;

    public LoginDAO() {
        try {
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static User checkLogin(String userName, String userPassword) throws SQLException, ClassNotFoundException {
        try {
            connection = SQLConnection.getConnection();
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
        ps.setString(1, userName);
        ps.setString(2, userPassword);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new User(rs.getString(1), rs.getString(2),rs.getInt(3));
        }
        return null;
    }
}
