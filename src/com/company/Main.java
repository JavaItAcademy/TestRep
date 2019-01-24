package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DatabaseConnector db = new DatabaseConnector();
        for (int i = 1; i < 6; i++){
            Human h = new Human(i, "Aziz Surakmatov" + i);
            System.out.println(db.insertHuman(h));
            Country kg = new Country(i, "Kyrgyzstan" + i);
            System.out.println(db.insertCountry(kg));

            City city = new City(i, "Bishkek" + i, kg,h);
            System.out.println(db.insertCity(city));
            ArrayList<City> cities = db.getCities();
            for (City c: cities) {
                if (c.name.length() > 5)
                    System.out.println(c);
            }
        }
    }



}


abstract class BaseClass {
    int id;
    String name;

    public BaseClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
class Human extends BaseClass {
    public Human(int id, String name) {
        super(id, name);
    }
}
class Country extends BaseClass {
    public Country(int id, String name) {
        super(id, name);
    }
}

class City extends BaseClass{
    Country country;
    Human human;

    public City(int id, String name, Country country, Human human) {
        super(id, name);
        this.country = country;
        this.human = human;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", human=" + human +
                ", country=" + country +
                '}';
    }
}