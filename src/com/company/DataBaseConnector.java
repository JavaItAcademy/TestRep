package com.company;

import java.sql.*;

public class DataBaseConnector {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "1267476";
    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void getTrainers(){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "SELECT id, title from courses";

        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while(rs.next()){
                System.out.println(rs.getInt("id") + " || "
                        + rs.getString("title"));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
