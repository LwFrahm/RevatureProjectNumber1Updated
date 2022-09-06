package com.revature.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.lang.*;

public class ConnectionFactory {

    // limits access to this class
    private ConnectionFactory() {
    }

    private static Connection connection = null;

    public static Connection getConnection() {

        if(connection == null) {
            System.out.println("Connection is being created");

            // reads info from properties file
            ResourceBundle bundle = ResourceBundle.getBundle("DbConfig");

            // this will change depending on database name
            String url = bundle.getString("url");
            String user = bundle.getString("user");
            String password = bundle.getString("password");

            // put in manager??? leave it for now and possibly try to test it
            // Vikings2017 or with !?
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);

                // put in manager??? try without first

            } catch (SQLException e) {
                System.out.println("Something went wrong when creating connection");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
