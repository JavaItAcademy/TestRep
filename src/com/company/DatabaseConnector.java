package com.company;

    import java.awt.image.AreaAveragingScaleFilter;
    import java.sql.*;
    import java.util.ArrayList;

public class DatabaseConnector {
    public final String url = "jdbc:postgresql://localhost:5432/postgres";
    public final String user = "postgres";
    public final String password = "123";

    public Connection connect() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Successfully connected to the Database");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public int getTrainersCount(String tableName) {
        String SQL = "Select count(*) as cnt from " + tableName;
        int count = 0;

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt("cnt");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public int getTrainers() {
        String SQL = "Select full_name, trainer_id, phone_number from trainers";
        int count = 0;

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {

            while (rs.next()){
                System.out.println(rs.getInt("trainer_id") + " : "
                        + rs.getString("full_name")
                        + ", tel: "
                        + rs.getString("phone_number")) ;
            }


        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public int getStudentsCount() {
        String SQL = "select count(*) as studentsCount from students where full_name like '%а%'";
        int count = 0;

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt("studentsCount");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    public int getStudent(int searchId) {
        String SQL = "select * from students where id = "+ searchId + ";";
        int id = 0;

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            id = rs.getInt(1);
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public void printGroup(int searchId) {
        String SQL = "select * from groups where id = ? ;";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)
            ) {
            stmt.setInt(1, searchId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2));
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean insertHuman(Human human) {
        String SQL =
                "insert into humans (id,name) " +
                        "values (" + human.id +", '" + human.name + "')";

        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(SQL);

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertCountry(Country country) {
        String SQL = "insert into countries (id,name) values (?,?);";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, country.id);
            stmt.setString(2, country.name);
            stmt.executeUpdate();
            }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertCity(City city) {
        String SQL = "insert into cities (id,name, country_id, human_id) values (?, ?, ?, ?);";

        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, city.id);
            stmt.setString(2, city.name);
            stmt.setInt(3, city.country.id);
            stmt.setInt(4, city.human.id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();
        String SQL = "select \n" +
                "\tc.id as city_id, \n" +
                "\tc.name as city_name, \n" +
                "\tc.country_id, \n" +
                "\tcc.name as country_name, \n" +
                "\tc.human_id, \n" +
                "\th.name as human_name \n" +
                " from cities c join countries cc on c.country_id = cc.id\n" +
                " join humans h on c.human_id = h.id";
        try(Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            while(rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"));
                Human human = new Human(rs.getInt("human_id"), rs.getString("human_name"));
                City city = new City(rs.getInt("city_id"), rs.getString("city_name"), country, human);
                cities.add(city);
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }
}
