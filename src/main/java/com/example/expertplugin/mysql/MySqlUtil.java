package com.example.expertplugin.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlUtil {

    private static final String url = "jdbc:mysql://localhost:3306/expert_plugin";
    private static final String user = "root";
    private static final String password = "sqlqlqjs31014*";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);
    }
}
