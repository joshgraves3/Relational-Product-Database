package com.Normalization1;

import java.sql.*;


/**
 * Created by Josh on 4/1/16.
 */
public class DBConnection {


    public DBConnection() {}

    //public static void main(String[] args){}

    public Connection getConnection() {
        String url = "jdbc:mysql://us-cdbr-azure-west-c.cloudapp.net:3306/bikeinfodatabase";
        String user = "b67c222ee69958";
        String password = "ba6158f8";
        Connection conn = null;

        // Load the Connector/J driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



}
