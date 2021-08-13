package com.company.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "Irbites2021!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
