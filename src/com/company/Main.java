package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
TryCon tryCon=new TryCon();
//tryCon.connect();

        tryCon.getFromTrainers();
System.out.println("Сумма всех зарплат тренеров: "+tryCon.getSum()) ;
tryCon.nameOfTrainer();
    }
}
class TryCon {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "2283";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server succesfully. ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public String getFromTrainers() {
        String SQL = "select t.full_name, s.sport_type, s.s_salary\n" +
                "from \n" +
                "trainers5 t join sport_types5 s on t.sport_id= s.sport_id\n  ";
        String count = "";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()){
                System.out.println(rs.getString("s_salary")+" "
                        + rs.getString("full_name"));
            }
            count = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public int getSum(){
            String SQL = "\tselect sum(s_salary) from sport_types5 \n";
            int count = 0;
            try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
                rs.next();
                System.out.println(count = rs.getInt(1));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return count;
    }
    public String nameOfTrainer(){
        String SQL = "select t.full_name from trainers5 t";
        String count = "";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()){
                count=(rs.getString("full_name"));
                if(count.contains(" ")){
                    count= count.substring(0, count.indexOf(" "));
                    if(count.length()>3){
                        System.out.println(count+" Молодец!");
                    }
                }
            }
            count = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    }


