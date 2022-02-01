package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Util {

    public static Connection connect() {
        String userName = "root";
        String password = "1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/mysql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =  DriverManager.getConnection(connectionUrl, userName, password);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

