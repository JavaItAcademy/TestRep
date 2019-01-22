package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

    }

    public Connection connect(){
        final String url = "jdbc:postgresql://localhost:5432/";
        final String login = "postgres";
        final String password = "umarlove";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url , login , password);
            System.out.println("Connected to the PostgreSQl server successfully.");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
