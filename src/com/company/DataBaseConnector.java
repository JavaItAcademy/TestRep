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
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void getTrainers(){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "select t.trainer_id, t.fullname, s.salary from trainers t join sports s on t.sport_id = s.sport_id";

        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while(rs.next()){
                String fullname = rs.getString("fullname");
                System.out.print("\n"+rs.getInt("trainer_id") + " || "
                        + fullname + " -- "
                        + rs.getInt("salary"));
                if(containsMoreThanThreeSimbols(fullname))
                    System.out.print(" -- Молодец!!!");
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static double getTrainersTotalSalary(){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "select s.salary from trainers t join sports s on t.sport_id = s.sport_id";
        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            double totalSalary = 0;
            while(rs.next()){
                totalSalary += rs.getInt("salary");
            }
            return totalSalary;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public static boolean containsMoreThanThreeSimbols(String text) {
        boolean test = false;
        int n = text.indexOf(" ");
        int charCount = 0;

        for( int i = n+1; i < text.length(); i++ )
        {
            charCount++;
        }
        if(charCount >3)
            test = true;
        return test;
    }

}
