package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    static String url = "jdbc:postgresql://localhost:5432/course";
    static String user = "postgres";
    static String password = "postgres";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
        return connection;
    }

    public static void close(Connection connection) throws SQLException {
        connection.close();
        System.out.println("Connection closed");
    }
}
