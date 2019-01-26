package com.company;
import java.sql.*;
import java.util.ArrayList;

public class TryCon1 {
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

    public boolean insertCities(Cities cities) {
        String SQL = "insert into cities (id,name,country_id, human_id) values (?,?,?,?);";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, cities.id);
            stmt.setString(2, cities.name);
            stmt.setInt(3, cities.country.id);
            stmt.setInt(4, cities.human.id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<Cities> getCities() {
        ArrayList<Cities> cities = new ArrayList<>();
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
                Countries country = new Countries(rs.getInt("country_id"), rs.getString("country_name"));
                Human human = new Human(rs.getInt("human_id"), rs.getString("human_name"));
                Cities city = new Cities(rs.getInt("city_id"), rs.getString("city_name"), country, human);
                cities.add(city );
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cities;
    }

    public boolean insertCountry(Countries country) {
        String SQL = "insert into countries (id,name) values (?,?);";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, country.id);
            stmt.setString(2, country.name);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertHuman(Human human) {
        String SQL =
                "insert into humans (id,name) " +
                        "values (" + human.id + ", '" + human.name + "')";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean updateCity(Cities city) {
       String SQL = "UPDATE cities SET id = (SELECT id FROM humans WHERE humans.id = cities.id)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
