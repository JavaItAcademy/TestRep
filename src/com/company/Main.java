package com.company;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        DataBaseConnector db = new DataBaseConnector();
//        System.out.println("id      Fullname         Salary");
//        db.getTrainers();
//        System.out.print("\nTotal Salary = " + db.getTrainersTotalSalary());
//        db.insertCity(new City(6, "New York", 1, 3));
//        db.insertCountry(new Country(6, "Japan"));
//        db.insertHuman(new Human(6, "Aibek Ashirov"));
        ArrayList<Country> countries = new ArrayList<>();
        db.fillArrayListCountry(countries);
        for(Country cn : countries){
            System.out.println(cn);
        }
        System.out.println("=======================================");
        ArrayList<City> cities = new ArrayList<>();
        db.fillArrayListCities(cities);
        for(City c: cities){
            if(c.title.length()>5){System.out.print("\n"+ c + " -=- "+ c.title.length() + " букв");}
        }
        System.out.println("\n=======================================");
        ArrayList<Human> humans = new ArrayList<>();
        db.fillArrayListHumans(humans);
        for(Human h: humans){
            System.out.println(h);
        }


    }

}
