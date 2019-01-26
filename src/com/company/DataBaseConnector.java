package com.company;

import java.sql.*;
import java.util.ArrayList;

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

    //---------------   NEW -------------

    public static void insertCity(City city){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "insert into cities values(?,?,?,?)";
//        String SQL = "insert into cities values(" + city.id + ", " + city.title + ", " + city.country_id + ", " + city.mayor_id;
        try(Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, city.id);
            stmt.setString(2, city.title);
            stmt.setInt(3, city.country_id);
            stmt.setInt(4, city.mayor_id);
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void insertCountry(Country country){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "insert into country values(?,?)";
//        String SQL = "insert into country values(" + country.id + ", " + country.title");")
        try(Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1, country.id);
            stmt.setString(2, country.title);
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void insertHuman(Human human){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "insert into human values(?,?)";
        try(Connection conn = db.connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)
        ){
            stmt.setInt(1,  human.id );
            stmt.setString(2, human.fullname);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void fillArrayListCountry(ArrayList<Country> a){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "select * from country";
        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)
        ){
            while(rs.next()){
                a.add(new Country(rs.getInt("id"), rs.getString("title")));
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void fillArrayListCities(ArrayList<City> a){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "select * from cities";
        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)
        ){
            while(rs.next()){
                a.add(new City(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("country_id"),
                        rs.getInt("mayor_id")));
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void fillArrayListHumans(ArrayList<Human> a){
        DataBaseConnector db = new DataBaseConnector();
        String SQL = "select * from human";
        try(Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)
        ){
            while(rs.next()){
                a.add(new Human(rs.getInt("id"),
                        rs.getString("fullname")));
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
