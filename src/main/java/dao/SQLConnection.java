package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/casetest?useSSL=false";
    private static final String name = "root";
    private static final String password = "123456";
    protected static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection =null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL,name,password);
        return connection;
    }
}

